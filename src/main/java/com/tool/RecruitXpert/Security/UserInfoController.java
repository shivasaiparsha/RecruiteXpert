package com.tool.RecruitXpert.Security;

import com.tool.RecruitXpert.DTO.SignUp.ResponseJWT;
import com.tool.RecruitXpert.Enums.Status;
import com.tool.RecruitXpert.Repository.UserInfoRepository;
import com.tool.RecruitXpert.Security.Config.AuthRequest;
import com.tool.RecruitXpert.Security.Jwt.JwtService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Optional;

@RestController
@RequestMapping("/auth")
@CrossOrigin(origins =" https://recruiterexperttest.netlify.app")
public class UserInfoController {
    @Autowired
    private UserInfoService service;

    @Autowired
    private JwtService jwtService;

    @Autowired
    private UserInfoRepository repository;

    PasswordEncoder passwordEncoder=new BCryptPasswordEncoder();
    @Autowired
    private AuthenticationManager authenticationManager;

    @PostMapping("/addNewUser")
    public ResponseEntity<?> addNewUser(@RequestBody UserInfoDto dto) {
        try {
            String response = service.addUser(dto);
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.OK);
        }
    }


    @PostMapping("/logout")
    public ResponseEntity<?> logout(HttpServletRequest request) {
        // Perform logout actions such as token invalidation, session cleanup, etc.
        // For JWT, you may not have to do much on the server side.
        return ResponseEntity.ok("successfully Logout.");
    }

HashMap<String, Integer> map =new HashMap<>();
    @PostMapping("/login")
    public ResponseEntity<?> authenticateAndGetToken(@RequestBody AuthRequest authRequest) throws Exception {

        UserInfo user = repository.findByEmail(authRequest.getEmail()).get();

        String email_id = user.getEmail();

        ResponseJWT res = new ResponseJWT();

        Authentication authentication = authenticationManager.authenticate
                (new UsernamePasswordAuthenticationToken(authRequest.getEmail(), authRequest.getPassword()));

        if (authentication.isAuthenticated()) {
            // for each successful login setting count 0;
            user.setPasswordCount(0);
            String jwtToken=jwtService.generateToken(authRequest.getEmail());
            res.setJwt_token(jwtToken);
            res.setEmail_id(email_id);
            return new ResponseEntity<>(res,HttpStatus.OK);
        }

        if (user.isAccountBlock())
            throw new RuntimeException("Oops! you're account is blocked! reach-out to Admin");

        if(user.getPasswordCount() > 3){
            user.setAccountBlock(true);
            throw new RuntimeException("You've already done 3 wrong attempts, now " +
                    "kindly reach-out to admin for further actions.");
        }

        else {
            int count = user.getPasswordCount();
            user.setPasswordCount(count + 1);
            throw new UsernameNotFoundException("invalid user request !");
        }
    }



// TESTING purpose

    @GetMapping("/welcome")
    public String welcome() {
        return "Welcome this endpoint is not secure";
    }

    @GetMapping("/user/userProfile")
    @PreAuthorize("hasAuthority('ROLE_USER')")
    public String userProfile() {
        return "Welcome to User Profile";
    }

    @GetMapping("/admin/adminProfile")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public String adminProfile() {
        return "Welcome to Admin Profile";
    }

}