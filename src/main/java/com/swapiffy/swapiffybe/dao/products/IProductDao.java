package com.swapiffy.swapiffybe.dao.products;

import com.swapiffy.swapiffybe.dao.IBaseDao;
import com.swapiffy.swapiffybe.entity.Product;

import java.util.List;

public interface IProductDao extends IBaseDao {
    public abstract List<Product> getAllProduct();
    public abstract Product getProduct(Long id);
}
