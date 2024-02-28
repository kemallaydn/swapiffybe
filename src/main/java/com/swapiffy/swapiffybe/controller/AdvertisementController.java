package com.swapiffy.swapiffybe.controller;

import com.swapiffy.swapiffybe.entity.Advertisement;
import com.swapiffy.swapiffybe.service.AdvertisementService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/v1/advertisements")
public class AdvertisementController {
    private final AdvertisementService advertisementService;
    private static final Logger logger = Logger.getLogger(AdvertisementController.class);

    @Autowired
    public AdvertisementController(AdvertisementService advertisementService) {
        this.advertisementService = advertisementService;
    }

    @PostMapping("/add")
    public void createAdvertisement(@RequestBody Advertisement advertisement) {
       advertisementService.saveAdvertisement(advertisement);
       System.out.println("Advertisement saved");
    }
    @GetMapping("/getAdvertisement")
    public List<Advertisement> getAdvertisement(@RequestParam Long id) {
        logger.info("Advertisement get");
        List<Advertisement> savedAdvertisement = advertisementService.getAdvertisement(id);
        System.out.println("Advertisement get");
        return savedAdvertisement;
    }
}
