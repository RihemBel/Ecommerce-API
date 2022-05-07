package com.example.ecommerce.repositories;

import com.example.ecommerce.entities.ImageItem;
import com.example.ecommerce.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface ImageRepository extends JpaRepository<ImageItem, UUID> {

    Optional<ImageItem> deleteById(Long id);

    Optional<ImageItem> findById(Long id);
}
