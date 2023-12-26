package com.swapiffy.swapiffybe.dto;

import com.swapiffy.swapiffybe.entity.Product;

public class cardProductDto {
    private Product product;
    private  int adet;

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public int getAdet() {
        return adet;
    }

    public void setAdet(int adet) {
        this.adet = adet;
    }
}
