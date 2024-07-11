package com.resendegabriel.investmentscontrolapi.model.dto.user;

import com.resendegabriel.investmentscontrolapi.model.auth.User;
import lombok.Builder;

@Builder
public record LoginResponse(Long id,

                            String email,

                            String role,

                            String token
) {

    public static LoginResponse buildResponse(User user, String tokenJWT) {
        return LoginResponse.builder()
                .id(user.getId())
                .email(user.getEmail())
                .role(user.getRole().toString())
                .token(tokenJWT)
                .build();
    }
}
