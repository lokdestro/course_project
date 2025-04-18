package com.example.UserService.domain.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "personal")
public class Personal {

    public static final List<String> ALLOWED_FIELD_NAMES = Arrays.asList(
            "first_name",
            "second_name",
            "patronymic_name",
            "birth_date",
            "phone",
            "email",
            "passport_series",
            "passport_number",
            "address_type",
            "city",
            "street",
            "building",
            "apartment"
    );

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "user_id")
    private Long userId;

    private String name;  // Название поля из ALLOWED_FIELD_NAMES
    private String value; // Значение поля

    @Column(name = "create_at")
    private LocalDateTime createAt = LocalDateTime.now();;

    public static boolean isValidFieldName(String fieldName) {
        return ALLOWED_FIELD_NAMES.contains(fieldName);
    }

    public boolean isCurrentFieldValid() {
        return isValidFieldName(this.name);
    }
}