package com.home.finder.homefinder.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.home.finder.homefinder.exception.UserNotFoundException;
import com.home.finder.homefinder.model.LoginRequest;
import com.home.finder.homefinder.model.SignUpRequest;
import com.home.finder.homefinder.service.AuthService;

import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/api/v1")
@Validated
@Slf4j
public class AuthController {

    @Autowired
    private AuthService authService;

    @PostMapping("/login")
    public void login(@RequestBody @Valid LoginRequest loginRequest) {
        log.info("Auth Controller called "+  loginRequest);
        try {
            if(authService.login(loginRequest)) {
                log.info("Login is successfull");
            }
        } catch(UserNotFoundException userNotFoundException) {
            log.error("Login Falied", userNotFoundException);
        }
    }

    @PostMapping("/register")
    public void register(@RequestBody @Valid SignUpRequest signUpRequest) {
        log.info("Register method called "+  signUpRequest);
        authService.register(signUpRequest);
        log.info("User is successfully registered");
    }

}
