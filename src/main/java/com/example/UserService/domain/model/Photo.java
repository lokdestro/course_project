package com.example.UserService.domain.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "photoes")
public class Photo {

    @Id
    @Column(name = "user_id")
    private Long userId;

    private String name;

    @Column(unique = true)
    private String photo;
}