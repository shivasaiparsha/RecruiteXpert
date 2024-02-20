package com.tool.RecruitXpert.Service;

import com.tool.RecruitXpert.DTO.UserInfoSignUp;
import com.tool.RecruitXpert.Entities.UserInfo;

import com.tool.RecruitXpert.Repository.UserInfoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserInfoService {

    @Autowired
    private PasswordEncoder passwordEncoder;


    @Autowired
    private UserInfoRepository userInfoRepo;
    public String addUser(UserInfoSignUp dto) {
        UserInfo user = new UserInfo(dto.getName(), passwordEncoder.encode(dto.getPassword()),dto.getEmail(),dto.getRoles());
         userInfoRepo.save(user);
         return "Success";
    }


}
