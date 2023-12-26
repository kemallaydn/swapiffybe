package com.swapiffy.swapiffybe.entity;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.*;

import java.util.UUID;
@Entity
@Table(name = "cardproduct")
@NamedQueries({
        @NamedQuery(name = "cardproduct.findById", query = "SELECT u FROM CardProduct u WHERE u.id = :id"),
        @NamedQuery(name = "cardproduct.updateCardProduct", query = "UPDATE CardProduct u SET u.adet = :newStock WHERE u.urun.id = :id")
})
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")

public class CardProduct {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "urun_id", nullable = false)
    private Product urun;

    private int adet;

    @ManyToOne
    @JoinColumn(name = "card_id")
    private Card sepet;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Product getUrun() {
        return urun;
    }

    public void setUrun(Product urun) {
        this.urun = urun;
    }

    public int getAdet() {
        return adet;
    }

    public void setAdet(int adet) {
        this.adet = adet;
    }

    public Card getSepet() {
        return sepet;
    }

    public void setSepet(Card sepet) {
        this.sepet = sepet;
    }
}
