package com.projeto.adamod4.service;

import com.projeto.adamod4.domain.Card;
import com.projeto.adamod4.repository.CardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class CardService {
    private final CardRepository cardRepository;

    private final String API_URL = "https://db.ygoprodeck.com/api/v7/cardinfo.php";

    @Autowired
    public CardService(CardRepository cardRepository) {
        this.cardRepository = cardRepository;
    }

    public Card createCardByName(String cardName){
        RestTemplate restTemplate = new RestTemplate();
        String url = API_URL + "?name=" + cardName;
        CardApiResponse response = restTemplate.getForObject(url, CardApiResponse.class);
        if(response != null && response.getData() != null && !response.getData().isEmpty()){
            Card card = mapApiResponseToCard(response.getData().get(0));
            return cardRepository.save(card);
        } else {
            return null;
        }

    }

    //public Card createRandomCard(){
    //    return cardRepository.save(card);
    //}

    public Card mapApiResponseToCard(CardData cardData) {
        Card card = new Card(cardData.getName(), cardData.getImg(), cardData.getLevel(), cardData.getAtk(), cardData.getDef());
        return card;
    }

    public List<Card> getAllCards() {
        return cardRepository.findAll();
    }

    public Card getCardById(Long id) {
        return cardRepository.findById(id).orElse(null);
    }

    public Card createCard(Card card) {
        return cardRepository.save(card);
    }

    public Card updateCard(Long id, Card card) {
        Card existingCard = cardRepository.findById(id).orElse(null);
        if (existingCard != null) {
            existingCard.setName(card.getName());
            existingCard.setImg(card.getImg());
            existingCard.setLevel(card.getLevel());
            existingCard.setAtk(card.getAtk());
            existingCard.setDef(card.getDef());
            return cardRepository.save(existingCard);
        }
        return null;
    }

    public void deleteCard(Long id) {
        cardRepository.deleteById(id);
    }
}
