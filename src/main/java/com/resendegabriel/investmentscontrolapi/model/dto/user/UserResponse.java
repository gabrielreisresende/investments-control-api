package com.resendegabriel.investmentscontrolapi.model.dto.user;

import com.resendegabriel.investmentscontrolapi.model.auth.User;
import lombok.Builder;

@Builder
public record UserResponse(Long id,

                           String email,

                           String role
) {

    public static UserResponse fromEntity(User user) {
        return UserResponse.builder()
                .id(user.getId())
                .email(user.getEmail())
                .role(user.getRole().toString())
                .build();
    }
}
