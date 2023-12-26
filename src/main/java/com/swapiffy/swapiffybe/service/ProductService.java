package com.swapiffy.swapiffybe.service;

import com.swapiffy.swapiffybe.dao.products.IProductDao;
import com.swapiffy.swapiffybe.dao.products.ProductDaoImpl;
import com.swapiffy.swapiffybe.dao.userToken.IUserTokenDao;
import com.swapiffy.swapiffybe.dao.userToken.UserTokenImpl;
import com.swapiffy.swapiffybe.dto.LoginResponse;
import com.swapiffy.swapiffybe.entity.Product;
import com.swapiffy.swapiffybe.entity.User;
import com.swapiffy.swapiffybe.entity.UserToken;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;
@Service
public class ProductService {
    public List<Product> getAll() {
        IProductDao productDao =new ProductDaoImpl();
        return productDao.getAllProduct();
    }
    public Product FindById(Long id) {
        IProductDao productDao =new ProductDaoImpl();
        return productDao.getProduct(id);
    }
}
