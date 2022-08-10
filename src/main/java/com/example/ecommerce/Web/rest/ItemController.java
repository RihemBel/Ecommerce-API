package com.example.ecommerce.Web.rest;


import com.example.ecommerce.Service.ImageService;
import com.example.ecommerce.Service.ItemService;
import com.example.ecommerce.Service.ProductService;
import com.example.ecommerce.Utility.HeaderUtil;
import com.example.ecommerce.Utility.ResponseUtil;
import com.example.ecommerce.Web.rest.errors.BadRequestAlertException;
import com.example.ecommerce.entities.*;
import com.example.ecommerce.repositories.ItemRepository;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.*;
import java.util.concurrent.ExecutionException;

@RestController
@RequestMapping("/api")
public class ItemController {

    private final Logger log = LoggerFactory.getLogger(ItemController.class);

    private static final String ENTITY_NAME = "mywaybaseItem";

    private String applicationName;

    private final ItemService itemService;

    private final ImageService imageService;

    private final ProductService productService;

    private final ItemRepository itemRepository;


    public ItemController(ItemService itemService, ImageService imageService, ProductService productService, ItemRepository itemRepository) {
        this.itemService = itemService;
        this.imageService = imageService;
        this.productService = productService;
        this.itemRepository = itemRepository;
    }

    private static final List<User> USERS = Arrays.asList(
            new User(UUID.fromString("2de63968-ac23-11ec-b909-0242ac120002"), "anna" ,"Smith")
//            new User("Maria", "Jones"),
//            new User("Anna", "Smith")
    );


//    /**
//     * {@code POST  /items} : Create a new item.
//     *
//     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new item, or with status {@code 400 (Bad Request)} if the item has already an ID.
//     * @throws URISyntaxException if the Location URI syntax is incorrect.
//     */
//    @PostMapping("/items")
//    public ResponseEntity<Item> createItem(@Valid @RequestBody Item item) throws URISyntaxException, IOException {
//        Gson g = new Gson();
//        JsonObject object = g.fromJson(subCategoryJson,JsonObject.class);
//        SubCategory subCategory = g.fromJson(subCategoryJson, SubCategory.class);
//        UUID category_id= UUID.fromString(object.get("category_id").getAsString());
//        Optional<Category> category= categoryService.findOne(category_id);
//        log.debug("REST request to save category : {}", category);
//        log.debug("REST request to save SubCategory : {}", subCategory);
//        subCategory.setCategory(category.get());
//
//        if (subCategory.getId() != null) {
//            throw new BadRequestAlertException("A new subCategory cannot already have an ID", ENTITY_NAME, "idexists");
//        }
//        Item result = itemService.save(item);
//        return ResponseEntity.created(new URI("/api/items/" + result.getId()))
//                .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
//                .body(result);
//    }


    /**
     * {@code POST  /ITEMS} : Create a new item.
     *
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new item, or with status {@code 400 (Bad Request)} if the item has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/items")
    public ResponseEntity<Item> createItem(@Valid @RequestBody Item item) throws URISyntaxException, IOException, ExecutionException, InterruptedException {

        log.debug("REST request to save item : {}", item);

        if (item.getId() != null) {
            throw new BadRequestAlertException("A new item cannot already have an ID", ENTITY_NAME, "idexists");
        }
        Item result = itemService.save(item);

        return ResponseEntity.created(new URI("/api/items/" + result.getId()))
                .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
                .body(result);
    }


//    /**
//            * {@code PUT  /items} : Updates an existing item.
//            *
//            * @param item the item to update.
//     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated item,
//     * or with status {@code 400 (Bad Request)} if the item is not valid,
//            * or with status {@code 500 (Internal Server Error)} if the item couldn't be updated.
//            * @throws URISyntaxException if the Location URI syntax is incorrect.
//            */

//    @PutMapping("/items")
//    public ResponseEntity<Item> updateItem(@Valid @RequestBody Item item) throws URISyntaxException, IOException {
//        log.debug("REST request to update Item : {}", item);
//        if (item.getId() == null) {
//            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
//        }
//
//        Item result = itemService.save(item);
//        return ResponseEntity.ok()
//                .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
//                .body(result);
//    }


    @GetMapping("/items")
    public ResponseEntity<List<Item>> getAllItems() {
        log.debug("REST request to get a page of Orders");
        List<Item> result =  itemService.findAll();

        return new ResponseEntity(result, HttpStatus.OK);
    }

    /**
     * {@code GET  /items/:id} : get the "id" item.
     *
     * @param id the id of the item to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the item, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/items/{id}")
    public ResponseEntity<Item> getItem(@PathVariable UUID id) {
        log.debug("REST request to get Item : {}", id);
        Optional<Item> item = itemService.findOne(id);
        return ResponseUtil.wrapOrNotFound(item);
    }

    /**
     * {@code DELETE  /items/:id} : delete the "id" item.
     *
     * @param id the id of the item to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/items/{id}")
    public ResponseEntity<Void> deleteItem(@PathVariable UUID id) throws IOException {
        log.debug("REST request to delete Item : {}", id);
        itemService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }

    //used to delete item before update product
//    @DeleteMapping("/items/delete/{id}")
//    public ResponseEntity<Void> deleteItemToUpdateProduct(@PathVariable UUID id) throws IOException {
//        log.debug("REST request to delete Item : {}", id);
//        itemService.deleteToUpdateProduct(id);
//        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
//    }
}
