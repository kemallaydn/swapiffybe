package com.swapiffy.swapiffybe.dao.Advertisement;

import com.swapiffy.swapiffybe.dao.BaseDao;
import com.swapiffy.swapiffybe.dao.cart.ICartDao;
import com.swapiffy.swapiffybe.entity.Advertisement;
import com.swapiffy.swapiffybe.entity.Card;
import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;

import java.util.List;

public class AdvertisementDaoImpl extends BaseDao implements IAdvertisementDao {
    @Override
    public List<Advertisement> getAdvertisement(Long id) {
        EntityManager em = null;
        List<Advertisement> advertisement = null;
        try {
            em = openConnection();
            try {
                advertisement = (List<Advertisement>) em.createNamedQuery("Advertisement.findById").setParameter("id", id).getResultList();
            } catch (NoResultException nre) {
                System.err.println(nre.toString());
            }

        } catch (Exception ex) {
            System.err.println(ex.toString());
        } finally {
            closeConnection(em);
        }
        return advertisement;
    }

    @Override
    public void saveAdvertisement(Advertisement advertisement) {
        EntityManager em = null;
        try {
            em = openTransactionalConnection();
            em.persist(advertisement);
            commitTransaction(em);

        } catch (Exception ex) {
            System.err.println(ex.toString());
        } finally {
            closeConnection(em);
        }
    }
}
