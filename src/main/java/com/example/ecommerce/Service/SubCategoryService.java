package com.example.ecommerce.Service;

import com.example.ecommerce.Config.StorageProperties;
import com.example.ecommerce.entities.SubCategory;
import com.example.ecommerce.repositories.BlogRepository;
import com.example.ecommerce.repositories.SubCategoryRepository;
import org.apache.commons.io.FilenameUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@Transactional
public class SubCategoryService {

    private final Logger log = LoggerFactory.getLogger(SubCategoryService.class);

    private final MessageSource messageSource;
    private final StorageProperties storageProps;

    private final SubCategoryRepository subCategoryRepository;

    private final BlogRepository blogRepository;

    public SubCategoryService(MessageSource messageSource, StorageProperties storageProps, SubCategoryRepository subCategoryRepository, BlogRepository blogRepository) {
        this.messageSource = messageSource;
        this.storageProps = storageProps;
        this.subCategoryRepository = subCategoryRepository;
        this.blogRepository = blogRepository;
    }

    /**
     * Save a SubCategory.
     *
     * @param subCategory the entity to save.
     * @return the persisted entity.
     */
    public SubCategory save(MultipartFile files, SubCategory subCategory) throws IOException {
        log.debug("Request to save SubCategory : {}", subCategory);

        System.out.println("files "+ files);
        System.out.println("image "+ subCategory.getImage());
        if(files != null) {



            String path = storageProps.getPath();
            // do your stuff here
            System.out.println(path);


            String realPath = path.substring(7,path.length());
            System.out.println(realPath);
            String articleSubCategoriesFolder = realPath+"/topmaticImages/topmaticSubCategories/";
            Path rootArticleSubCategories = Paths.get(articleSubCategoriesFolder);
            String currentDate = new SimpleDateFormat("yyyyMMddHHmm").format(new Date());


            Files.copy(files.getInputStream(), rootArticleSubCategories.resolve(files.getOriginalFilename().replace(files.getOriginalFilename(),
                    FilenameUtils.getBaseName(files.getOriginalFilename()).concat(currentDate) + "." + FilenameUtils.getExtension(files.getOriginalFilename()))));


            /*get name of image with currentDate + extension*/
            String fileName = files.getOriginalFilename();
            int locationofExtension = fileName.lastIndexOf('.');
            String extension = fileName.substring(locationofExtension, fileName.length());
            String nameWithoutExtension = fileName.substring(0, locationofExtension);


            String newNameOfImage = nameWithoutExtension + currentDate + extension;

            String newPath = storageProps.getUrl()+"/topmaticImages/topmaticSubCategories/"+newNameOfImage;
            subCategory.setImage(newPath);
        }

        return subCategoryRepository.save(subCategory);
    }


    /**
     * Get all the subCategories.
     *
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public List<SubCategory> findAll() {
        log.debug("Request to get all SubCategories");
        return (List<SubCategory>) subCategoryRepository.findAll();
    }


    /**
     * Get one articleSubCategory by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<SubCategory> findOne(UUID id) {
        log.debug("Request to get SubCategory : {}", id);
        return subCategoryRepository.findById(id);
    }

    /**
     * Delete the articleSubCategory by id.
     *
     * @param id the id of the entity.
     */
    public void delete(UUID id) {
        log.debug("Request to delete subCategory : {}", id);
        subCategoryRepository.deleteById(id);

    }
}