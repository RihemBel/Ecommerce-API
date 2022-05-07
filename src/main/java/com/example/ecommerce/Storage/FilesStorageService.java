package com.example.ecommerce.Storage;

import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Path;
import java.util.stream.Stream;

public interface FilesStorageService {

    void init(String schemaName) throws IOException;

    public void save(MultipartFile file);

    public Resource load(String filename);

    public void deleteAll();

    public void deleteImage(String filename);
}
