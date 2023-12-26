package com.swapiffy.swapiffybe.dao.userToken;

import com.swapiffy.swapiffybe.dao.BaseDao;
import com.swapiffy.swapiffybe.entity.UserToken;
import jakarta.persistence.EntityManager;
import org.springframework.stereotype.Repository;

@Repository
public class UserTokenImpl extends BaseDao implements IUserTokenDao {

    @Override
    public void save(UserToken token) {
        EntityManager em = null;
        try {
            em=openTransactionalConnection();
            em.persist(token);
            commitTransaction(em);

        } catch (Exception ex) {
            rollbackTransaction(em);
            throw ex;
        } finally {
            closeConnection(em);
        }
    }
}
