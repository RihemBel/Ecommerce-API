package com.example.ecommerce.repositories;

import com.example.ecommerce.entities.Mark;
import com.example.ecommerce.entities.SubCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface MarkRepository extends JpaRepository<Mark, UUID> {

    @Query("select count(m) from Mark m ")
    Long stat_findTotalMarks();

    Mark findByName(String name);

   List<Mark> findAll();

    @Query("SELECT m  from Mark m  where  m.deleted= false ")
    List<Mark> findAllM();
}
