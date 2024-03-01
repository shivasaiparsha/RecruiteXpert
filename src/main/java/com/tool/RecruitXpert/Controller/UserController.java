package com.tool.RecruitXpert.Controller;


import com.tool.RecruitXpert.DTO.UserDTO.*;
import com.tool.RecruitXpert.Entities.JobsApplication;
import com.tool.RecruitXpert.Entities.User;
import com.tool.RecruitXpert.Service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/user")
@Slf4j
@CrossOrigin(origins =" https://recruiterexperttest.netlify.app")
public class UserController {

    @Autowired
    private UserService userService;

    // apply jobs
    @PutMapping("/job-apply")
    public ResponseEntity<?> jobAppliedByUser(@RequestBody JobApplyDto dto) {
        try {
            String response = userService.jobAppliedByUser(dto);
            return new ResponseEntity<>(response, HttpStatus.OK);
        }
        catch(Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.OK);
        }
    }

    @PostMapping("/sign-up")
    public ResponseEntity<?> recruiterSignUp(@RequestBody SignUserDto signUpDto) {
        try {
            String response = userService.signUp(signUpDto);
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            log.error(e.getMessage());
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    // create user using dto -
    @PostMapping("/add")
    public ResponseEntity<?> addUser(@RequestBody UserRequest userRequest) {
        UserResponse userResponse = userService.addUser(userRequest);
        return new ResponseEntity<>(userResponse, HttpStatus.CREATED);
    }

    // delete user by id
    @DeleteMapping("/delete")
    public ResponseEntity<?> deleteUser(@RequestParam int id) {
        String message = userService.deleteUser(id);
        return new ResponseEntity<>(message, HttpStatus.ACCEPTED);
    }

    //create DTO : update user account status by id
    @PutMapping("/update_status")
    public ResponseEntity<?> updateUserStatus(@RequestBody UpdateUserStatus updateUserStatus) {
        String message = userService.updateUserStatus(updateUserStatus);
        return new ResponseEntity<>(message, HttpStatus.CREATED);
    }

    @PutMapping("/update_user")
    public ResponseEntity<?> updateUserStatus(@RequestBody UserRequest userRequest) {
        String message = userService.updateUser(userRequest);
        return new ResponseEntity<>(message, HttpStatus.CREATED);
    }

    @GetMapping("/user/listOfUsersByStatus")
    public ResponseEntity<?> returnReturnStatus() {
        try {
            List<User> list = userService.getListForNullStatus();
            return new ResponseEntity<>(list, HttpStatus.ACCEPTED);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/approve")
    public ResponseEntity<?> returnReturnStatus(@RequestBody UpdateUserStatus update) {
        try {
            String response = userService.updateStatus(update);
            return new ResponseEntity<>(response, HttpStatus.ACCEPTED);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/manage")
    public ResponseEntity<?> getApprovedList() {
        try {
            List<User> list = userService.getApprovedList();
            return new ResponseEntity<>(list, HttpStatus.ACCEPTED);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/get-List-of-Applied-Jobs/{id}")
    public ResponseEntity<?> getAllAppliedJobList(@PathVariable("id") int userId) {
        try {
            List<JobsApplication> list = userService.getAllAppliedJobList(userId);
            return new ResponseEntity<>(list, HttpStatus.ACCEPTED);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
}
