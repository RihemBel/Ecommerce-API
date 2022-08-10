package com.example.ecommerce.repositories;

import com.example.ecommerce.entities.Order;
import com.example.ecommerce.entities.SubCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface OrderRepository extends JpaRepository<Order, UUID> {
    @Query("SELECT o  from Order o left join fetch o.user where  o.user.id= ?1")
    List<Order> findAllOrderUser(UUID userId);

}
