package com.tool.RecruitXpert.Controller;

import com.tool.RecruitXpert.DTO.SignUserDto;
import com.tool.RecruitXpert.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("auth/login")
public class AuthenticationController {

    @Autowired UserRepository repo;

    @PostMapping("/register")

    public ResponseEntity<?> createUser(@RequestBody SignUserDto user){

        if(repo.exis)
    }


    public Respon {}
    @PostMapping("/signup")
}
