package com.example.ecommerce.Web.rest;


import com.example.ecommerce.Service.CategoryService;
import com.example.ecommerce.Service.SubCategoryService;
import com.example.ecommerce.Utility.HeaderUtil;
import com.example.ecommerce.Utility.ResponseUtil;
import com.example.ecommerce.Web.rest.errors.BadRequestAlertException;
import com.example.ecommerce.entities.Category;
import com.example.ecommerce.entities.SubCategory;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.MessageSource;
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
import java.util.concurrent.ExecutionException;

@RestController
@RequestMapping("/api")
public class SubCategoryController {

    private final Logger log = LoggerFactory.getLogger(MarkController.class);

    private static final String ENTITY_NAME = "mywaybaseSubCategory";

    private String applicationName;

    private final SubCategoryService subCategoryService;
private final CategoryService categoryService;
    private final MessageSource messageSource;

    public SubCategoryController(SubCategoryService subCategoryService, CategoryService categoryService, MessageSource messageSource) {
        this.subCategoryService = subCategoryService;
        this.categoryService = categoryService;
        this.messageSource = messageSource;
    }

    /**
     * {@code POST  /subCategories} : Create a new subCategory.
     *
//     * @param subCategoryJson the category to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new subCategory, or with status {@code 400 (Bad Request)} if the subCategory has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/subCategories")
    public ResponseEntity<SubCategory> createSubCategory(@Valid @RequestBody SubCategory subCategory) throws URISyntaxException, IOException, ExecutionException, InterruptedException {
//        log.debug("here: " + subCategoryJson);
        Gson g = new Gson();
//        JsonObject object = g.fromJson(subCategory,JsonObject.class);
//        SubCategory subCategory = g.fromJson(subCategoryJson, SubCategory.class);
//        UUID category_id= UUID.fromString(object.get("category_id").getAsString());
//        Optional<Category> category= categoryService.findOne(category_id);
//        System.out.println("files"+files);
//        log.debug("REST request to save category : {}", category);
        log.debug("REST request to save SubCategory : {}", subCategory);
//        subCategory.setCategory(category.get());

        if (subCategory.getId() != null) {
            throw new BadRequestAlertException("A new subCategory cannot already have an ID", ENTITY_NAME, "idexists");
        }
        SubCategory result = subCategoryService.save(subCategory);

        return ResponseEntity.created(new URI("/api/subCategories/" + result.getId()))
                .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
                .body(result);
    }

    /**
     * {@code PUT  /subCategories} : Updates an existing subCategory.
     *
//     * @param subCategoryJson the category to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated subCategory,
     * or with status {@code 400 (Bad Request)} if the category is not valid,
     * or with status {@code 500 (Internal Server Error)} if the category couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */


    @PutMapping("/subCategories")
    public ResponseEntity<SubCategory> updateSubCategory(@Valid @RequestBody SubCategory subCategory) throws URISyntaxException, IOException, ExecutionException, InterruptedException {

        Gson g = new Gson();
//        SubCategory subCategory = g.fromJson(subCategoryJson , SubCategory.class);

        log.debug("REST request to update SubCategory : {}", subCategory);
        if (subCategory.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        SubCategory result = subCategoryService.save( subCategory);
        return ResponseEntity.ok()
                .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, subCategory.getId().toString()))
                .body(result);
    }
    /**
     * {@code GET  /sub-categories} : get all the subCategories.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of subCategories in body.
     */
    @GetMapping("/sub-categories")
    public ResponseEntity<List<SubCategory>> getAllSubCategories() {
        log.debug("REST request to get a page of SubCategories");
        List<SubCategory> page = (List<SubCategory>) subCategoryService.findAll();

        return new ResponseEntity(page, HttpStatus.OK);
    }

    /**
     * {@code GET  /sub-categories/:id} : get the "id" SubCategory.
     *
     * @param id the id of the subCategory to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the subCategory, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/sub-categories/{id}")
    public ResponseEntity<SubCategory> getSubCategory(@PathVariable UUID id) {
        log.debug("REST request to get SubCategory : {}", id);
        Optional<SubCategory> subCategory = subCategoryService.findOne(id);
        return ResponseUtil.wrapOrNotFound(subCategory);
    }

    @GetMapping("/subCategories/{id}")
    public ResponseEntity<List<SubCategory>> getAllSubCategoriesByCategories(@PathVariable UUID id) {
        log.debug("REST request to get a page of SubCategories");
        List<SubCategory> page = (List<SubCategory>) subCategoryService.findAllSubCa(id);

        return new ResponseEntity(page, HttpStatus.OK);
    }

    /**
     * {@code DELETE  /sub-categories/:id} : delete the "id" subCategory.
     *
     * @param id the id of the subCategory to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/sub-categories/{id}")
    public ResponseEntity<Void> deleteSubCategory(@PathVariable UUID id) {
        log.debug("REST request to delete SubCategory : {}", id);
        subCategoryService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }

}
