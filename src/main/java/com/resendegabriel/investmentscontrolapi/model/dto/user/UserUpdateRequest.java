package com.resendegabriel.investmentscontrolapi.model.dto.user;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record UserUpdateRequest(@NotBlank @Email String email) {
}
