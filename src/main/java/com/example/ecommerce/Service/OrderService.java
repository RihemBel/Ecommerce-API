package com.example.ecommerce.Service;

import com.example.ecommerce.entities.Order;
import com.example.ecommerce.entities.Product;
import com.example.ecommerce.entities.ProductOrder;
import com.example.ecommerce.entities.ProductOrderPk;
import com.example.ecommerce.repositories.OrderRepository;
import com.example.ecommerce.repositories.ProductOrderRepository;
import com.example.ecommerce.repositories.ProductRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;

@Service
@Transactional
public class OrderService {

    private final Logger log = LoggerFactory.getLogger(OrderService.class);

    private final OrderRepository orderRepository;
    private final ProductOrderRepository productOrderRepository;
    private final ProductRepository productRepository;



    public OrderService(OrderRepository orderRepository, ProductOrderRepository productOrderRepository, ProductRepository productRepository) {

        this.orderRepository = orderRepository;
        this.productOrderRepository = productOrderRepository;
        this.productRepository = productRepository;
    }


    /**
     * Get all the orders.
     *
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public List<Order> findAll() {
        log.debug("Request to get all Orders");
        return orderRepository.findAll();
    }

    /**
     * Get ORDERS by users.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public List<Order> findAllOrdersU(UUID id) {
        log.debug("Request to get Order : {}", id);
        return orderRepository.findAllOrderUser(id);
    }
   /**
           * Get one order by id.
     *
             * @param id the id of the entity.
            * @return the entity.
            */
    @Transactional(readOnly = true)
    public Optional<Order> findOne(UUID id) {
        log.debug("Request to get Order : {}", id);
        return orderRepository.findById(id);
    }

    /**
     * Save an order.
     *
     * @param order the entity to save.
     * @return the persisted entity.
     */
    public Order saveOrder(Order order) {
       /* Set<ProductOrder> pos = order.getProductOrder();
        System.out.println("size of product order "+pos.size());
        Order o = orderRepository.save(order);
        for (ProductOrder po : pos) {
            System.out.println("product order "+po);
            po.setOrder(o);
            productOrderRepository.save(po);

        }*/
        System.out.println("order "+order);
        Order orderSaved = orderRepository.save(order);

        for (ProductOrder productOrder : order.getProductOrder()) {
            ProductOrderPk productOrderPk = new ProductOrderPk(orderSaved.getId(),productOrder.getProduct().getId());
            ProductOrder productOrder1 = new ProductOrder();
            productOrder1.setProductOrderPk(productOrderPk);
            productOrder1.setOrder(orderSaved);
            productOrder1.setNbProd(productOrder.getNbProd());
            productOrder1.setPrice(productOrder.getProduct().getPrice());
            productOrder1.setProduct(productOrder.getProduct());
            productOrderRepository.save(productOrder1);
        }

        return order;
    }
    /**
     * Delete the order by id.
     *
     * @param id the id of the entity.
     */
    public void delete(UUID id) {
        log.debug("Request to delete Order : {}", id);
        orderRepository.deleteById(id);
    }
}
