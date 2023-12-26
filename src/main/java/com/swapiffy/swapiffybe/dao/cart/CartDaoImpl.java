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
    public Card updateCard(int newStock, Long id,Long userId) {
        EntityManager em = null;
        Card card = null;
        try {
            em = openTransactionalConnection();
            try {

                // Kullanıcının sepetindeki belirli ürünün bilgisini al
                Query getCartItemQuery = em.createQuery("SELECT c FROM CardProduct c WHERE c.id = :userId AND c.id = :productId");
                getCartItemQuery.setParameter("userId", userId);
                getCartItemQuery.setParameter("productId", id);

                Card cartItem = (Card) getCartItemQuery.getSingleResult();

                // Ürün adetini güncelle
                if (cartItem != null) {
                    cartItem.getSepetUrunList().get(0).setAdet(newStock);
                    em.merge(card);

                }

                commitTransaction(em);
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
}
