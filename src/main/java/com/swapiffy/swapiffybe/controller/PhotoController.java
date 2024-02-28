package com.swapiffy.swapiffybe.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.io.IOException;
import java.net.URI;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

@RestController
@RequestMapping("/photos")
public class PhotoController {

    private static final String UPLOAD_FOLDER = "/Users/furkanaydin/swapiffybe/images"; // Yükleme klasörünü belirtin
    @Value("${upload-dir}")
    private String uploadDir;

    @PostMapping("/upload")
    public ResponseEntity<String> uploadFile(@RequestPart("file") MultipartFile file) {
        try {
            // Dosyanın adını benzersiz bir şekilde oluşturun
            String fileName = StringUtils.cleanPath(file.getOriginalFilename());
            String fileExtension = StringUtils.getFilenameExtension(fileName);
            String generatedFileName = UUID.randomUUID().toString() + "." + fileExtension;

            // Dosyayı kaydetmek için dosya yolu oluşturun
            Path filePath = Paths.get(uploadDir + generatedFileName);

            // Dosyayı belirtilen klasöre kaydet
            Files.copy(file.getInputStream(), filePath);


            // Başarıyla yüklendi yanıtı döndürünüz
            return ResponseEntity.ok().body("Dosya başarıyla yüklendi: " + generatedFileName);
        } catch (IOException ex) {
            return ResponseEntity.badRequest().body("Dosya yükleme hatası: " + ex.getMessage());
        }
    }
    @GetMapping("/get")
    public ResponseEntity<Resource> getPhoto(@RequestParam String photoName) {
        try {
            Path photoPath = Paths.get(UPLOAD_FOLDER).resolve(photoName);
            Resource photo = new UrlResource(photoPath.toUri());

            return ResponseEntity.ok()
                    .header(HttpHeaders.CONTENT_TYPE, "image/jpeg") // İçerik türünü belirtin
                    .body(photo);
        } catch (IOException e) {
            return ResponseEntity.notFound().build();
        }
    }
}

