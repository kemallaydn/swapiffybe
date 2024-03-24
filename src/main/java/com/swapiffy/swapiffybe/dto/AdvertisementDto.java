package com.swapiffy.swapiffybe.dto;

import org.springframework.web.multipart.MultipartFile;

import java.sql.Timestamp;

public class AdvertisementDto {
    private String title;
    private String description;
    private String category;
    private String location;
    private MultipartFile imageurl;
    private String status;
    private Timestamp date;
    private Long userid;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public MultipartFile getImageurl() {
        return imageurl;
    }

    public void setImageurl(MultipartFile imageurl) {
        this.imageurl = imageurl;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Timestamp getDate() {
        return date;
    }

    public void setDate(Timestamp date) {
        this.date = date;
    }

    public Long getUserid() {
        return userid;
    }

    public void setUserid(Long userid) {
        this.userid = userid;
    }
}
