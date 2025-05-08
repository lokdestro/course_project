package com.example.UserService.domain.dto;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CardRequest {

    private String name;
    private String number;

    @Column(name = "card_date")
    private LocalDateTime cardDate;
}
