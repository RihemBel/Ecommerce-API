package com.example.ecommerce.Service;


import com.example.ecommerce.Config.StorageProperties;
import com.example.ecommerce.entities.ImageItem;
import com.example.ecommerce.repositories.ImageRepository;
import org.apache.commons.io.FilenameUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Optional;
import java.util.UUID;

@Service
@Transactional
public class ImageService {

    private final Logger log = LoggerFactory.getLogger(ImageService.class);

    private final ImageRepository imageRepository;

    private final StorageProperties storageProps;

    public ImageService(ImageRepository imageRepository, StorageProperties storageProps) {
        this.imageRepository = imageRepository;
        this.storageProps = storageProps;
    }

    /**
     * Save an image.
     *
     * @param image the entity to save.
     * @return the persisted entity.
     */
//    public ImageItem save(ImageItem image) {
//        log.debug("Request to save ImageItem : {}", image);
//        return imageRepository.save(image);
//    }

    public ImageItem save(MultipartFile files, ImageItem image) throws IOException {


        log.debug("Request to save Image : {}", image);

        System.out.println("files "+ files);
        System.out.println("url "+ image.getName());
        if(files != null) {



            String path = storageProps.getPath();
            // do your stuff here
            System.out.println(path);


            String realPath = path.substring(7,path.length());
            System.out.println(realPath);
            String imageItemsFolder = realPath+"/topmaticImages/topmaticImage/";
            Path rootImageItems = Paths.get(imageItemsFolder);
            String currentDate = new SimpleDateFormat("yyyyMMddHHmm").format(new Date());


            Files.copy(files.getInputStream(), rootImageItems.resolve(files.getOriginalFilename().replace(files.getOriginalFilename(),
                    FilenameUtils.getBaseName(files.getOriginalFilename()).concat(currentDate) + "." + FilenameUtils.getExtension(files.getOriginalFilename()))));


            /*get name of image with currentDate + extension*/
            String fileName = files.getOriginalFilename();
            int locationofExtension = fileName.lastIndexOf('.');
            String extension = fileName.substring(locationofExtension, fileName.length());
            String nameWithoutExtension = fileName.substring(0, locationofExtension);


            String newNameOfImage = nameWithoutExtension + currentDate + extension;

            String newPath = storageProps.getUrl()+"/resources/topmaticImages/topmaticImage/"+newNameOfImage;
            image.setName(newPath);
        }

        return imageRepository.save(image);
    }


    /**
     * Delete the image by id.
     *
     * @param id the id of the entity.
     */
    public void delete(UUID id) throws IOException {
        log.debug("Request to delete ImageItem : {}", id);
        imageRepository.deleteById(id);
    }

    /**
     * Get one image by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<ImageItem> findOne(UUID id) {
        log.debug("Request to get ImageItem : {}", id);
        return imageRepository.findById(id);
    }


    /**
     * Get all the image.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Page<ImageItem> findAll(Pageable pageable) {
        log.debug("Request to get all ImageItems");
        return imageRepository.findAll(pageable);
    }

}
