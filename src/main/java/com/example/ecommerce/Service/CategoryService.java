package com.example.ecommerce.Service;

import com.example.ecommerce.Config.StorageProperties;
import com.example.ecommerce.entities.Category;
import com.example.ecommerce.entities.Product;
import com.example.ecommerce.entities.SubCategory;
import com.example.ecommerce.repositories.CategoryRepository;
import com.example.ecommerce.repositories.SubCategoryRepository;
import org.apache.commons.io.FilenameUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.MessageSource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import javax.sql.DataSource;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.*;


@Service
@Transactional
public class CategoryService {
    private final Logger log = LoggerFactory.getLogger(CategoryService.class);

    private final CategoryRepository categoryRepository;

    private final SubCategoryRepository subCategoryRepository;

//    private final SubCategory subCategory;

    private final MessageSource messageSource;

    private final StorageProperties storageProps;

    private final DataSource dataSource;

    private final NotificationService notificationService;

    public CategoryService(CategoryRepository categoryRepository, SubCategoryRepository subCategoryRepository, MessageSource messageSource, StorageProperties storageProps, DataSource dataSource, NotificationService notificationService) {
        this.categoryRepository = categoryRepository;
        this.subCategoryRepository = subCategoryRepository;
        this.messageSource = messageSource;
        this.storageProps = storageProps;
        this.dataSource = dataSource;
        this.notificationService = notificationService;
    }




    /**
     * Save a Category.
     *
     * @param category the entity to save.
     * @return the persisted entity.
     */
    public Category save(MultipartFile files, Category category) throws IOException {
        log.debug("Request to save Category : {}", category);

        System.out.println("files "+ files);
        System.out.println("image "+ category.getImage());
        if(files != null) {



            String path = storageProps.getPath();
            // do your stuff here
            System.out.println(path);


            String realPath = path.substring(7,path.length());
            System.out.println(realPath);
            String articleCategoriesFolder = realPath+"/topmaticImages/topmaticCategories/";
            Path rootArticleCategories = Paths.get(articleCategoriesFolder);
            String currentDate = new SimpleDateFormat("yyyyMMddHHmm").format(new Date());


            Files.copy(files.getInputStream(), rootArticleCategories.resolve(files.getOriginalFilename().replace(files.getOriginalFilename(),
                    FilenameUtils.getBaseName(files.getOriginalFilename()).concat(currentDate) + "." + FilenameUtils.getExtension(files.getOriginalFilename()))));


            /*get name of image with currentDate + extension*/
            String fileName = files.getOriginalFilename();
            int locationofExtension = fileName.lastIndexOf('.');
            String extension = fileName.substring(locationofExtension, fileName.length());
            String nameWithoutExtension = fileName.substring(0, locationofExtension);


            String newNameOfImage = nameWithoutExtension + currentDate + extension;

            String newPath = storageProps.getUrl()+"/resources/topmaticImages/topmaticCategories/"+newNameOfImage;
            category.setImage(newPath);
        }

        return categoryRepository.save(category);
    }

    /**
     * Get all the articleCategories.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Page<Category> findAll(Pageable pageable) {
        log.debug("Request to get all ProductCategories");
        return categoryRepository.findAllByOrderByName(pageable);

    }

    /**
     * Get all the articleCategories.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public List<Category> findAll() {
        log.debug("Request to get all ProductCategories");


            return categoryRepository.findAllCa();
    }

    /**
     * Get one productCategory by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<Category> findOne(UUID id) {
        log.debug("Request to get ProductCategory : {}", id);
        return categoryRepository.findById(id);
    }

    //verify if name of articleCategory exist

    public Boolean findByName(String name){

        Category productCategory = categoryRepository.findByName(name);
        if(productCategory == null){
            return false;
        }
        else
            return true;
    }


//
//    public Boolean findByName(UUID categoryId, String name) {
//
//        Optional<Category> category = this.findOne(categoryId);
//
//        Set<SubCategory> subCategorySet = category.get().getSubCategory();
//
//        Boolean result = false;
//
//        if(subCategorySet != null && subCategorySet.size() != 0){
//            for (SubCategory subCategory : subCategorySet) {
//
//                if (subCategory.getName().equals(name)) {
//
//                    result = true;
//                }
//            }
//        }
//
//        return result;
//    }
    /**
     * Delete the category by id.
     *
     * @param id the id of the entity.
     */
    public void delete(UUID id) throws IOException {
        log.debug("Request to delete category : {}", id);
//        categoryRepository.deleteById(id);


        Category category = categoryRepository.findById(id).get();
        category.setIsDeleted(true);

        for(SubCategory subCategory : category.getSubCategory()) {
            subCategory.setIsDeleted(true);
        }
//        for(Product product : category.getProduct()) {
//            product.setDeleted(true);
//        }

        categoryRepository.save(category);
    }


}