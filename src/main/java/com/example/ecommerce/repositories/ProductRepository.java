package com.example.ecommerce.repositories;

import com.example.ecommerce.entities.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;
@Repository
public interface ProductRepository extends JpaRepository<Product, UUID> {

    @Query("select count(p) from Product p")
    Long stat_findTotalProducts();
//
//    @Query("select distinct (p.item) from Product p  where p.id = ?1")
//    List<Item> getAllItems_ofProduct(UUID productId);

    @Query("select sum(p.qttInStock) from Product p ")
    BigDecimal stat_stock_products_quantity();

    Product findByName(String name);

    @Query("SELECT p  from Product p  where  p.deleted= false ")
    Page<Product> findAllP(Pageable pageable);

//    @Query("SELECT p from Product p where  p.subCategory.name = ?1")
//    List<Product> findAll_hasSubCategory(String subCategory);

    @Query("SELECT p from Product p where  p.mark.name = ?1")
    List<Product> findAll_hasMark(String mark);

//    @Query("SELECT p from Product p where  p.mark.name IN ?1 and p.subCategory.name in ?2 ")
//    List<Product> findAll_InMark_hasSubCategory(List<String> markList, List<String> subCategoryList);
}
