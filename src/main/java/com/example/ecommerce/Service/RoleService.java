package com.example.ecommerce.Service;


import com.example.ecommerce.entities.Authority;
import com.example.ecommerce.repositories.RoleRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class RoleService {

    private final RoleRepository roleRepository;

    public RoleService(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }


    /**
     * Save a role.
     *
     * @param role the entity to save.
     * @return the persisted entity.
     */
    public Authority save(Authority role){

        return roleRepository.save(role);
    }

    public void createRoles() {

        List<Authority> roleList = roleRepository.findAll();
        if (roleList.size() == 0) {
            //ADMIN
            Authority ADMIN = new Authority();
            //ADMIN.setId(UUID.fromString("b6c4042e-af50-11ec-b909-0242ac120002"));
            ADMIN.setName("ROLE_ADMIN");

            roleRepository.save(ADMIN);

            //CLIENT
            Authority CLIENT = new Authority();
            //ADMIN.setId(UUID.fromString("d13468d0-5e40-4944-bc8a-52ca2bd84adc"));
            CLIENT.setName("ROLE_CLIENT");

            roleRepository.save(CLIENT);

            //INTERNAUTE
            Authority INTERNAUTE = new Authority();
            //ADMIN.setId(UUID.fromString("ba769c1c-79c3-4ab7-b9d5-87b31d39415b"));
            INTERNAUTE.setName("ROLE_INTERNAUTE");

            roleRepository.save(INTERNAUTE);

        }
    }

}
