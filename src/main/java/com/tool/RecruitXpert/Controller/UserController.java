package com.tool.RecruitXpert.Controller;


import com.tool.RecruitXpert.DTO.UserDTO.SignUserDto;
import com.tool.RecruitXpert.DTO.UserDTO.UpdateUserStatus;
import com.tool.RecruitXpert.DTO.UserDTO.UserRequest;
import com.tool.RecruitXpert.DTO.UserDTO.UserResponse;
import com.tool.RecruitXpert.Service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static org.hibernate.query.sqm.tree.SqmNode.log;

@RestController
@RequestMapping("/user")
@Slf4j
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/sign-up")
    public ResponseEntity<?> recruiterSignUp(@RequestBody SignUserDto signUpDto){
        try {
            String response = userService.signUp(signUpDto);
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            log.error(e.getMessage());
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/login")
    public ResponseEntity<?> recruiterSignIn(@RequestBody SignUserDto loginDto){
        try {
            String response = userService.logIn(loginDto);
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            String response = "Incorrect email or password";
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }
    }

    // create user using dto -
    @PostMapping("/add")
    public ResponseEntity addUser(@RequestBody UserRequest userRequest) {
        UserResponse userResponse = userService.addUser(userRequest);
        return new ResponseEntity(userResponse, HttpStatus.CREATED);
    }

    // delete user by id
    @DeleteMapping("/delete")
    public ResponseEntity deleteUser(@RequestParam int id) {
        String message = userService.deleteUser(id);
        return new ResponseEntity(message, HttpStatus.ACCEPTED);
    }

    //create DTO : update user account status by id
    @PutMapping("/update_status")
    public ResponseEntity updateUserStatus(@RequestBody UpdateUserStatus updateUserStatus){
          String message = userService.updateUserStatus(updateUserStatus);
          return new ResponseEntity(message,HttpStatus.CREATED);
    }

    @PutMapping("/update_user")
    public ResponseEntity updateUserStatus(@RequestBody UserRequest userRequest){
        String message = userService.updateUser(userRequest);
        return new ResponseEntity(message,HttpStatus.CREATED);
    }

}
