package com.swapiffy.swapiffybe.dao.cart;

import com.swapiffy.swapiffybe.dao.BaseDao;
import com.swapiffy.swapiffybe.entity.Card;
import com.swapiffy.swapiffybe.entity.CardProduct;
import com.swapiffy.swapiffybe.entity.User;
import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public class CartDaoImpl extends BaseDao implements ICartDao {

    @Override
    public Card getCard(Long id) {
        EntityManager em = null;
        Card card = null;
        try {
            em = openConnection();
            try {
                card = (Card) em.createNamedQuery("Card.findById").setParameter("id", id).getSingleResult();
            } catch (NoResultException nre) {
                System.err.println(nre.toString());
            }

        } catch (Exception ex) {
            System.err.println(ex.toString());
        } finally {
            closeConnection(em);
        }
        return card;
    }

    @Override
    public Card updateCard(Long id) {
        EntityManager em = openTransactionalConnection();
        Card card=null;
        try {
            if (em != null) {
                // Silme işlemini gerçekleştir
                CardProduct cardProduct = em.find(CardProduct.class, id);
                if (cardProduct != null) {
                   em.remove(cardProduct);
                    em.getTransaction().commit();
                    System.out.println("CardProduct deleted successfully.");
                } else {
                    System.out.println("CardProduct not found.");
                }
            } else {
                System.out.println("Failed to open transactional connection.");
            }
        } finally {
            if (em != null && em.isOpen()) {
                em.close();
            }
        }
        return null;
    }

    @Override
    public void addCard(Card card) {
        EntityManager em = null;
        try {
            em=openTransactionalConnection();
            em.merge(card);
            commitTransaction(em);

        } catch (Exception ex) {
            rollbackTransaction(em);
            throw ex;
        } finally {
            closeConnection(em);
        }

    }
    @Override
    public  void cardUpdate(CardProduct card){
        EntityManager em = null;
        try {
            em=openTransactionalConnection();
            em.createNamedQuery("cardproduct.update").setParameter("adet", card.getAdet()).setParameter("id", card.getId()).executeUpdate();
            commitTransaction(em);

        } catch (Exception ex) {
            rollbackTransaction(em);
            throw ex;
        } finally {
            closeConnection(em);
        }
    }

}
