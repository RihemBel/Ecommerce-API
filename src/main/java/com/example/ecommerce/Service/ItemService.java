package com.example.ecommerce.Service;

import com.example.ecommerce.Config.StorageProperties;
import com.example.ecommerce.entities.*;
import com.example.ecommerce.repositories.*;
import org.apache.commons.io.FilenameUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.*;

@Service
@Transactional
public class ItemService {


    private final Logger log = LoggerFactory.getLogger(ItemService.class);

    private final ItemRepository itemRepository;

    private final PivvRepository pivvRepository;

    private final StorageProperties storageProps;

    private final ProductService productService;

    private final BlogRepository blogRepository;

    private final ImageService imageService;

    private final ImageRepository imageRepository;

    private final ProductRepository productRepository;

    public ItemService(ItemRepository itemRepository, StorageProperties storageProps, PivvRepository pivvRepository, ProductService productService, BlogRepository blogRepository, ImageService imageService, ImageRepository imageRepository, ProductRepository productRepository) {
        this.itemRepository = itemRepository;
        this.storageProps = storageProps;
        this.pivvRepository = pivvRepository;
        this.productService = productService;
        this.blogRepository = blogRepository;
        this.imageService = imageService;
        this.imageRepository = imageRepository;
        this.productRepository = productRepository;
    }

    /**
     * Save a Item.
     *
     * @param item the entity to save.
     * @return the persisted entity.
     */
    public Item save( Item item) throws IOException {
        log.debug("Request to save Item : {}", item);

        return itemRepository.save(item);
    }


//    /**
//     * Save an item.
//     *
//     * @param item the entity to save.
//     * @return the persisted entity.
//     */
//    public Item save(Item item) throws IOException {
//
//            String path = storageProps.getPath();
////            System.out.println(path);
//            String realPath = path.substring(7,path.length());
//            System.out.println(item);
//
//        Set<ImageItem> imageItemList = item.getImage();
//        if(imageItemList.size() != 0) {
//            for (ImageItem imageItem : imageItemList) {
//                if (imageItem.getId() == null) {
//                    Random rand = new Random();
//                    int rand_int1 = rand.nextInt(1000);
//                    String currentDate = new SimpleDateFormat("yyyyMMddHHmm").format(new Date());
//                    String fileName = imageItem.getName();
//                    int locationofExtension = fileName.lastIndexOf('.');
//                    String extension = fileName.substring(locationofExtension, fileName.length());
//                    String nameWithoutExtension = fileName.substring(0, locationofExtension);
//                    String newNameOfImage = nameWithoutExtension + currentDate + rand_int1 + extension;
//                    //   System.out.println(newNameOfImage+"   name of Image");
//                    String pathImageItems = storageProps.getUrl() + "/resources/topmaticImages/topmaticItems/" + newNameOfImage;
//                    imageItem.setName(pathImageItems);
//                    imageItem.setItem(item);
//                    imageRepository.save(imageItem);
//
//                    String itemsFolder = realPath + "/topmaticImages/topmaticItems/";
//
//
//                    Path rootItems = Paths.get(itemsFolder);
//                    byte[] scanBytes = Base64.getDecoder().decode(imageItem.getName());
//                    Files.write(rootItems, scanBytes);
//                }
//            }
//        }
//        return itemRepository.save(item);
//    }


    /**
     * Get one item by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<Item> findOne(UUID id) {
        log.debug("Request to get Item : {}", id);
        return itemRepository.findById(id);
    }



    /**
     * Delete the item by id.
     *
     * @param id the id of the entity.
     */
    public void delete(UUID id) throws IOException {
        log.debug("Request to delete category : {}", id);
//        categoryRepository.deleteById(id);


        Item item = itemRepository.findById(id).get();
        item.setIsDeleted(true);



        itemRepository.save(item);
    }

    /**
     * Get all the items.
     *
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public List<Item> findAll() {
        log.debug("Request to get all items");
        return itemRepository.findAll();

    }
    //used to delete item before update product

//    public void deleteToUpdateProduct(UUID id) throws IOException {
//        log.debug("Request to delete Item : {}", id);
//        Product product = pivvRepository.getProduct_ofItem(id);
//        Optional<Item> item = this.findOne(id);
//        Set<ImageItem>imageItemSet = item.get().getImage();
//        if(imageItemSet.size() != 0) {
//            for (ImageItem imageItem : imageItemSet) {
//                imageService.delete(imageItem.getId());
//
//            }
//        }
//        itemRepository.deleteById(id);
//
//    }

}
