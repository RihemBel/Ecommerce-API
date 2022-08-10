package com.example.ecommerce.Web.rest;


import com.example.ecommerce.Service.ProductOrderService;
import com.example.ecommerce.Utility.HeaderUtil;
import com.example.ecommerce.Utility.ResponseUtil;
import com.example.ecommerce.entities.ProductOrder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/api")
public class ProductOrderController {

    private final Logger log = LoggerFactory.getLogger(ProductOrderController.class);

    private static final String ENTITY_NAME = "mywaybaseLignePanier";

    private String applicationName;

    private final ProductOrderService productOrderService;

    public ProductOrderController( ProductOrderService productOrderService) {
        this.productOrderService = productOrderService;
    }


    /**
     * {@code POST  /lignePanier} : Create a new lignePanier.
     */
    @PostMapping("/lignePanier")
    public void createLignePanier(@RequestBody ProductOrder lp) {

        productOrderService.save(lp);
    }

    /**
     * {@code GET  /lignePanier} : get all the lignePanier.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of variantValue in body.
     */
    @GetMapping("/lignePanier")
    public ResponseEntity<List<ProductOrder>> getAlllignePanier(Pageable pageable) {
        log.debug("REST request to get a page of lignePanier");
        Page<ProductOrder> page = (Page<ProductOrder>) productOrderService.findAll(pageable);

        return new ResponseEntity(page, HttpStatus.OK);
    }

    /**
     * {@code GET  /lignePanier/:id} : get the "id" lignePanier.
     *
     * @param id the id of the variant to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the lignePanier, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/lignePanier/{id}")
    public ResponseEntity<ProductOrder> getLignePanier(@PathVariable UUID id) {
        log.debug("REST request to get Panier : {}", id);
        Optional<ProductOrder> lignePanier = productOrderService.findOne(id);
        return ResponseUtil.wrapOrNotFound(lignePanier);
    }


    /**
     * {@code DELETE  /lignePanier/:id} : delete the "id" lignePanier.
     *
     * @param id the id of the lignePanier to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/lignePanier/{id}")
    public ResponseEntity<Void> deleteLignePanier(@PathVariable UUID id) {
        log.debug("REST request to delete LignePanier : {}", id);
        productOrderService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }


}
