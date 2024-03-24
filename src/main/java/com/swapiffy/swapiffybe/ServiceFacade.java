package com.swapiffy.swapiffybe;

import com.swapiffy.swapiffybe.dao.Advertisement.AdvertisementDaoFactory;
import com.swapiffy.swapiffybe.dao.Advertisement.IAdvertisementDao;
import com.swapiffy.swapiffybe.dao.Favorites.FavoritesDaoFactory;
import com.swapiffy.swapiffybe.dao.Favorites.IFavoritesDao;
import com.swapiffy.swapiffybe.dao.user.IUserDao;
import com.swapiffy.swapiffybe.dao.user.UserDaoFactory;

import java.util.logging.LogManager;
import java.util.logging.Logger;

public class ServiceFacade {
    private static ServiceFacade instance = null;
    private IUserDao userDao = null;

    private IAdvertisementDao advertisementDao = null;

    private IFavoritesDao favoritesDao = null;
    public static ServiceFacade getInstance() {
        if (instance == null) {
            instance = new ServiceFacade();
        }
        return instance;
    }
    ServiceFacade () {
        try {
            initializeDao();
        } catch (Exception ex) {
            Logger.getLogger(ServiceFacade.class.getName()).severe(ex.toString());
        }
    }
    private void initializeDao() throws Exception {

        userDao = new UserDaoFactory().getUserDao();
        userDao.initialize();

        favoritesDao= new FavoritesDaoFactory().getFavoritesDao();
        favoritesDao.initialize();

        advertisementDao = new AdvertisementDaoFactory().getAdvertisementDao();
        advertisementDao.initialize();

    }
    public IUserDao getUserDao() {
        return userDao;
    }

    public IFavoritesDao getFavoritesDao() {
        return favoritesDao;
    }

    public IAdvertisementDao getAdvertisementDao() {
        return advertisementDao;
    }
}
