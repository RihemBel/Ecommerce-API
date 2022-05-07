package com.example.ecommerce.repositories;

import com.example.ecommerce.entities.Authority;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface RoleRepository extends JpaRepository<Authority, String> {

    //Optional<Role> findByRoleName(String role);

   // Optional<Authority> findByRoleName(RoleName roleName);
}
