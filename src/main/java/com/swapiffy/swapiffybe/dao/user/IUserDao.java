package com.swapiffy.swapiffybe.dao.user;

import com.swapiffy.swapiffybe.dao.IBaseDao;
import com.swapiffy.swapiffybe.entity.User;

public interface IUserDao extends IBaseDao {
    public abstract User save(User user);
    public abstract User getUser(String id);
}
