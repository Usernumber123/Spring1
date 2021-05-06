package com.progectFood.security;

import com.progectFood.domian.dto.SignUpDto;
import com.progectFood.domian.dto.TokenRequest;
import com.progectFood.domian.entity.User;
import com.progectFood.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
private final  UserDetailsServiceImpl userDetailsService;
private final AuthenticationManager authenticationManager;
private final JwtTokenService tokenService;

    public String generateToken(TokenRequest tokenRequest) {
        User person = userRepository.findOneByLogin(tokenRequest.getLogin()).orElseThrow(() -> new RuntimeException());
        if (!passwordEncoder.matches(tokenRequest.getPassword(), person.getPassword())) {
            throw new RuntimeException();
        }
        return tokenService.generateToken(person);
    }

    public String signUp(SignUpDto signUpDto){
        User user=new User();
        user.setLogin(signUpDto.getLogin());
        user.setPassword(passwordEncoder.encode(signUpDto.getPassword()));
        userRepository.save(user);
        return generateToken(TokenRequest.builder().login(signUpDto.getLogin()).password(signUpDto.getPassword()).build());
    }
}
