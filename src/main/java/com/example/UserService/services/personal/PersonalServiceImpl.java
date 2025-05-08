package com.example.UserService.services.personal;

import com.example.UserService.domain.dto.GetPersonalRequest;
import com.example.UserService.domain.dto.GetPersonalResponse;
import com.example.UserService.domain.dto.UpdateRequest;
import com.example.UserService.domain.model.Personal;
import com.example.UserService.repositories.PersonalRepo;
import com.example.UserService.services.user.UserSerivce;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class PersonalServiceImpl implements PersonalService {
    private final PersonalRepo personalRepo;
    private final UserSerivce userSerivce;

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

    public static boolean isValidFieldName(String fieldName) {
        return ALLOWED_FIELD_NAMES.contains(fieldName);
    }

    public PersonalServiceImpl(PersonalRepo personalRepo, UserSerivce userSerivce) {
        this.personalRepo = personalRepo;
        this.userSerivce = userSerivce;
    }

    public void Add(UpdateRequest request) {
        if (!isValidFieldName(request.getName())) {
            throw new RuntimeException();
        }
        Personal personal = Personal.builder()
                .userId(userSerivce.GetCurrentUser().getId())
                .name(request.getName())
                .value(request.getValue())
                .createAt(LocalDateTime.now())
                .build();

        personalRepo.save(personal);
    }

    public GetPersonalResponse Get(GetPersonalRequest request) {
        if (!isValidFieldName(request.getName())) {
            throw new RuntimeException();
        }

        var id = userSerivce.GetCurrentUser().getId();
        var data = personalRepo.findByNameIdAndMaxCreateAtBeforeDate(request.getName(), id, request.getDate());
        if (data.isPresent()) {
            var resp = GetPersonalResponse.builder().value(data.get().getValue()).build();
        }
        throw new RuntimeException();
    }

    public List<String> CheckRegistrationStatus() {
        List<String> status = new ArrayList<>();

        var user_id = userSerivce.GetCurrentUser().getId();

        for (var field : ALLOWED_FIELD_NAMES) {
            var data = personalRepo.findFirstByUserIdAndNameOrderByCreateAtDesc(user_id, field);
            if (data.isEmpty()) {
                status.add(field);
            }
        }

        return status;
    }


}
