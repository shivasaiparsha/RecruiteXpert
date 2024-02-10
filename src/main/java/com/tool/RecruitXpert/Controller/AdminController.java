package com.tool.RecruitXpert.Controller;

import com.tool.RecruitXpert.DTO.AdminDTO.AdminRequest;
import com.tool.RecruitXpert.DTO.AdminDTO.AdminResponse;
import com.tool.RecruitXpert.DTO.AdminDTO.UpdateAdminDTO;
import com.tool.RecruitXpert.Service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private AdminService adminService;

    // addAdmin new admin
    @PostMapping("/register")
    public ResponseEntity addAdmin(@RequestBody AdminRequest adminRequest) {
        AdminResponse adminResponse = adminService.addAdmin(adminRequest);
        return new ResponseEntity(adminResponse, HttpStatus.CREATED);

    }

    // update
    //  ADMIN CAN UPDATE THE JOB ROLE AND DESCRIPTION
    @PutMapping("/update")
    public ResponseEntity updateAdmin(@RequestBody UpdateAdminDTO adminRequest) {
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
