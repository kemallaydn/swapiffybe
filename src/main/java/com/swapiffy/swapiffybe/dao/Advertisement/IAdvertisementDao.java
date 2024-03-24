package com.swapiffy.swapiffybe.dao.Advertisement;

import com.swapiffy.swapiffybe.dto.AdvertisementDto;
import com.swapiffy.swapiffybe.entity.Advertisement;
import com.swapiffy.swapiffybe.entity.Card;
import com.swapiffy.swapiffybe.entity.CardProduct;

import java.util.List;

public interface IAdvertisementDao {
    public  abstract void initialize() throws Exception;
    public abstract List<Advertisement> getAdvertisement(Long id);
    public abstract Advertisement getFindByAdvertId(Long id);
    public abstract Advertisement saveAdvertisement(Advertisement advertisement);
    public abstract List<Advertisement> getAllAdvertisement();
}
