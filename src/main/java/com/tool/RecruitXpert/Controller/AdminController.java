package com.tool.RecruitXpert.Controller;

import com.tool.RecruitXpert.DTO.AdminDTO.AdminRequest;
import com.tool.RecruitXpert.DTO.AdminDTO.AdminResponse;
import com.tool.RecruitXpert.DTO.AdminDTO.UpdateAdminDTO;
import com.tool.RecruitXpert.DTO.AdminDTO.UpdateRecruiterStatus;
import com.tool.RecruitXpert.Entities.Recruiter;
import com.tool.RecruitXpert.Service.AdminService;
import com.tool.RecruitXpert.Service.RecruiterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin")
public class AdminController {

    @Autowired private AdminService adminService;
    @Autowired private RecruiterService recruiterService;

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

//    show this list to admin home page whos status is not active.
//    return the list whos recruiter status == null;
    @GetMapping("/recruiter/listOfRecruitersByStatus")
    public ResponseEntity<?> returnReturnStatus(){
        try {
            List<Recruiter> list = recruiterService.getListForNullStatus();
            return new ResponseEntity<>(list, HttpStatus.ACCEPTED);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/approve")
    public ResponseEntity<?> returnReturnStatus(@RequestBody UpdateRecruiterStatus update){
        try {
            String response = recruiterService.updateStatus(update);
            return new ResponseEntity<>(response, HttpStatus.ACCEPTED);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/manage")
    public ResponseEntity<?> getApprovedList(){
        try {
            List<Recruiter> list = recruiterService.getApprovedList();
            return new ResponseEntity<>(list, HttpStatus.ACCEPTED);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    // delete profile by id
    @DeleteMapping("/delete")
    public ResponseEntity<?> deleteAdmin(@RequestParam Long id) {
        String message = adminService.deleteAdmin(id);
        return new ResponseEntity<>(message, HttpStatus.ACCEPTED);
    }

}
