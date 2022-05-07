package com.example.ecommerce.Web.rest;

import com.example.ecommerce.Security.Jwt.JWTFilter;
import com.example.ecommerce.Security.Jwt.TokenProvider;

import com.example.ecommerce.Service.UserService;
import com.example.ecommerce.Utility.HeaderUtil;
import com.example.ecommerce.Web.rest.VM.LoginVM;
import com.example.ecommerce.Web.rest.errors.BadRequestAlertException;
import com.example.ecommerce.entities.Category;
import com.example.ecommerce.entities.User;
import com.example.ecommerce.repositories.RoleRepository;
import com.example.ecommerce.repositories.UserRepository;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.gson.Gson;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;


import javax.validation.Valid;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.*;
import java.util.concurrent.ExecutionException;
import java.util.stream.Collectors;

@RestController
@RequestMapping( "/api")
public class UserController {

    private final Logger log = LoggerFactory.getLogger(TokenProvider.class);
    private static final String ENTITY_NAME = "mywaybaseUser";
    private String applicationName;

    private final UserService userService;
    private final TokenProvider tokenProvider;
    private final UserRepository userRepository;
    private final AuthenticationManagerBuilder authenticationManagerBuilder;



    public UserController(UserService userService, TokenProvider tokenProvider, UserRepository userRepository, AuthenticationManagerBuilder authenticationManagerBuilder) {
        this.userService = userService;
        this.tokenProvider = tokenProvider;
        this.userRepository = userRepository;
        this.authenticationManagerBuilder = authenticationManagerBuilder;
    }

    /**
     * {@code POST  /registerUser} : Create a new user.
     *
     * @param userJson the user to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new user, or with status {@code 400 (Bad Request)} if the user has already an ID.
    * @throws URISyntaxException if the Location URI syntax is incorrect.
    */
    @PostMapping("/registerUser")
    public ResponseEntity<User> createUser( @RequestParam("user") String userJson) throws URISyntaxException, IOException, ExecutionException, InterruptedException {
        Gson g = new Gson();
        User user = g.fromJson(userJson, User.class);

        log.debug("REST request to save User : {}", user);
        if (user.getId() != null) {
            throw new BadRequestAlertException("A new user cannot already have an ID", ENTITY_NAME, "idexists");
        }
        User result = userService.registerUser(user);

        return ResponseEntity.created(new URI("/api/registerUser/" + result.getId()))
                .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
                .body(result);
    }

    @PostMapping("/authenticate")
    public ResponseEntity<JWTToken> authorize(@RequestBody LoginVM loginVM) {
        log.info("loginVM");
        System.out.println("rrrrrrrrrrrrrrrrr "+ loginVM.getUsername());
        List<User> users = userRepository.findAll();
        for (int i = 0; i < users.size(); i++) {
            System.out.println("from db"+users.get(i).getEmail());

        }
        Optional<User> userLogin = userRepository.findByEmail(loginVM.getUsername());

        System.out.println("userLogin " + userLogin);

//        ApplicationUserService s = new ApplicationUserService(userRepository);
//     UserDetails details =s.loadUserByUsername(userLogin.get().getLogin());
//        System.out.println("userDetails" + details);

//         TokenProvider token= new TokenProvider(new MyWayProperties());
//         token.createToken();
//
//               List<GrantedAuthority> authoritiies=new ArrayList<>();
//        authoritiies.add(new SimpleGrantedAuthority(userLogin.get().getAuthorities().stream().findFirst().get().getRoleName().name()));

        UsernamePasswordAuthenticationToken authenticationToken =
             new UsernamePasswordAuthenticationToken(userLogin.get().getLogin(), loginVM.getPassword());


        log.debug("authenticationToken", authenticationToken);

        System.out.println("authenticationToken " + authenticationToken);

        System.out.println("bbbbbbbbbbb");

        Authentication authentication = authenticationManagerBuilder.getObject().authenticate(authenticationToken);

        System.out.println("authentication " + authentication);
        System.out.println("teeeeest");
        SecurityContextHolder.getContext().setAuthentication(authentication);
        boolean rememberMe = (loginVM.isRememberMe() == null) ? false : loginVM.isRememberMe();


        System.out.println("rememberMe " + rememberMe);
        Optional<User> user = userService.getUserWithRolesByLogin(userLogin.get().getLogin());
        System.out.println("userrrrrrrrrrr " + user);

        UUID userId = user.get().getId();

        String login = user.get().getLogin();
        String userName = user.get().getLastname() + " " + user.get().getFirstname();

        System.out.println("login " + login);
        System.out.println("userName " + userName);
        String jwt = tokenProvider.createToken(authentication, rememberMe, userId, login, userName);

        System.out.println("jwt " + jwt);
        log.debug("jwttttt", jwt);
        org.springframework.http.HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add(JWTFilter.AUTHORIZATION_HEADER, "Bearer " + jwt);
        if (new JWTToken(jwt) == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<JWTToken>(new JWTToken(jwt), httpHeaders, HttpStatus.OK);
    }

    static class JWTToken {

        private String idToken;

        JWTToken(String idToken) {
            this.idToken = idToken;
        }

        @JsonProperty("id_token")
        String getIdToken() {
            return idToken;
        }

        void setIdToken(String idToken) {
            this.idToken = idToken;
        }
    }


    }
