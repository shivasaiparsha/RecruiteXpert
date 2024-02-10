package com.tool.RecruitXpert.Controller;

import com.tool.RecruitXpert.DTO.AdminDTO.FormAdminDTO;
import com.tool.RecruitXpert.DTO.AdminDTO.AdminResponse;
import com.tool.RecruitXpert.DTO.AdminDTO.AdminSignUp;
import com.tool.RecruitXpert.DTO.AdminDTO.UpdateAdminDTO;
import com.tool.RecruitXpert.Service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private AdminService adminService;


    @PostMapping("/sign-up")
    public ResponseEntity<?> adminSignUp(@RequestBody AdminSignUp signUpDto){
        try {
            String response = adminService.adminSignUp(signUpDto);
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/login")
    public ResponseEntity<?> adminSignIn(@RequestBody AdminSignUp loginDto){
        try {
            String response = adminService.adminSignIn(loginDto);
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            String response = "Incorrect organisation, email or password";
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }
    }


    @PostMapping("/updateAdminFile")
    public ResponseEntity addMultipartFile(@RequestParam MultipartFile file, @RequestParam Long id) throws  IOException{
               String message = adminService.addMultipartFile(file,id);
               return new ResponseEntity(message,HttpStatus.CREATED);
    }
    // addAdmin new admin
    @PostMapping("/register")
    public ResponseEntity addAdmin(@RequestBody FormAdminDTO formAdminDTO) throws IOException {
        String message = adminService.addAdmin(formAdminDTO);
        return new ResponseEntity(message, HttpStatus.CREATED);

    }

    // update
    //  ADMIN CAN UPDATE THE JOB ROLE AND DESCRIPTION

    @PutMapping("/update")
    public ResponseEntity updateAdmin(@RequestBody UpdateAdminDTO adminRequest) throws IOException {
        String message = adminService.updateAdmin(adminRequest);
        return new ResponseEntity(message, HttpStatus.ACCEPTED);
    }

    // delete profile by id
    @DeleteMapping("/delete")
    public ResponseEntity deleteAdmin(@RequestParam Long id) {
        String message = adminService.deleteAdmin(id);
        return new ResponseEntity(message, HttpStatus.ACCEPTED);
    }




}
