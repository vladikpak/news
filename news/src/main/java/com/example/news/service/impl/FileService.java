package com.example.news.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

@Service
public class FileService {
    private static final String PRODUCT_IMG_DIR = "uploads/products/";

    public String saveImage(MultipartFile file) {
        try {
            if (file == null || file.isEmpty()) {
                return null;
            }

            String basePath = System.getProperty("user.dir");
            File uploadDir = new File(basePath, PRODUCT_IMG_DIR);

            if (!uploadDir.exists()) {
                uploadDir.mkdirs();
            }

            String fileName = System.currentTimeMillis() + "_" + file.getOriginalFilename().replaceAll(" ", "_");
            File dest = new File(uploadDir, fileName);

            file.transferTo(dest);

            return "/" + PRODUCT_IMG_DIR + fileName;

        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException("Ошибка сохранения файла: " + e.getMessage());
        }
    }
}
