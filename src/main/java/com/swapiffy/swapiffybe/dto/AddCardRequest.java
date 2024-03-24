package com.swapiffy.swapiffybe.dto;

import java.util.UUID;

public class AddCardRequest {
    private UUID kullaniciId;
    private Long urunId;
    private int adet;

    public UUID getKullaniciId() {
        return kullaniciId;
    }

    public void setKullaniciId(UUID kullaniciId) {
        this.kullaniciId = kullaniciId;
    }

    public Long getUrunId() {
        return urunId;
    }

    public void setUrunId(Long urunId) {
        this.urunId = urunId;
    }

    public int getAdet() {
        return adet;
    }

    public void setAdet(int adet) {
        this.adet = adet;
    }
}
