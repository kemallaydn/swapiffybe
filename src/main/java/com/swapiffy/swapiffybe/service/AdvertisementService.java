package com.swapiffy.swapiffybe.service;

import com.swapiffy.swapiffybe.Repository.AdvertisementRepository;
import com.swapiffy.swapiffybe.dao.Advertisement.AdvertisementDaoImpl;
import com.swapiffy.swapiffybe.dao.Advertisement.IAdvertisementDao;
import com.swapiffy.swapiffybe.dao.cart.CartDaoImpl;
import com.swapiffy.swapiffybe.dto.AdvertisementDto;
import com.swapiffy.swapiffybe.entity.Advertisement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Base64;
import java.util.List;
import java.util.UUID;

@Service
public class AdvertisementService {
    private  final IAdvertisementDao advertisementDao;
    private final AdvertisementRepository advertisementRepository;
    private static final String UPLOAD_FOLDER = "/Users/furkanaydin/swapiffybe/images"; // Yükleme klasörünü belirtin
    @Value("${upload-dir}")
    private String uploadDir;
    @Value("${github.access.token}") // GitHub API erişim anahtarını application.properties dosyasından alıyoruz
    private String githubAccessToken;
    @Autowired
    public AdvertisementService(AdvertisementRepository advertisementRepository) {
        this.advertisementRepository = advertisementRepository;
        this.advertisementDao= new AdvertisementDaoImpl();
    }

    public Advertisement saveAdvertisement(Advertisement advertisement) {
        advertisement.setImageurl((uploadImage(advertisement.getImageurl())));
      return  advertisementDao.saveAdvertisement(advertisement);
    }
    public String uploadImage(String imageBase64) {
        String downloadUrl = null;
        try {
            byte[] decodedBytes = Base64.getDecoder().decode(imageBase64);

            // Base64 veriyi dosyaya yaz
            String randomFileName = UUID.randomUUID().toString() + ".jpg"; // Rasgele dosya adı
            Path path = Paths.get(UPLOAD_FOLDER, randomFileName); // Dosya yolu oluştur
            Files.write(path, decodedBytes);

            // GitHub'a yükle
            String githubUploadUrl = "https://api.github.com/repos/{owner}/{repo}/contents/{path}";
            String url = githubUploadUrl
                    .replace("{owner}", "kemallaydn")
                    .replace("{repo}", "images")
                    .replace("{path}", randomFileName); // GitHub'a yüklenen dosyanın adı ve uzantısı önemlidir

            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            headers.set("Authorization", "Bearer " + githubAccessToken);

            // Base64 veriyi dosya içeriği olarak al
            String content = Base64.getEncoder().encodeToString(decodedBytes);

            // GitHub'a yükleme isteğini oluştur
            String requestBody = String.format("{\"message\": \"Add %s\", \"content\": \"%s\", \"encoding\": \"base64\"}", randomFileName, content);
            RequestEntity<String> request = RequestEntity
                    .put(new URI(url))
                    .headers(headers)
                    .body(requestBody);
            // GitHub'a yükleme isteğini gönder ve cevabı al
            ResponseEntity<String> response = new RestTemplate().exchange(request, String.class);
            downloadUrl= "https://raw.githubusercontent.com/kemallaydn/images/main/" + randomFileName;
            return downloadUrl;
        } catch (IOException | URISyntaxException e) {
            e.printStackTrace();
        }
        return downloadUrl;
    }

    public List<Advertisement> getAdvertisement(Long id) {
        return advertisementDao.getAdvertisement(id);
    }
    public List<Advertisement> getAllAdvertisement() {
        return advertisementDao.getAllAdvertisement();
    }
}
