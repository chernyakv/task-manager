package com.chernyak.fapi.controller;


import com.chernyak.fapi.message.LoginForm;
import com.chernyak.fapi.models.JwtToken;
import com.chernyak.fapi.models.User;
import com.chernyak.fapi.security.JwtTokenProvider;
import com.chernyak.fapi.service.UserService;
import io.jsonwebtoken.ExpiredJwtException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(maxAge = 3600)
@RestController
@RequestMapping("/token")
public class AuthenticationController {
    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtTokenProvider tokenProvider;

    @Qualifier("customUserDetailsService")
    @Autowired
    private UserDetailsService userDetailsService;

    @Bean
    public BCryptPasswordEncoder encoder(){
        return new BCryptPasswordEncoder();
    }


    private UserService userService;

    @Autowired
    public  AuthenticationController(UserService userService){
        this.userService = userService;
    }

    @PostMapping(value = "/generate-token")
    public  ResponseEntity<?> signin(@RequestBody LoginForm loginUser) {
        final Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                    loginUser.getUsername(),
                    loginUser.getPassword()
                )
        );
        try{
            SecurityContextHolder.getContext().setAuthentication(authentication);
            final String token = tokenProvider.generateToken(authentication);
            final String refreshToken = tokenProvider.generateRefreshToken(authentication);
            return ResponseEntity.ok(new JwtToken(token, refreshToken));
        } catch (ExpiredJwtException e) {
            return  new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
    }

    @PostMapping(value = "/refresh-token")
    public ResponseEntity<?> refresh(@RequestBody String refreshToken) {

        String username = tokenProvider.getUsernameFromToken(refreshToken);
        UserDetails userDetails = userDetailsService.loadUserByUsername(username);
        if(tokenProvider.validateToken(refreshToken, userDetails)){
            final Authentication authentication = new UsernamePasswordAuthenticationToken(userDetails, "", userDetails.getAuthorities());

            SecurityContextHolder.getContext().setAuthentication(authentication);
            final String token = tokenProvider.generateToken(authentication);
            final String newRefreshToken = tokenProvider.generateRefreshToken(authentication);
            return ResponseEntity.ok(new JwtToken(token, newRefreshToken));
        }
        return null;


    }
}
