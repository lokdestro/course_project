package com.example.UserService.repositories;

import com.example.UserService.domain.model.Card;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CardRepo extends JpaRepository<Card, Long> {
}
