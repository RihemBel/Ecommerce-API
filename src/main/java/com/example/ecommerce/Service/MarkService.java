package com.example.ecommerce.Service;

import com.example.ecommerce.Config.StorageProperties;
import com.example.ecommerce.entities.Mark;
import com.example.ecommerce.repositories.BlogRepository;
import com.example.ecommerce.repositories.MarkRepository;
import com.google.gson.JsonElement;
import org.apache.commons.io.FilenameUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.MessageSource;
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
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.ExecutionException;

@Service
@Transactional
public class MarkService {

    private final Logger log = LoggerFactory.getLogger(MarkService.class);


    private final MarkRepository markRepository;

    private final MessageSource messageSource;

    private final StorageProperties storageProps;

    private final BlogRepository blogRepository;

    public MarkService(MarkRepository markRepository, MessageSource messageSource, StorageProperties storageProps, BlogRepository blogRepository) {
        this.markRepository = markRepository;
        this.messageSource = messageSource;
        this.storageProps = storageProps;
        this.blogRepository = blogRepository;
    }




    /**
         * Save a mark.
         *
         * @return the persisted entity.
         */
        public Mark save(MultipartFile files, Mark mark) throws IOException {
            log.debug("Request to save Mark : {}", mark);

            System.out.println("files "+ files);
            System.out.println("image "+ mark.getImage());
            if(files != null) {



                String path = storageProps.getPath();
                // do your stuff here
                System.out.println(path);


                String realPath = path.substring(7,path.length());
                System.out.println(realPath);
                String productFolder = realPath+"/topmaticImages/topmaticMarks/";
                Path rootProduct = Paths.get(productFolder);
                String currentDate = new SimpleDateFormat("yyyyMMddHHmm").format(new Date());


                Files.copy(files.getInputStream(), rootProduct.resolve(files.getOriginalFilename().replace(files.getOriginalFilename(),
                        FilenameUtils.getBaseName(files.getOriginalFilename()).concat(currentDate) + "." + FilenameUtils.getExtension(files.getOriginalFilename()))));


                /*get name of image with currentDate + extension*/
                String fileName = files.getOriginalFilename();
                int locationofExtension = fileName.lastIndexOf('.');
                String extension = fileName.substring(locationofExtension, fileName.length());
                String nameWithoutExtension = fileName.substring(0, locationofExtension);


                String newNameOfImage = nameWithoutExtension + currentDate + extension;

                String newPath = storageProps.getUrl()+"/topmaticImages/topmaticMarks/"+newNameOfImage;
                mark.setImage(newPath);
            }

            return markRepository.save(mark);
        }

    /**
     * Get all the marks.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Page<Mark> findAll(Pageable pageable) {
        log.debug("Request to get all Marks");
        return markRepository.findAll(pageable);
    }

    /**
     * Get all the marks.
     *
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public List<Mark> findAll() {
        log.debug("Request to get all Marks");
        return markRepository.findAll();
    }

    /**
     * Get one mark by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<Mark> findOne(UUID id) {
        log.debug("Request to get Mark : {}", id);
        return markRepository.findById(id);
    }

    /**
     * Delete the mark by id.
     *
     * @param id the id of the entity.
     */
    public void delete(UUID id) throws ExecutionException, InterruptedException {
        log.debug("Request to delete Mark : {}", id);
        markRepository.deleteById(id);

    }

    //verify if name of mark exist

    public Boolean findByName(String name){

        Mark mark = markRepository.findByName(name);
        if(mark == null){
            return false;
        }

        else
            return true;
    }

}
