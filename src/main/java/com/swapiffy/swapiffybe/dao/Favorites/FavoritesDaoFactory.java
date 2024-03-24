package com.swapiffy.swapiffybe.dao.Favorites;

import com.swapiffy.swapiffybe.dao.BaseDao;
import jakarta.persistence.EntityManager;

public class FavoritesDaoFactory {
    public IFavoritesDao getFavoritesDao() throws Exception {
        return new FavoritesDaoImpl();
    }

}
