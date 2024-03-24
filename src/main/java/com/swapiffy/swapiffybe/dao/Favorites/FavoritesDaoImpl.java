package com.swapiffy.swapiffybe.dao.Favorites;

import com.swapiffy.swapiffybe.dao.BaseDao;
import com.swapiffy.swapiffybe.entity.Card;
import com.swapiffy.swapiffybe.entity.Favorites;
import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.Query;

import java.util.List;
import java.util.UUID;

public class FavoritesDaoImpl extends BaseDao implements IFavoritesDao {
    @Override
    public void initialize() {

    }

    @Override
    public void AddToFavorites(Favorites favorites) {
        EntityManager em = null;

        try {
            em=openTransactionalConnection();
            em.persist(favorites);
            commitTransaction(em);
        } catch (Exception ex) {
            rollbackTransaction(em);
            throw ex;
        } finally {
            closeConnection(em);
        }

    }

    public void RemoveFromFavorites(String userId, String productId) {
        EntityManager em = null;
        System.out.println("RemoveFromFavorites"+userId+productId);
        try {
            em = openTransactionalConnection();
           Query query= em.createNamedQuery("Favorites.RemoveFromFavorites").setParameter("userId", userId).setParameter("productId", productId);
           query.executeUpdate();
           commitTransaction(em);
        } catch (Exception ex) {
            rollbackTransaction(em);
            throw ex;
        } finally {
            closeConnection(em);
        }
    }
    @Override
    public List<Favorites> GetFavorites(String userId) {
        EntityManager em = null;
        List<Favorites> favorites = null;
        try {
            em = openConnection();
            try {
                favorites = (List<Favorites>) em.createNamedQuery("Favorites.findByUserId").setParameter("userId", userId).getResultList();
            } catch (NoResultException nre) {
                System.err.println(nre.toString());
            }

        } catch (Exception ex) {
            System.err.println(ex.toString());
        } finally {
            closeConnection(em);
        }
        return  favorites;
    }

}
