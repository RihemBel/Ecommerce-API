package com.example.ecommerce.Service;


import com.example.ecommerce.entities.Product;
import com.example.ecommerce.entities.ProductOrder;
import com.example.ecommerce.repositories.ProductOrderRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Array;
import java.util.*;

@Service
@Transactional
public class ProductOrderService {
    private final Logger log = LoggerFactory.getLogger(ProductOrder.class);
    private final ProductOrderRepository productOrderRepository;

    public ProductOrderService( ProductOrderRepository productOrderRepository) {
        this.productOrderRepository = productOrderRepository;
    }

    /**
     * Save a productOrder.
     *
     * @param productOrder the entity to save.
     * @return the persisted entity.
     */
    public ProductOrder save(ProductOrder productOrder) {
        return productOrderRepository.save(productOrder);

    }

    /**
     * Save all productOrders.
     *
     * @return the persisted entity.
     */
    public Set<ProductOrder> saveOrders(Set<ProductOrder> productOrders) {
        Set<ProductOrder> pos = new HashSet<ProductOrder>();

        for(ProductOrder productOrder : productOrders) {
            ProductOrder po = productOrderRepository.save(productOrder);
            pos.add(po);
        }
        return pos;
    }

    /**
     * Delete the productOrder by id.
     *
     * @param id the id of the entity.
     */
    public void delete(UUID id) {
        log.debug("Request to delete ProductOrder : {}", id);
        productOrderRepository.deleteById(id);
    }

    /**
     * Get one lignePanier by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<ProductOrder> findOne(UUID id) {
        log.debug("Request to get LignePanier : {}", id);
        return productOrderRepository.findById(id);
    }

    /**
     *
     * @return  total of lignePaniers
     */
    public Integer calculate_total_lignepaniers() {
        Integer total_lignepaniers = productOrderRepository.findTotalProductOrders();
        return total_lignepaniers;
    }

    /**
     * Get all the paniers.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Page<ProductOrder> findAll(Pageable pageable) {
        log.debug("Request to get all LignePaniers");
        return productOrderRepository.findAll(pageable);
    }

}
