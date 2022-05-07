package com.example.ecommerce.Storage;

import com.example.ecommerce.Config.StorageProperties;
import com.example.ecommerce.Security.SecurityUtils;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.util.FileSystemUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Stream;

@Service
public class FilesStorageServiceImpl  implements FilesStorageService{

    private final StorageProperties storageProps;

    String pathName = "compaktorImages";

    final private  Path root = Paths.get(pathName);

    public FilesStorageServiceImpl(StorageProperties storageProps) {
        this.storageProps = storageProps;
    }


    @Override
    public void init(String schemaName) throws IOException {

        String path = storageProps.getPath();
        // do your stuff here
        System.out.println(path);


        String realPath = path.substring(7,path.length());
        System.out.println(realPath);
        File file = new File(realPath+"/topmaticImages");
        File fileCompanyConfigs = new File(realPath+"/tomaticImages/companyConfigs");
        File fileItems = new File(realPath+"/topmaticImages/items");
//        File fileReglements = new File(realPath+"/topmaticImages/"+schemaName+"/reglements");
//        File fileTiers = new File(realPath+"/topmaticImages/"+schemaName+"/tiers");
//        File filePdfs = new File(realPath+"/topmaticImages/"+schemaName+"/pdfs");
        File fileCategories = new File(realPath+"/topmaticImages/articleCategories");
//        File fileSubCategories = new File(realPath+"/topmaticImages/articleSubCategories");
        File fileProducts = new File(realPath+"/topmaticImages/products");
        if (!file.exists()) {
            file.mkdir();
        }else {
            System.out.println("file topmaticImage exist");
        }

    }

    @Override
    public void save(MultipartFile file  ) {
        try {
            //  String currentDate = new SimpleDateFormat("yyyyMMddHHmm").format(new Date());
            //    Files.copy(file.getInputStream(), this.root.resolve(file.getOriginalFilename().replace(file.getOriginalFilename(), FilenameUtils.getBaseName(file.getOriginalFilename()).concat(currentDate) + "." + FilenameUtils.getExtension(file.getOriginalFilename())).toLowerCase()));

            Files.copy(file.getInputStream(),this.root.resolve(file.getOriginalFilename()));

        } catch (Exception e) {
            throw new RuntimeException("Could not store the file. Error: " + e.getMessage());
        }
    }


    @Override
    public Resource load(String filename) {
        try {
            Path file = root.resolve(filename);
            Resource resource = new UrlResource(file.toUri());
            //System.out.println(file);


            /**//*
            File filee = new File(String.valueOf(file));
            filee.delete();

            *//**/

            if (resource.exists() || resource.isReadable()) {

                return resource;
            } else {
                throw new RuntimeException("Could not read the file!");
            }
        } catch (MalformedURLException e) {
            throw new RuntimeException("Error: " + e.getMessage());
        }
    }



    @Override
    public void deleteImage(String filename) {
        Path file = root.resolve(filename);
        System.out.println(file);

        File filee = new File(String.valueOf(file));
        filee.delete();

    }

    @Override
    public void deleteAll() {
        FileSystemUtils.deleteRecursively(root.toFile());
    }


}
