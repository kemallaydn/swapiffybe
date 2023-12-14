package com.swapiffy.swapiffybe.dao;

import jakarta.persistence.EntityManager;

public interface IBaseDao {

    public abstract EntityManager openTransactionalConnection();

    public abstract void rollbackTransaction(EntityManager em);

    public abstract void closeConnection(EntityManager em);

    public abstract void commitTransaction(EntityManager em);

}
