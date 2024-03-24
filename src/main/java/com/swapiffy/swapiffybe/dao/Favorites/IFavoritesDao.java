package com.swapiffy.swapiffybe.dao.Favorites;

import com.swapiffy.swapiffybe.dao.IBaseDao;
import com.swapiffy.swapiffybe.entity.Favorites;

import java.util.List;
import java.util.UUID;

public interface IFavoritesDao extends IBaseDao {
    public abstract void initialize();
    public abstract void AddToFavorites(Favorites favorites);
    public abstract void RemoveFromFavorites(String userId, String productId);
    public abstract List<Favorites> GetFavorites(String userId);

}
