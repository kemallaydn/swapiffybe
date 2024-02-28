package com.swapiffy.swapiffybe;

import com.swapiffy.swapiffybe.dao.user.IUserDao;
import com.swapiffy.swapiffybe.dao.user.UserDaoFactory;

import java.util.logging.LogManager;
import java.util.logging.Logger;

public class ServiceFacade {
    private static ServiceFacade instance = null;
    private IUserDao userDao = null;
    public static ServiceFacade getInstance() {
        if (instance == null) {
            instance = new ServiceFacade();
        }
        return instance;
    }
    private void initializeDao() throws Exception {

        userDao = new UserDaoFactory().getUserDao();
        userDao.initialize();

    }
    public IUserDao getUserDao() {
        return userDao;
    }
}
