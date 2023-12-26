package com.swapiffy.swapiffybe.controller;

import com.swapiffy.swapiffybe.dto.AddCardRequest;
import com.swapiffy.swapiffybe.dto.cardProductDto;
import com.swapiffy.swapiffybe.entity.CardProduct;
import com.swapiffy.swapiffybe.service.CardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/v1/api/card")
public class CardController {
    private final CardService cardService;

    @Autowired
    public CardController(CardService cardService) {
        this.cardService = cardService;
    }

    @PostMapping("/getCard")
    public  List<cardProductDto> urungetir(@RequestBody AddCardRequest request){
        List<CardProduct> productList=    cardService.runGetting(request.getKullaniciId(),request.getUrunId());
        cardProductDto cardProductDto = new cardProductDto();
        List<cardProductDto> productDTOList = new ArrayList<>();
        for (CardProduct cardProduct : productList) {
            cardProductDto productDTO = new cardProductDto();
            productDTO.setProduct(cardProduct.getUrun());
            productDTO.setAdet(cardProduct.getAdet());

            productDTOList.add(productDTO);
        }
        return productDTOList;
    }
    @PostMapping("/addCard")
    public List<cardProductDto> urunEkle(@RequestBody AddCardRequest request) {
        List<CardProduct> productList=    cardService.sepeteUrunEkle(request.getKullaniciId(), request.getUrunId(), request.getAdet());
        cardProductDto cardProductDto = new cardProductDto();
        List<cardProductDto> productDTOList = new ArrayList<>();
        for (CardProduct cardProduct : productList) {
            cardProductDto productDTO = new cardProductDto();
                  productDTO.setProduct(cardProduct.getUrun());
                  productDTO.setAdet(cardProduct.getAdet());

            productDTOList.add(productDTO);
        }
        return productDTOList;
    }
    @PostMapping("/updateCard")
    public List<cardProductDto> urunguncelle(@RequestBody AddCardRequest request) {
        List<CardProduct> productList=    cardService.sepeteUrunEkle(request.getKullaniciId(), request.getUrunId(), request.getAdet());
        cardProductDto cardProductDto = new cardProductDto();
        List<cardProductDto> productDTOList = new ArrayList<>();
        for (CardProduct cardProduct : productList) {
            cardProductDto productDTO = new cardProductDto();
                  productDTO.setProduct(cardProduct.getUrun());
                  productDTO.setAdet(cardProduct.getAdet());

            productDTOList.add(productDTO);
        }
        return productDTOList;
    }
    @PutMapping("/update")
    public void azaltUrunAdet(@RequestBody AddCardRequest request) {
        cardService.azaltUrunAdet(request.getKullaniciId(), request.getUrunId(), request.getAdet());
    }
}
