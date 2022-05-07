package com.example.ecommerce;

import com.example.ecommerce.Service.CategoryService;
import com.example.ecommerce.Service.RoleService;
import com.example.ecommerce.entities.Category;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;




@SpringBootApplication
public class EcommerceApplication  implements CommandLineRunner {

    private final RoleService roleService;

    public EcommerceApplication(RoleService roleService) {
        this.roleService = roleService;
    }

    public static void main(String[] args) {

        SpringApplication.run(EcommerceApplication.class, args);

    }


    @Override
    public void run(String... args) throws Exception {

        roleService.createRoles();
    }

}

