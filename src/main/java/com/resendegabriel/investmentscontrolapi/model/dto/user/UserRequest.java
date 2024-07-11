package com.resendegabriel.investmentscontrolapi.model.dto.user;

import jakarta.validation.constraints.NotBlank;

public record UserRequest(@NotBlank String email,

                          @NotBlank String password) {
}
