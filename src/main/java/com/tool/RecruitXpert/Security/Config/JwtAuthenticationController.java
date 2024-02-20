package com.tool.RecruitXpert.Security.Config;

import com.tool.RecruitXpert.Security.Jwt.JwtService;
import com.tool.RecruitXpert.Security.JwtRequest;
import com.tool.RecruitXpert.Security.JwtResponse;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@Slf4j
public class JwtAuthenticationController {

    private UserDetailsService userDetailsService;

    private AuthenticationManager manager;

    private JwtService helper;

    @PostMapping("/auth")
    public ResponseEntity<JwtResponse> login(@RequestBody JwtRequest request) {

        // authenticate first
        this.doAuthenticate(request.getName(), request.getPassword());

        UserDetails userDetails = userDetailsService.loadUserByUsername(request.getName());
        String token = this.helper.generateToken(userDetails);

        JwtResponse response = JwtResponse.builder()
                .jwtToken(token)
                .name(userDetails.getUsername()).build();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    private void doAuthenticate(String name, String password) {
        UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(name, password);
        try {
            manager.authenticate(authentication);
        } catch (BadCredentialsException e) {
            throw new BadCredentialsException("Credentials Invalid !!");
        }

    }
}