package com.example.ecommerce.Web.rest;

import com.example.ecommerce.Service.CategoryService;
import com.example.ecommerce.Service.MarkService;
import com.example.ecommerce.Service.ProductService;
import com.example.ecommerce.Service.SubCategoryService;
import com.example.ecommerce.Utility.HeaderUtil;
import com.example.ecommerce.Utility.ResponseUtil;
import com.example.ecommerce.Web.rest.VM.ProductWithHisItems;
import com.example.ecommerce.Web.rest.errors.BadRequestAlertException;
import com.example.ecommerce.entities.Category;
import com.example.ecommerce.entities.Mark;
import com.example.ecommerce.entities.Product;
import com.example.ecommerce.entities.SubCategory;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/api")
public class ProductController {

    private final Logger log = LoggerFactory.getLogger(ProductController.class);

    private static final String ENTITY_NAME = "mywaybaseProduct";

    private String applicationName;

    private final ProductService productService;

     private final MarkService markService;

     private final SubCategoryService subCategoryService;

     private  final CategoryService categoryService;

    public ProductController(ProductService productService, MarkService markService, SubCategoryService subCategoryService, CategoryService categoryService) {
        this.productService = productService;

        this.markService = markService;
        this.subCategoryService = subCategoryService;
        this.categoryService = categoryService;
    }

    /**
     * {@code POST  /products} : Create a new product.
     *
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new product, or with status {@code 400 (Bad Request)} if the product has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/products")
    public ResponseEntity<Product> createArticle(@Valid @RequestBody Product product) throws URISyntaxException, IOException {
        System.out.println("productss "+ product);
//        Gson g = new Gson();
//        JsonObject object = g.fromJson(productJson,JsonObject.class);
//        Product product = g.fromJson(productJson, Product.class);
//        UUID mark_id= UUID.fromString(object.get("mark_id").getAsString());
//        UUID subCategory_id= UUID.fromString(object.get("subCategory_id").getAsString());
//        UUID category_id= UUID.fromString(object.get("category_id").getAsString());
//        Optional<Mark> mark= markService.findOne(mark_id);
//        Optional<SubCategory> subCategory= subCategoryService.findOne(subCategory_id);
//        Optional<Category> category= categoryService.findOne(category_id);
//        log.debug("REST request to save mark : {}", mark);
//        log.debug("REST request to save subCategory : {}", subCategory);
//        log.debug("REST request to save category : {}", category);
        log.debug("REST request to save product : {}", product);
//        product.setCategory(category.get());
//        product.setSubCategory(subCategory.get());
//        product.setMark(mark.get());
        if (product.getId() != null) {
            throw new BadRequestAlertException("A new product cannot already have an ID", ENTITY_NAME, "idexists");
        }

        Product result = productService.save( product);
        return ResponseEntity.created(new URI("/api/products/" + result.getId()))
                .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
                .body(result);

    }

    @PutMapping("/products")
    public @ResponseBody
    ResponseEntity<Product> updateArticle(@Valid @RequestBody Product product) throws URISyntaxException, IOException {

        Gson g = new Gson();
//        Product product = g.fromJson(productJson, Product.class);

        log.debug("REST request to update Product : {}", product);
        if (product.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }

        Product result = productService.save( product);
        return ResponseEntity.ok()
                .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
                .body(result);
    }

    /**
     * {@code GET  /products} : get all the products.
     */
    @GetMapping("/products/productsWithItems")
    public ResponseEntity<List<ProductWithHisItems>> getAllProductsWithItems(Pageable pageable, Sort sort) {
        log.debug("REST request to get a page of Products with their items");

        Page<ProductWithHisItems> result = productService.findAllWithItems(pageable, sort);

        return new ResponseEntity(result, HttpStatus.OK);


    }


    @GetMapping("/products/productWithItems/{id}")
    public ResponseEntity<ProductWithHisItems> getProductWithItems(@PathVariable UUID id) {
        log.debug("REST request to get Product with his items");

        ProductWithHisItems result = productService.findOneWithHisItems(id);

        return new ResponseEntity<>(result, HttpStatus.OK);

    }

    /**
     * {@code GET  /products} : get all the products
     *
     * @param pageable the pagination information.
     *                 // * @param eagerload flag to eager load entities from relationships (This is applicable for many-to-many).
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of products in body.
     */
    @GetMapping("/products")
    public ResponseEntity<List<Product>> getAllProducts(@PageableDefault( value = Integer.MAX_VALUE)  Pageable pageable) {
        log.debug("REST request to get a page of Products");
        Page<Product> result = productService.findAll(pageable);

        return new ResponseEntity(result, HttpStatus.OK);

    }


    /**
     * {@code GET  /products/:id} : get the "id" product.
     *
     * @param id the id of the product to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the product, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/products/{id}")
    public ResponseEntity<Product> getProduct(@PathVariable UUID id) {
        log.debug("REST request to get Product : {}", id);
        Optional<Product> product = productService.findOne(id);
        return ResponseUtil.wrapOrNotFound(product);
    }

    /**
     * {@code DELETE  /products/:id} : delete the "id" product.
     *
     * @param id the id of the product to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/products/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable UUID id) throws IOException {
        log.debug("REST request to delete Product : {}", id);
        productService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }


    /**
     * {@code GET  /productS/:name} : get the "name" product.
     *
     * @param name the name of the product to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the product, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/products/checkName/{name}")
    public ResponseEntity<Boolean> findByName(@PathVariable String name) {
        log.debug("REST request to check if name exist : {}", name);
        Boolean result = productService.findByName(name);
        return new ResponseEntity<>(result, HttpStatus.OK);

    }
}