package com.resendegabriel.investmentscontrolapi.controller.auth;

import com.resendegabriel.investmentscontrolapi.controller.docs.auth.LoginDoc;
import com.resendegabriel.investmentscontrolapi.model.auth.User;
import com.resendegabriel.investmentscontrolapi.model.dto.user.LoginResponse;
import com.resendegabriel.investmentscontrolapi.model.dto.user.UserRequest;
import com.resendegabriel.investmentscontrolapi.service.auth.TokenService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/auth")
public class AuthenticationController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private TokenService tokenService;

    @LoginDoc
    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@RequestBody @Valid UserRequest userRequest) {
        log.info("[POST - LOGIN] - start");
        var authToken = new UsernamePasswordAuthenticationToken(userRequest.email(), userRequest.password());
        var authentication = authenticationManager.authenticate(authToken);

        var user = (User) authentication.getPrincipal();
        var tokenJWT = tokenService.generateToken(user);

        log.info("[POST - LOGIN] - end");
        return ResponseEntity.ok().body(LoginResponse.buildResponse(user, tokenJWT));
    }
}
