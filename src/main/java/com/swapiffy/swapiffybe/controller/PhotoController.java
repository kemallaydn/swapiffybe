package com.swapiffy.swapiffybe.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.*;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Base64;
import java.util.UUID;

@RestController
@RequestMapping("/photos")
public class PhotoController {

    private static final String UPLOAD_FOLDER = "/Users/furkanaydin/swapiffybe/images"; // Yükleme klasörünü belirtin
    @Value("${upload-dir}")
    private String uploadDir;
    @Value("${github.access.token}") // GitHub API erişim anahtarını application.properties dosyasından alıyoruz
    private String githubAccessToken;
    @PostMapping("/upload")
    public ResponseEntity<String> uploadImage(@RequestParam("image") MultipartFile image) {
        if (image.isEmpty()) {
            return ResponseEntity.badRequest().body("Resim dosyası boş olamaz");
        }

        try {
            // Resmi geçici bir dosyaya kaydet
            byte[] bytes = image.getBytes();
            Path path = Paths.get(UPLOAD_FOLDER, image.getOriginalFilename());
            System.out.println("path: " + image.getOriginalFilename());
            Files.write(path, bytes);

            // GitHub'a yükle
            String githubUploadUrl = "https://api.github.com/repos/{owner}/{repo}/contents/{path}";
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            headers.set("Authorization", "Bearer " + githubAccessToken);
            String content = Base64.getEncoder().encodeToString(bytes);
            String url = githubUploadUrl
                    .replace("{owner}", "kemallaydn")
                    .replace("{repo}", "images")
                    .replace("{path}", image.getOriginalFilename());
            String requestBody = String.format("{\"message\": \"Add %s\", \"content\": \"%s\"}", image.getOriginalFilename(), content);
            RequestEntity<String> request = RequestEntity
                    .put(new URI(url))
                    .headers(headers)
                    .body(requestBody);
            ResponseEntity<String> response = new RestTemplate().exchange(request, String.class);

            // Resmin URL'sini oluştur ve geri dön
            String imageUrl = "https://raw.githubusercontent.com/kemallaydn/images/main/" + image.getOriginalFilename();
            return ResponseEntity.ok(imageUrl);
        } catch (IOException | URISyntaxException e) {
            e.printStackTrace();
            return ResponseEntity.status(500).body("Resim yüklenirken bir hata oluştu");
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

