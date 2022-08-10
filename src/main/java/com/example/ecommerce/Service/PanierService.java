//package com.example.ecommerce.Service;
//
//import com.example.ecommerce.entities.Item;
//import com.example.ecommerce.entities.Panier;
//import com.example.ecommerce.entities.Product;
//import com.example.ecommerce.repositories.PanierRepository;
//import com.example.ecommerce.repositories.PivvRepository;
//import com.example.ecommerce.repositories.ProductRepository;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.data.domain.Page;
//import org.springframework.data.domain.Pageable;
//import org.springframework.stereotype.Service;
//import org.springframework.transaction.annotation.Transactional;
//
//import java.math.BigDecimal;
//import java.util.List;
//import java.util.Optional;
//import java.util.Set;
//import java.util.UUID;
//
//@Service
//@Transactional
//public class PanierService {
//
//    private final Logger log = LoggerFactory.getLogger(PanierService.class);
//    private final PanierRepository panierRepository;
//    private final PivvRepository pivvRepository;
//    private final ProductRepository productRepository;
//
//    public PanierService(PanierRepository panierRepository, PivvRepository pivvRepository, ProductRepository productRepository) {
//        this.panierRepository = panierRepository;
//        this.pivvRepository = pivvRepository;
//        this.productRepository = productRepository;
//    }
//
//
////    /**
////     * Save a panier.
////     *
////     * @param panier the entity to save.
////     * @return the persisted entity.
////     */
////    public Panier save(Panier panier) {
////        return panierRepository.save(panier);
////
////    }
//
//    /**
//     * Save a movement.
//     *
//     * @param panier the entity to save.
//     * @return the persisted entity.
//     */
//    public Panier save(Panier panier) {
//        log.debug("Request to save panier : {}", panier);
////        for (Item item : panier.getItem()) {
//////            Item item = panier.getItem();
//////        }
////            Product product = pivvRepository.getProduct_ofItem(item.getId());
//////        if(movement.getType().equals("INPUT")){
//////            item.setStockQuantity(item.getStockQuantity().add(movement.getQuantity()));
//////            itemRepository.save(item);
////
////            if (product.getHasVariant() == true) {
////                BigDecimal newTotalQuantity = BigDecimal.valueOf(0);
////                List<Item> itemList = pivvRepository.getAllItems_ofProduct(product.getId());
////                for (Item item1 : itemList) {
////                    System.out.println("item1 " + item1);
////                    System.out.println("item1.getStockQuantity() " + item1.getQttInStock());
////
////                    if (item1.getQttInStock().compareTo(BigDecimal.ZERO) > 0) {
////                        newTotalQuantity = newTotalQuantity.add(item1.getQttInStock());
////                        System.out.println("newTotalQuantity " + newTotalQuantity);
////                    }
////                }
////                product.setQttInStock(newTotalQuantity);
////
////            } else {
////                product.setQttInStock(item.getQttInStock());
////            }
////
////
////            productRepository.save(product);
////        }
//        Panier ppp= panierRepository.save(panier);
//        for(Product product : panier.getProduct()) {
//           Product pp= productRepository.getById(product.getId());
//           Set<Panier> p=pp.getPanier();
//          p.add(ppp);
//          pp.setPanier(p);
//            productRepository.save(pp);
//        }
//        return ppp ;
//        }
//    /**
//     * Get all the paniers.
//     *
//     * @param pageable the pagination information.
//     * @return the list of entities.
//     */
//    @Transactional(readOnly = true)
//    public Page<Panier> findAll(Pageable pageable) {
//        log.debug("Request to get all Paniers");
//        return panierRepository.findAll(pageable);
//    }
//
//    /**
//     * Get one panier by id.
//     *
//     * @param id the id of the entity.
//     * @return the entity.
//     */
//    @Transactional(readOnly = true)
//    public Optional<Panier> findOne(UUID id) {
//        log.debug("Request to get Panier : {}", id);
//        return panierRepository.findById(id);
//    }
//
//    /**
//     * Delete the panier by id.
//     *
//     * @param id the id of the entity.
//     */
//    public void delete(UUID id) {
//        log.debug("Request to delete Panier : {}", id);
//        panierRepository.deleteById(id);
//    }
//    /**
//     *
//     * @return  total of paniers
//     */
//    public Integer calculate_total_paniers() {
//        Integer total_paniers = panierRepository.findTotalPaniers();
//        return total_paniers;
//    }
//
//}
