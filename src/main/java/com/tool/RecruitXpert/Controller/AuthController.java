package com.tool.RecruitXpert.Controller;

import com.tool.RecruitXpert.DTO.AdminDTO.AdminRequest;
import com.tool.RecruitXpert.DTO.LogIn.LogIn;
import com.tool.RecruitXpert.Entities.Admin;
import com.tool.RecruitXpert.Repository.AdminRepository;
import com.tool.RecruitXpert.Repository.RecruiterRepository;
import com.tool.RecruitXpert.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("auth")
public class AuthController {

    @Autowired
    UserRepository userRepository;
    @Autowired
    AdminRepository adminRepository;
    @Autowired
    RecruiterRepository recruiterRepository;
//
//    @PostMapping("/admin/login")
//    public String registerAdmin(@RequestBody LogIn logIn) {
//
//        Authentication authentication = authenticationManager.authenticate
//                (new UsernamePasswordAuthenticationToken(logIn.getEmail(), logIn.getPassword()));
//        if (authentication.isAuthenticated()) {
//            return jwtService.generateToken(logIn.getEmail());
//        } else {
//            throw new UsernameNotFoundException("invalid user request !");
//        }
//    }

//    @PostMapping("/user/login")
//    public String registerAdmin(@RequestBody AdminRequest admin) {
//        Admin admin1 =
//    }
//
//
//    @PostMapping("/recruiter/login")
//    public String registerUser(   // userdto @RequestBody AdminRequest admin) {
//
//        }
    
}
