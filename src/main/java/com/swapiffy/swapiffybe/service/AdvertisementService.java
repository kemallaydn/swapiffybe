package com.swapiffy.swapiffybe.service;

import com.swapiffy.swapiffybe.Repository.AdvertisementRepository;
import com.swapiffy.swapiffybe.dao.Advertisement.AdvertisementDaoImpl;
import com.swapiffy.swapiffybe.dao.Advertisement.IAdvertisementDao;
import com.swapiffy.swapiffybe.dao.cart.CartDaoImpl;
import com.swapiffy.swapiffybe.entity.Advertisement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdvertisementService {
    private  final IAdvertisementDao advertisementDao;
    private final AdvertisementRepository advertisementRepository;

    @Autowired
    public AdvertisementService(AdvertisementRepository advertisementRepository) {
        this.advertisementRepository = advertisementRepository;
        this.advertisementDao= new AdvertisementDaoImpl();
    }

    public void saveAdvertisement(Advertisement advertisement) {
        advertisementDao.saveAdvertisement(advertisement);
    }
    public List<Advertisement> getAdvertisement(Long id) {
        return advertisementDao.getAdvertisement(id);
    }
}
