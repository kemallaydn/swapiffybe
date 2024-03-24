package com.swapiffy.swapiffybe.entity;

import jakarta.persistence.*;

import java.util.UUID;

@Entity
@NamedQueries(value = {
        @NamedQuery(name = "Favorites.findByUserId", query = "SELECT u FROM Favorites u WHERE u.userId = :userId"),
        @NamedQuery(name = "Favorites.removeFavorites", query = "DELETE FROM Favorites u WHERE u.userId = :userId"),
        @NamedQuery(name = "Favorites.RemoveFromFavorites", query = "DELETE FROM Favorites u WHERE u.userId = :userId AND u.productId = :productId")
})
public class Favorites {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    @Column(name = "user_id")
    String userId;
    @Column(name = "product_id")
    String productId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }
}
