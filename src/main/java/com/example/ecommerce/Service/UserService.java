package com.example.ecommerce.Service;

import com.example.ecommerce.Config.Constants;
import com.example.ecommerce.Config.StorageProperties;
import com.example.ecommerce.Security.AuthoritiesConstants;
import com.example.ecommerce.Service.exception.InvalidPasswordException;
import com.example.ecommerce.entities.Authority;
import com.example.ecommerce.entities.User;
import com.example.ecommerce.repositories.RoleRepository;
import com.example.ecommerce.repositories.UserRepository;
import org.apache.commons.io.FilenameUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.*;

@Service
@Transactional
public class UserService {

    private final Logger log = LoggerFactory.getLogger(UserService.class);

    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    private final StorageProperties storageProps;

    private final RoleRepository roleRepository;

    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder, StorageProperties storageProps, RoleRepository roleRepository) {
        this.userRepository = userRepository;
     this.passwordEncoder = passwordEncoder;
        this.storageProps = storageProps;
        this.roleRepository = roleRepository;
    }


    public Optional<User> completePasswordReset(String newPassword, String email) {
        log.debug("Reset user password {}", email);
        return userRepository.findByEmail(email)
               // .filter(user -> user.getDateCrea().isAfter(Instant.now().minusSeconds(86400)))
                .map(user -> {
                    user.setPassword(passwordEncoder.encode(newPassword));
                    user.setEmail(null);
                  //  user.setDateCrea(null);
                    return user;
                });
    }

    public User registerUser(MultipartFile files,User user) throws IOException {
        User newUser = new User();
        String encryptedPassword = passwordEncoder.encode(user.getPassword());
        newUser.setLogin(user.getLogin().toLowerCase());
        // new user gets initially a generated password
        newUser.setPassword(encryptedPassword);
        newUser.setFirstname(user.getFirstname());
        newUser.setLastname(user.getLastname());
        if (user.getEmail() != null) {
            newUser.setEmail(user.getEmail().toLowerCase());
        }

//        newUser.setImage(user.getImage());
        System.out.println("image "+ user.getImage());
        if(files != null) {


            String path = storageProps.getPath();
            // do your stuff here
            System.out.println(path);


            String realPath = path.substring(7, path.length());
            System.out.println(realPath);
            String usersFolder = realPath + "/topmaticImages/topmaticUsers/";
            Path rootUsers = Paths.get(usersFolder);
            String currentDate = new SimpleDateFormat("yyyyMMddHHmm").format(new Date());


            Files.copy(files.getInputStream(), rootUsers.resolve(files.getOriginalFilename().replace(files.getOriginalFilename(),
                    FilenameUtils.getBaseName(files.getOriginalFilename()).concat(currentDate) + "." + FilenameUtils.getExtension(files.getOriginalFilename()))));


            /*get name of image with currentDate + extension*/
            String fileName = files.getOriginalFilename();
            int locationofExtension = fileName.lastIndexOf('.');
            String extension = fileName.substring(locationofExtension, fileName.length());
            String nameWithoutExtension = fileName.substring(0, locationofExtension);


            String newNameOfImage = nameWithoutExtension + currentDate + extension;

            String newPath = storageProps.getUrl() + "/topmaticImages/topmaticUsers/" + newNameOfImage;
            newUser.setImage(newPath);
        }
        String phoneWithoutSpace = user.getPhone().replace(" ", "");
        newUser.setPhone(phoneWithoutSpace);
        newUser.setSexe(user.getSexe());
        newUser.setAdresse(user.getAdresse());
        newUser.setDateNaissance(user.getDateNaissance());
        newUser.setActivated(true);

        Set<Authority> roles = new HashSet<>();
      //  roleRepository.findByRoleName(RoleName.ROLE_ADMIN).ifPresent(roles::add);
        roleRepository.findById(AuthoritiesConstants.ADMIN).ifPresent(roles::add);
        System.out.println("roles "+ roles);
//        Optional<Role> role = roleRepository.findByRoleName(RoleName.ROLE_ADMIN);
        System.out.println("role of user"+roles);
        newUser.setAuthorities(roles);
        DateTimeFormatter formatter = DateTimeFormatter
                .ofPattern("yyyy-MM-dd HH:mm:ss")
                .withLocale(Locale.getDefault())
                .withZone(ZoneId.systemDefault());

        Instant now = Instant.now();
        String formatted = formatter.format(now);


//        List<GrantedAuthority> authoritiies=new ArrayList<>();
//        authoritiies.add(new SimpleGrantedAuthority(userLogin.get().getRoles().stream().findFirst().get().getRoleName().name()));
        userRepository.save(newUser);
        log.debug("Created Information for User: {}", newUser);

        return newUser;

    }
    public void deleteUser(String login) {
        userRepository.findByLogin(login).ifPresent(user -> {
            userRepository.delete(user);
            // this.clearUserCaches(user);
            log.debug("Deleted User: {}", user);
        });
    }
    @Transactional
    public void changePassword(User user, String currentClearTextPassword, String newPassword) {

                    String currentEncryptedPassword = user.getPassword();
                    if (!passwordEncoder.matches(currentClearTextPassword, currentEncryptedPassword)) {
                        throw new InvalidPasswordException();
                    }
                    String encryptedPassword = passwordEncoder.encode(newPassword);
                    user.setPassword(encryptedPassword);
                    // this.clearUserCaches(user);
                    log.debug("Changed password for User: {}", user);
                };

    @Transactional(readOnly = true)
    public Page<User> getAllManagedUsers(Pageable pageable) {
        return userRepository.findAllByLoginNot(pageable, Constants.ANONYMOUS_USER).map(user -> new User());
    }


    @Transactional(readOnly = true)
    public Optional<User> getUserWithRolesByLogin(String login) {
        return userRepository.findOneWithAuthoritiesByLogin(login);
    }

//    @Transactional(readOnly = true)
//    public Optional<User> findOneWithRoleByEmail(String email) {
//      Optional<User> u=  userRepository.findByEmail(email).map(user -> {
//            Optional<Role> role = roleRepository.findByRoleName(RoleName.ROLE_ADMIN);
//           System.out.println("role of user"+role.get());
//            user.setRole(role.get());
//            return user;
//        });
//           return  u;
//    }


    /**
     * Gets a list of all the ROLES.
     * @return a list of all the ROLES.
     */
//    @Transactional(readOnly = true)
//    public List<RoleName> getRoles() {
//        return roleRepository.findAll().stream().map(Authority::getRoleName).collect(Collectors.toList());
//    }


    /**
     * Get one user by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<User> findOne(UUID id) {
        log.debug("Request to get User : {}", id);
        return userRepository.findById(id);
    }

    public Boolean updateEmail(UUID id, String oldEmail, String newEmail){
        System.out.println(oldEmail);
        System.out.println(newEmail);
        Optional<User> user = userRepository.findById(id);

        String userEmail = user.get().getEmail();

        Boolean result = true;
        if( ! userEmail.equals(oldEmail)){
            result =  false;
        }

        return result;
    }

    public Optional<User> getUserWithRolesByEmail(String email) {
        return userRepository.findOneWithAuthoritiesByEmail(email);
    }
}


