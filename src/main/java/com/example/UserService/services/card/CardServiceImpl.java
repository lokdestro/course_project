package com.example.UserService.services.card;

import com.example.UserService.domain.dto.CardRequest;
import com.example.UserService.domain.model.Card;
import com.example.UserService.repositories.CardRepo;
import com.example.UserService.services.user.UserSerivce;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class CardServiceImpl implements CardService {
    private final CardRepo cardRepo;
    private final UserSerivce userSerivce;

    public CardServiceImpl(CardRepo cardRepo, UserSerivce userSerivce) {
        this.cardRepo = cardRepo;
        this.userSerivce = userSerivce;
    }

    public void AddCard(CardRequest request) {
        Card card = Card.builder()
                .userId(userSerivce.GetCurrentUser().getId())
                .number(request.getNumber())
                .cardDate(LocalDateTime.now())
                .name(request.getName())
                .build();
        card.setUserId(userSerivce.GetCurrentUser().getId());
        cardRepo.save(card);
    }

    public void RemoveCard() {
        var id = userSerivce.GetCurrentUser().getId();
        cardRepo.deleteById(id);
    }

    public void UpdateCard(CardRequest request) {
        Long userId = userSerivce.GetCurrentUser().getId();

        Card existingCard = cardRepo.findByUserId(userId)
                .orElseThrow(EntityNotFoundException::new);

        existingCard.setNumber(request.getNumber());
        existingCard.setName(request.getName());
        existingCard.setCardDate(LocalDateTime.now());

        cardRepo.save(existingCard);
    }

    public Optional<Card> GetCard() {
        return cardRepo.findById(userSerivce.GetCurrentUser().getId());
    }
}
