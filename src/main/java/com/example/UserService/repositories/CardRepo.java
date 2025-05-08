package com.example.UserService.repositories;

import com.example.UserService.domain.model.Card;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

public interface CardRepo extends JpaRepository<Card, Long> {
    @Transactional
    @Modifying
    @Query("DELETE FROM Card c WHERE c.userId = :userId")
    void deleteByUserId(Long userId);

    Optional<Card> findByUserId(Long userId);
}
