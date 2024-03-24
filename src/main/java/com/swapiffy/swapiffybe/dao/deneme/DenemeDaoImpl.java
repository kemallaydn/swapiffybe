package com.swapiffy.swapiffybe.dao.deneme;

import com.swapiffy.swapiffybe.dao.BaseDao;
import com.swapiffy.swapiffybe.entity.deneme;
import jakarta.persistence.EntityManager;

public class DenemeDaoImpl extends BaseDao {
    public void save(deneme deneme) {
        EntityManager em = null;
        try {
            em=openTransactionalConnection();
            em.persist(deneme);
            commitTransaction(em);

        } catch (Exception ex) {
            rollbackTransaction(em);
            throw ex;
        } finally {
            closeConnection(em);
        }
    }
}
