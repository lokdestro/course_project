package com.example.UserService.domain.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class GetPersonalRequest {
    private String name;

    @JsonFormat(shape = JsonFormat.Shape.NUMBER)
    private LocalDateTime date = LocalDateTime.now();
}
