//package com.tool.RecruitXpert.Security;
//
//import com.tool.RecruitXpert.Entities.Admin;
//import com.tool.RecruitXpert.Entities.UserInfo;
//import com.tool.RecruitXpert.Repository.AdminRepository;
//import com.tool.RecruitXpert.Repository.UserInfoRepository;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
//import org.springframework.stereotype.Component;
//
//import java.util.Optional;
//
//@Component
//public class UserInfoUserDetailsService implements UserDetailsService {
//
//    @Autowired
//    private UserInfoRepository userInfoRepository;
//
//    @Override
//    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//        Optional<UserInfo> userInfo = userInfoRepository.findByName(username);
//        return userInfo.map(UserInfoUserDetails::new)
//                .orElseThrow(() -> new UsernameNotFoundException("user not found " + username));
//
//    }
//}
//
