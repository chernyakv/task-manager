package com.chernyak.fapi.controller;


import com.chernyak.fapi.message.LoginForm;
import com.chernyak.fapi.models.JwtToken;
import com.chernyak.fapi.models.User;
import com.chernyak.fapi.security.JwtTokenProvider;
import com.chernyak.fapi.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(maxAge = 3600)
@RestController
@RequestMapping("/token")
public class AuthenticationController {
    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtTokenProvider tokenProvider;


    private UserService userService;

    @Autowired
    public  AuthenticationController(UserService userService){
        this.userService = userService;
    }

    @PostMapping(value = "/generate-token")
    public  ResponseEntity<?> signin(@RequestBody LoginForm loginUser){
        final Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                    loginUser.getUsername(),
                    loginUser.getPassword()
                )
        );
        SecurityContextHolder.getContext().setAuthentication(authentication);
        final String token = tokenProvider.generateToken(authentication);
        final String refreshToken = tokenProvider.generateRefreshToken(authentication);
        return ResponseEntity.ok(new JwtToken(token, refreshToken));
    }
}
