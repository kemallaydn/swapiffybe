package com.swapiffy.swapiffybe.dao.Advertisement;

public class AdvertisementDaoFactory {

    public static IAdvertisementDao getAdvertisementDao() {
        return new AdvertisementDaoImpl();
    }

}
