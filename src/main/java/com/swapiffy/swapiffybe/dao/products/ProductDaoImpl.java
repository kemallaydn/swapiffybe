package com.swapiffy.swapiffybe.dao.products;

import com.swapiffy.swapiffybe.dao.BaseDao;
import com.swapiffy.swapiffybe.entity.Product;
import com.swapiffy.swapiffybe.entity.User;
import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public class ProductDaoImpl extends BaseDao implements IProductDao {
    @Override
    public List<Product> getAllProduct() {
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

    @Override
    public Product getProduct(Long id) {
        EntityManager em = null;
        Product  product = null;
        try {
            em = openConnection();
            try {
                product = (Product) em.createNamedQuery("Product.findById").setParameter("id",id).getSingleResult();
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
