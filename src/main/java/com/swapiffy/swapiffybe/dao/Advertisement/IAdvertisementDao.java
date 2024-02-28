package com.swapiffy.swapiffybe.dao.Advertisement;

import com.swapiffy.swapiffybe.entity.Advertisement;
import com.swapiffy.swapiffybe.entity.Card;
import com.swapiffy.swapiffybe.entity.CardProduct;

import java.util.List;

public interface IAdvertisementDao {
    public abstract List<Advertisement> getAdvertisement(Long id);
    public abstract void saveAdvertisement(Advertisement  advertisement);
}
