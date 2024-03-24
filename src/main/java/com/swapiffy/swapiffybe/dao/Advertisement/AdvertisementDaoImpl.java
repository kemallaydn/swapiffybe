package com.swapiffy.swapiffybe.dao.Advertisement;

import com.swapiffy.swapiffybe.dao.BaseDao;
import com.swapiffy.swapiffybe.dao.cart.ICartDao;
import com.swapiffy.swapiffybe.dto.AdvertisementDto;
import com.swapiffy.swapiffybe.entity.Advertisement;
import com.swapiffy.swapiffybe.entity.Card;
import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;

import java.util.List;

public class AdvertisementDaoImpl extends BaseDao implements IAdvertisementDao {


    @Override
    public void initialize() throws Exception {

    }

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
    public Advertisement getFindByAdvertId(Long id) {
        EntityManager em = null;
        Advertisement advertisement = null;
        try {
            em = openConnection();
            try {
                advertisement = (Advertisement) em.createNamedQuery("Advertisement.findByAdvertId").setParameter("id", id).getSingleResult();
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
    public Advertisement saveAdvertisement(Advertisement advertisement) {
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
        return  advertisement;
    }
    @Override
    public List<Advertisement> getAllAdvertisement() {
        EntityManager em = null;
        List<Advertisement> advertisement = null;
        try {
            em = openConnection();
            try {
                advertisement = (List<Advertisement>) em.createNamedQuery("Advertisement.findAll").getResultList();
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
}
