package com.swapiffy.swapiffybe.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;

@Getter
@Setter
@Entity
@Table(name = "advertisement")
@NamedQueries({
        @NamedQuery(name = "Advertisement.findById", query = "SELECT u FROM Advertisement u WHERE u.userid = :id")
})
public class Advertisement {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String description;
    private String category;
    private String location;
    private String imageurl;
    private String status;
    private Timestamp date;
    private Long userid;
}
