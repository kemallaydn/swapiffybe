package com.swapiffy.swapiffybe.dao.user;

public class UserDaoFactory {
    public IUserDao getUserDao() throws Exception {
        return new UserDaoImpl();
    }

}
