package com.swapiffy.swapiffybe.dao.user;

import com.swapiffy.swapiffybe.dao.BaseDao;
import com.swapiffy.swapiffybe.entity.User;
import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;

public class UserDaoImpl extends BaseDao implements IUserDao {

    @Override
    public User save(User user) {
        EntityManager em = null;
        try {
            em=openTransactionalConnection();
            em.persist(user);
            commitTransaction(em);

        } catch (Exception ex) {
            rollbackTransaction(em);
            throw ex;
        } finally {
            closeConnection(em);
        }
        return user;
    }
    @Override
    public User getUser(String email) {
        EntityManager em = null;
        User user = null;
        try {
            em = openConnection();
            try {
                user = (User) em.createNamedQuery("User.findById").setParameter("email", email).getSingleResult();
            } catch (NoResultException nre) {
                System.err.println(nre.toString());
            }

        } catch (Exception ex) {
            System.err.println(ex.toString());
        } finally {
            closeConnection(em);
        }
        return user;
    }
}
