package com.example.ecommerce.repositories;

import com.example.ecommerce.entities.ProductOrder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface ProductOrderRepository extends JpaRepository<ProductOrder, UUID> {

    //calculate total lignepaniers
    @Query("select count(lp) from ProductOrder lp")
    Integer findTotalProductOrders();}
