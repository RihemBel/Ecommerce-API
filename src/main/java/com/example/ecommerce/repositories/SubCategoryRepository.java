package com.example.ecommerce.repositories;

import com.example.ecommerce.entities.SubCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface SubCategoryRepository extends JpaRepository<SubCategory , UUID> {

    @Modifying
    @Query("DELETE SubCategory subCa WHERE subCa.category.id = ?1")
    void deleteByCategoryId(int categoryId);

    @Query("SELECT s  from SubCategory s left join fetch s.category where  s.category.id= ?1")
    List<SubCategory> findAllSubCa(UUID categoryId);

    @Query("SELECT s  from SubCategory s  where  s.deleted= false ")
    List<SubCategory> findAllSuba();
}
