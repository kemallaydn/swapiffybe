package com.swapiffy.swapiffybe.dao;

import java.io.Serializable;
import java.util.Map;
import java.util.logging.Logger;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;


public class BaseDao implements Serializable {

    private EntityManagerFactory FACTORY = null;

    protected Logger logger = null;

    public BaseDao() {
        logger = Logger.getLogger(this.getClass().getName());
        FACTORY = Persistence.createEntityManagerFactory("swapiffy");
    }

    public BaseDao(String persistenceUnitName, Map<?,?> additionalParameters) {
        logger = Logger.getLogger(this.getClass().getName());
        FACTORY = Persistence.createEntityManagerFactory(persistenceUnitName, additionalParameters);
    }

    public EntityManager openConnection() {
        if (FACTORY == null) {
            throw new RuntimeException("The EntityManagerFactory is null.  This must be passed in to the constructor or set using the setEntityManagerFactory() method.");
        }

        EntityManager em = FACTORY.createEntityManager();
        if (em != null) {
            return em;
        }
        return null;
    }

    public void closeConnection(EntityManager em) {
        try {
            if (em != null && em.isOpen()) {
                rollbackTransaction(em);
                em.close();
            }
        } catch (Exception e) {
            System.err.println(e.toString());
        }

    }

    public EntityManager openTransactionalConnection() {
        EntityManager em = openConnection();
        if (em != null && em.isOpen()) {
            em.getTransaction().begin();
            return em;
        }
        return null;
    }

    public void startTransaction(EntityManager em) {
        if (em != null && em.isOpen() && !em.getTransaction().isActive()) {
            em.getTransaction().begin();
        }
    }

    public void commitTransaction(EntityManager em) {
        if (em != null && em.isOpen() && em.getTransaction().isActive()) {
            em.getTransaction().commit();
        }

    }

    public void rollbackTransaction(EntityManager em) {
        try {
            if (em != null && em.isOpen() && em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
        } catch (Exception e) {
            System.err.println(e.toString());
        }
    }

    public String hibernateClobConverterToString(Object object) throws Exception {
        /*
         * DB2 have some issue Solution : db2set DB2_RESTRICT_DDF=true
         * http://www.ibm.com/developerworks/forums/thread.jspa?threadID=268395
         */
        if (object != null) {
            java.sql.Clob clob;
            clob = (java.sql.Clob) object;
            if ((int) clob.length() > 0) {
                return clob.getSubString(1, (int) clob.length());
            }
        }
        return "";
    }

}

