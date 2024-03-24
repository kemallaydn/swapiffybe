package com.swapiffy.swapiffybe.controller;

import com.swapiffy.swapiffybe.dao.Advertisement.AdvertisementDaoImpl;
import com.swapiffy.swapiffybe.dao.Advertisement.IAdvertisementDao;
import com.swapiffy.swapiffybe.entity.Advertisement;
import com.swapiffy.swapiffybe.entity.Favorites;
import com.swapiffy.swapiffybe.entity.Product;
import com.swapiffy.swapiffybe.service.FavoritesService;
import com.swapiffy.swapiffybe.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/v1/api/favorites")
public class FavoritesController {

    private FavoritesService favoritesService = null;

    private ProductService productService = null;


    public FavoritesController(FavoritesService favoritesService, ProductService productService) {

        this.favoritesService = favoritesService;
        this.productService=productService;
    }


    @PostMapping("/add")
    public Advertisement addFavorite(@RequestBody Favorites favorites) {
        return favoritesService.addFavorite(favorites);

    }

    @GetMapping("/getFavorites")
    public List<Advertisement> getFavorites(@RequestParam String userId) {
        System.out.println("getFavorites"+userId);

        return favoritesService.getFavorites(userId);

    }
    @DeleteMapping("/removeFromFavorites")
    public void removeFromFavorites(@RequestParam String userId,@RequestParam String productId) {
        favoritesService.removeFromFavorites(userId,productId);

    }

}
