package com.tool.RecruitXpert.Controller;

import com.tool.RecruitXpert.DTO.UserInfoSignUp;
import com.tool.RecruitXpert.Service.UserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/userInfo")
public class UserInfoController {

    @Autowired
    private UserInfoService userInfoService;
//
//    @Autowired
//    private AuthenticationManager authenticationManager;
//
//    @Autowired
//    private JwtHelper jwtService;

    @PostMapping("/register")
    public String addUser(@RequestBody UserInfoSignUp userInfoSignUp){
            return userInfoService.addUser(userInfoSignUp);

    }

}
