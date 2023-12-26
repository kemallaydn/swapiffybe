package com.swapiffy.swapiffybe.service;

import com.swapiffy.swapiffybe.dao.cart.CartDaoImpl;
import com.swapiffy.swapiffybe.dao.cart.ICartDao;
import com.swapiffy.swapiffybe.entity.Card;
import com.swapiffy.swapiffybe.entity.Product;
import com.swapiffy.swapiffybe.entity.User;
import com.swapiffy.swapiffybe.entity.CardProduct;
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
    public List<CardProduct> runGetting(Long clinicianId,Long urunId){

        Card clinicianSpeciational =    cartDao.updateCard(3,urunId,1L);
        return  clinicianSpeciational.getSepetUrunList();
    }
    public List<CardProduct> sepeteUrunEkle(Long kullaniciId, Long urunId, int adet) {
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
        cartDao.addCard(kullaniciSepeti);

        return sepetUrunList;
    }


    public void azaltUrunAdet(Long sepetId, Long urunId, int azaltilanAdet) {
        Card sepet = cartDao.getCard(sepetId);
        for (CardProduct sepetUrun : sepet.getSepetUrunList()) {
            if (sepetUrun.getUrun().getId().equals(urunId)) {
                if (sepetUrun.getAdet() < azaltilanAdet) {
                    throw new RuntimeException("Sepette yeterli ürün bulunmamaktadır.");
                }
                sepetUrun.setAdet(sepetUrun.getAdet() - azaltilanAdet);
                cartDao.addCard(sepet);
                return;
            }
        }

        throw new RuntimeException("Belirtilen ürün sepetinizde bulunmamaktadır.");
    }

}
