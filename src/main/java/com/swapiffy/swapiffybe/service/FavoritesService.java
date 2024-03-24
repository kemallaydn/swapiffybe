package com.swapiffy.swapiffybe.service;

import com.swapiffy.swapiffybe.ServiceFacade;
import com.swapiffy.swapiffybe.dao.Advertisement.IAdvertisementDao;
import com.swapiffy.swapiffybe.dao.Favorites.IFavoritesDao;
import com.swapiffy.swapiffybe.entity.Advertisement;
import com.swapiffy.swapiffybe.entity.Favorites;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class FavoritesService {
    private IFavoritesDao favoritesDao = null;

    private IAdvertisementDao advertisementDao = null;

    public  FavoritesService() {
        favoritesDao = ServiceFacade.getInstance().getFavoritesDao();
        advertisementDao = ServiceFacade.getInstance().getAdvertisementDao();
    }
    public Advertisement addFavorite(Favorites favorites) {
        final Advertisement[] advertisement = {null};
      List<Favorites>  favoritesList = favoritesDao.GetFavorites(favorites.getUserId());
      favoritesList.stream().filter(fav -> fav.getProductId().equals(favorites.getProductId())).findFirst().ifPresentOrElse(
              fav -> {
                    favoritesDao.RemoveFromFavorites(favorites.getUserId(),favorites.getProductId());
                    System.out.println("Favorite removed");
              },
              () -> {
                  advertisement[0] = advertisementDao.getFindByAdvertId(Long.parseLong(favorites.getProductId()));
                  favoritesDao.AddToFavorites(favorites);
                  System.out.println("Favorite added");
              }
      );
      return advertisement[0];
    }

    public List<Advertisement> getFavorites(String userId) {
        List<Advertisement> advertisementList = new ArrayList<>();
        List<Favorites> favorites = favoritesDao.GetFavorites(userId);
        for (Favorites favorite : favorites) {
            Advertisement advertisement = advertisementDao.getFindByAdvertId(Long.parseLong(favorite.getProductId()));
            advertisementList.add(advertisement);
        }
        return advertisementList;
    }
    public void removeFromFavorites(String userId,String productId) {
        favoritesDao.RemoveFromFavorites(userId,productId);
    }
}
