package com.projeto.adamod4.controller;

import com.projeto.adamod4.domain.Card;
import com.projeto.adamod4.service.CardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.io.IOException;
import java.util.List;

@Controller
public class HtmlController {

    @Autowired
    private CardService cardService;

    @GetMapping("/display")
    public String display(Model model) {
        List<Card> cards = cardService.getAllCards();
        model.addAttribute("cards", cards);
        return "card";
    }


    @PostMapping("/createByName")
    public String createByName(String cardName) {
        Card card = cardService.createCardByName(cardName);
        return "redirect:/";
    }

    @PostMapping("/createRandom")
    public String createRandom() {
        //Card card = cardService.createRandomCard();
        return "redirect:/";
    }
}
