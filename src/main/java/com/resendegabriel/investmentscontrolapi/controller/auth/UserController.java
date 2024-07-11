package com.resendegabriel.investmentscontrolapi.controller.auth;

import com.resendegabriel.investmentscontrolapi.controller.docs.auth.UserRegisterDoc;
import com.resendegabriel.investmentscontrolapi.model.dto.user.UserRequest;
import com.resendegabriel.investmentscontrolapi.model.dto.user.UserResponse;
import com.resendegabriel.investmentscontrolapi.service.auth.UserService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    @UserRegisterDoc
    @PostMapping
    public ResponseEntity<UserResponse> register(@RequestBody @Valid UserRequest userRequest) {
        log.info("[POST - REGISTER] - start");
        var response = userService.save(userRequest);
        log.info("[POST - REGISTER] - end");
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
}
