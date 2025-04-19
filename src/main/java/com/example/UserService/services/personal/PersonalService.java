package com.example.UserService.services.personal;

import com.example.UserService.domain.dto.GetPersonalRequest;
import com.example.UserService.domain.dto.GetPersonalResponse;
import com.example.UserService.domain.dto.UpdateRequest;
import com.example.UserService.domain.model.Personal;

public interface PersonalService {
    void Add(UpdateRequest request);
    GetPersonalResponse Get(GetPersonalRequest request);
}
