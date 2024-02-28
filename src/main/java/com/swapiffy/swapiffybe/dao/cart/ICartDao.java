package com.swapiffy.swapiffybe.dao.cart;

import com.swapiffy.swapiffybe.dao.IBaseDao;
import com.swapiffy.swapiffybe.entity.Card;
import com.swapiffy.swapiffybe.entity.CardProduct;

public interface ICartDao extends IBaseDao {
    public abstract Card getCard(Long uuid);
    public abstract Card DeleteCard(Long uuid);
    public abstract void AddCard(Card card);
    public abstract void UpdateCard(CardProduct card);

}
