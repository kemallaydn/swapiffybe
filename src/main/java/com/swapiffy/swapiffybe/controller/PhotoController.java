package com.swapiffy.swapiffybe.controller;

import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

@RestController
@RequestMapping("/photos")
public class PhotoController {

    private static final String UPLOAD_FOLDER = "/Users/furkanaydin/Desktop/images"; // Yükleme klasörünü belirtin

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

