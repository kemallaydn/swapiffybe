package com.swapiffy.swapiffybe.dao.products;

import com.swapiffy.swapiffybe.dao.BaseDao;
import com.swapiffy.swapiffybe.entity.Product;
import com.swapiffy.swapiffybe.entity.User;
import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;

import java.util.List;

public class ProductDaoImpl extends BaseDao implements IProductDao {
    @Override
    public List<Product> getProduct() {
        EntityManager em = null;
        List <Product>  product = null;
        try {
            em = openConnection();
            try {
                product = (List<Product>) em.createNamedQuery("Product.all").getResultList();
            } catch (NoResultException nre) {
                System.err.println(nre.toString());
            }

        } catch (Exception ex) {
            System.err.println(ex.toString());
        } finally {
            closeConnection(em);
        }
        return product;
    }

}
