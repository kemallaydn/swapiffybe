package com.swapiffy.swapiffybe.service;


import com.swapiffy.swapiffybe.dao.cart.CartDaoImpl;
import com.swapiffy.swapiffybe.dao.cart.ICartDao;
import com.swapiffy.swapiffybe.entity.Card;
import com.swapiffy.swapiffybe.entity.Product;
import com.swapiffy.swapiffybe.entity.User;
import com.swapiffy.swapiffybe.entity.CardProduct;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@Transactional
public class CardService {

    private final ICartDao cartDao;
    private final UserService userService;
    private final ProductService productService;
    public CardService(ICartDao cartDao, UserService userService, ProductService productService) {
        this.userService = userService;
        this.productService = productService;
        this.cartDao= new CartDaoImpl();

    }
    public Card runGetting(UUID kullaniciId){

        Card kullaniciSepetiOptional = cartDao.getCard(kullaniciId);
        return  kullaniciSepetiOptional;
    }
    public List<CardProduct> sepeteUrunEkle(UUID kullaniciId, Long urunId, int adet) {
        // Kullanıcıya ait sepeti bul
        Card kullaniciSepetiOptional = cartDao.getCard(kullaniciId);

        Card kullaniciSepeti;
        if (kullaniciSepetiOptional != null) {
            kullaniciSepeti = kullaniciSepetiOptional;
        } else {
            // Kullanıcının sepeti yoksa yeni bir sepet oluştur
            User kullanici = userService.getUserById(kullaniciId);

            kullaniciSepeti = new Card();
            kullaniciSepeti.setKullanici(kullanici);
            kullaniciSepeti.setSepetUrunList(new ArrayList<>());
        }

        List<CardProduct> sepetUrunList = kullaniciSepeti.getSepetUrunList();

        Optional<CardProduct> urunOptional = sepetUrunList.stream()
                .filter(sepetUrun -> sepetUrun.getUrun().getId().equals(urunId))
                .findFirst();
        if (urunOptional.isPresent()) {
            // Sepette var olan ürünün adetini arttır
            CardProduct mevcutUrun = urunOptional.get();
            mevcutUrun.setAdet(mevcutUrun.getAdet() + adet);
        } else {
            // Sepette olmayan bir ürünü ekleyin
            Product urun = productService.FindById(urunId);
            CardProduct yeniUrun = new CardProduct();
yeniUrun.setSepet(kullaniciSepeti);
            yeniUrun.setUrun(urun);
            yeniUrun.setAdet(adet);
            sepetUrunList.add(yeniUrun);

        }
        // Sepeti güncelle
        kullaniciSepeti.setSepetUrunList(sepetUrunList);
        cartDao.AddCard(kullaniciSepeti);

        return sepetUrunList;
    }


    public Card azaltUrunAdet(UUID sepetId, Long urunId, int azaltilanAdet) {
        Card sepet = cartDao.getCard(sepetId);
        for (CardProduct sepetUrun : sepet.getSepetUrunList()) {
            if (sepetUrun.getUrun().getId().equals(urunId)) {

                sepetUrun.setAdet(sepetUrun.getAdet() + azaltilanAdet);
                if (sepetUrun.getAdet() == 0) {
                    // Eğer ürünün adeti azaltılan adete eşitse ürünü listeden kaldır
                    sepet.getSepetUrunList().remove(sepetUrun);
                    cartDao.DeleteCard(sepetUrun.getId());
                    Card kullaniciSepetiOptional = cartDao.getCard(sepetId);
                    return kullaniciSepetiOptional;
                }
                cartDao.UpdateCard(sepetUrun);
                Card kullaniciSepetiOptional = cartDao.getCard(sepetId);
                return kullaniciSepetiOptional;
            }
        }

        throw new RuntimeException("Belirtilen ürün sepetinizde bulunmamaktadır.");
    }
    @Transactional
    public Card urunsil(UUID sepetId, Long urunId, int azaltilanAdet) {
        Card sepet = cartDao.getCard(sepetId);

        Iterator<CardProduct> iterator = sepet.getSepetUrunList().iterator();
        while (iterator.hasNext()) {
            CardProduct sepetUrun = iterator.next();
            if (sepetUrun.getUrun().getId().equals(urunId)) {
                iterator.remove(); // Ürünü listeden kaldır
                cartDao.DeleteCard(sepetUrun.getId());
                Card kullaniciSepetiOptional = cartDao.getCard(sepetId);
                return kullaniciSepetiOptional;
            }
        }

        throw new RuntimeException("Belirtilen ürün sepetinizde bulunmamaktadır.");
    }

}
