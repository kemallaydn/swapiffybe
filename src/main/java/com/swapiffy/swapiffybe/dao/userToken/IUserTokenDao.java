package com.swapiffy.swapiffybe.dao.userToken;

import com.swapiffy.swapiffybe.dao.IBaseDao;
import com.swapiffy.swapiffybe.entity.UserToken;

public interface IUserTokenDao extends IBaseDao {
    public abstract void save(UserToken token);
}
