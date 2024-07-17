package com.resendegabriel.investmentscontrolapi.controller;

import com.resendegabriel.investmentscontrolapi.controller.docs.user.UserDeleteDoc;
import com.resendegabriel.investmentscontrolapi.controller.docs.user.UserGetByIdDoc;
import com.resendegabriel.investmentscontrolapi.controller.docs.user.UserRegisterDoc;
import com.resendegabriel.investmentscontrolapi.controller.docs.user.UserUpdateDoc;
import com.resendegabriel.investmentscontrolapi.model.dto.user.UserRequest;
import com.resendegabriel.investmentscontrolapi.model.dto.user.UserResponse;
import com.resendegabriel.investmentscontrolapi.model.dto.user.UserUpdateRequest;
import com.resendegabriel.investmentscontrolapi.service.auth.UserService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
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
        log.info("[POST - USER REGISTER] - start");
        var response = userService.save(userRequest);
        log.info("[POST - USER REGISTER] - end");
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @UserGetByIdDoc
    @GetMapping("/{id}")
    public ResponseEntity<UserResponse> getById(@PathVariable Long id) {
        log.info("[GET - USER] - start");
        var response = userService.getById(id);
        log.info("[GET - USER] - end");
        return ResponseEntity.ok().body(response);
    }

    @UserUpdateDoc
    @PutMapping("/{id}")
    public ResponseEntity<UserResponse> update(@PathVariable Long id, @RequestBody @Valid UserUpdateRequest userRequest) {
        log.info("[PUT - USER] - start");
        var response = userService.update(id, userRequest);
        log.info("[PUT - USER] - end");
        return ResponseEntity.ok().body(response);
    }

    @UserDeleteDoc
    @DeleteMapping("/{id}")
    public ResponseEntity<UserResponse> deleteById(@PathVariable Long id) {
        log.info("[DELETE - USER] - start");
        userService.deleteById(id);
        log.info("[DELETE - USER] - end");
        return ResponseEntity.noContent().build();
    }
}
