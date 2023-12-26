package com.swapiffy.swapiffybe.entity;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.*;

import java.util.List;
import java.util.UUID;
@Entity
@Table(name = "card")
@NamedQueries({
        @NamedQuery(name = "Card.findById", query = "SELECT u FROM Card u WHERE u.kullanici.id = :id")
})
public class Card {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @OneToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User kullanici;
    @JsonIgnore
    @OneToMany(mappedBy = "sepet",cascade = {CascadeType.MERGE},fetch =FetchType.EAGER)
    private List<CardProduct> sepetUrunList;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getKullanici() {
        return kullanici;
    }

    public void setKullanici(User kullanici) {
        this.kullanici = kullanici;
    }

    public List<CardProduct> getSepetUrunList() {
        return sepetUrunList;
    }

    public void setSepetUrunList(List<CardProduct> sepetUrunList) {
        this.sepetUrunList = sepetUrunList;
    }
}
