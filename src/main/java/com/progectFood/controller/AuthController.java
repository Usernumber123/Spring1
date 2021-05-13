package com.progectFood.controller;

import com.progectFood.domian.dto.SignUpDto;
import com.progectFood.domian.dto.TokenRequest;
import com.progectFood.security.AuthService;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping
@RequiredArgsConstructor
public class AuthController {
    private final AuthService authService;
    @PostMapping("/token")
    public String getToken(@RequestBody TokenRequest tokenRequest){
return authService.generateToken(tokenRequest);
    }
    @SneakyThrows
    @PostMapping("/sign-up")
    public String signUp(@RequestBody SignUpDto signUpDto){
        return authService.signUp(signUpDto);
    }
}
