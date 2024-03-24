package com.swapiffy.swapiffybe.dao.user;

import com.swapiffy.swapiffybe.dao.IBaseDao;
import com.swapiffy.swapiffybe.entity.User;

import java.util.UUID;

public interface IUserDao extends IBaseDao {
    public abstract void initialize();
    public abstract User save(User user);
    public abstract User getUserByEmail(String id);
    public abstract User getUserById(UUID id);
}
