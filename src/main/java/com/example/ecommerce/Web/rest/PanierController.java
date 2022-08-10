//package com.example.ecommerce.Web.rest;
//
//
//import com.example.ecommerce.Service.PanierService;
//import com.example.ecommerce.Utility.HeaderUtil;
//import com.example.ecommerce.Utility.ResponseUtil;
//import com.example.ecommerce.Web.rest.errors.BadRequestAlertException;
//import com.example.ecommerce.entities.Order;
//import com.example.ecommerce.entities.Panier;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.data.domain.Page;
//import org.springframework.data.domain.Pageable;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.*;
//
//import javax.validation.Valid;
//import java.net.URI;
//import java.net.URISyntaxException;
//import java.util.List;
//import java.util.Optional;
//import java.util.UUID;
//
//@RestController
//@RequestMapping("/api")
//public class PanierController {
//
//    private final Logger log = LoggerFactory.getLogger(PanierController.class);
//
//    private static final String ENTITY_NAME = "mywaybasePanier";
//
//    private String applicationName;
//
//    private final PanierService panierService;
//
//    public PanierController(PanierService panierService) {
//        this.panierService = panierService;
//    }
//
//
//    /**
//     * {@code POST  /paniers} : Create a new panier.
//     *
//     * @param panier the panier to create.
//     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new panier, or with status {@code 400 (Bad Request)} if the panier has already an ID.
//     * @throws URISyntaxException if the Location URI syntax is incorrect.
//     */
//    @PostMapping("/paniers")
//    public ResponseEntity<Panier> createPanier(@Valid @RequestBody Panier panier) throws URISyntaxException {
//        log.debug("REST request to save Panier : {}", panier);
//        if (panier.getId() != null) {
//            throw new BadRequestAlertException("A new panier cannot already have an ID", ENTITY_NAME, "idexists");
//        }
//        Panier result = panierService.save(panier);
//        return ResponseEntity.created(new URI("/api/paniers/" + result.getId()))
//                .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
//                .body(result);
//    }
//    /**
//     * {@code GET  /paniers} : get all the paniers.
//     *
//     * @param pageable the pagination information.
//     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of variantValue in body.
//     */
//    @GetMapping("/paniers")
//    public ResponseEntity<List<Panier>> getAllPaniers(Pageable pageable) {
//        log.debug("REST request to get a page of Paniers");
//        Page<Panier> page = (Page<Panier>) panierService.findAll(pageable);
//
//        return new ResponseEntity(page, HttpStatus.OK);
//    }
//
//    /**
//     * {@code GET  /paniers/:id} : get the "id" panier.
//     *
//     * @param id the id of the variant to retrieve.
//     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the panier, or with status {@code 404 (Not Found)}.
//     */
//    @GetMapping("/paniers/{id}")
//    public ResponseEntity<Panier> getPanier(@PathVariable UUID id) {
//        log.debug("REST request to get Panier : {}", id);
//        Optional<Panier> panier = panierService.findOne(id);
//        return ResponseUtil.wrapOrNotFound(panier);
//    }
//
//
//    /**
//     * {@code DELETE  /paniers/:id} : delete the "id" panier.
//     *
//     * @param id the id of the panier to delete.
//     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
//     */
//    @DeleteMapping("/paniers/{id}")
//    public ResponseEntity<Void> deletePanier(@PathVariable UUID id) {
//        log.debug("REST request to delete Panier : {}", id);
//        panierService.delete(id);
//        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
//    }
//
//}
