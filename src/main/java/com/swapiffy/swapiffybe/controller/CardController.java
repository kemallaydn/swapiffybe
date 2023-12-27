package com.swapiffy.swapiffybe.controller;

import com.swapiffy.swapiffybe.dto.AddCardRequest;
import com.swapiffy.swapiffybe.dto.cardProductDto;
import com.swapiffy.swapiffybe.entity.Card;
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
    public  List<cardProductDto> getCard(@RequestBody AddCardRequest request){
        Card card=    cardService.runGetting(request.getKullaniciId());
        cardProductDto cardProductDto = new cardProductDto();
        List<cardProductDto> productDTOList = new ArrayList<>();
        for (CardProduct cardProduct : card.getSepetUrunList()) {
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

    @PutMapping("/update")
    public List<cardProductDto>  azaltUrunAdet(@RequestBody AddCardRequest request) {
        Card productList=   cardService.azaltUrunAdet(request.getKullaniciId(), request.getUrunId(), request.getAdet());
        List<cardProductDto> productDTOList = new ArrayList<>();
        for (CardProduct cardProduct : productList.getSepetUrunList()) {
            cardProductDto productDTO = new cardProductDto();
            productDTO.setProduct(cardProduct.getUrun());
            productDTO.setAdet(cardProduct.getAdet());
            productDTOList.add(productDTO);
        }
        return productDTOList;
    }
    @DeleteMapping("/delete")
    public List<cardProductDto> urunsil(@RequestBody AddCardRequest request) {
        Card productList=   cardService.urunsil(request.getKullaniciId(), request.getUrunId(), request.getAdet());
        List<cardProductDto> productDTOList = new ArrayList<>();
        for (CardProduct cardProduct : productList.getSepetUrunList()) {
            cardProductDto productDTO = new cardProductDto();
            productDTO.setProduct(cardProduct.getUrun());
            productDTO.setAdet(cardProduct.getAdet());
            productDTOList.add(productDTO);
        }
        return productDTOList;

    }
}
