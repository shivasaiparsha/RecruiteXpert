package com.tool.RecruitXpert.Controller;

import com.tool.RecruitXpert.DTO.AdminDTO.UpdateRecruiterStatus;
import com.tool.RecruitXpert.DTO.RecruiterDto.AddRecruiterDto;
import com.tool.RecruitXpert.DTO.RecruiterDto.RecruiterHomepageResponseDTO;

import com.tool.RecruitXpert.DTO.RecruiterDto.RecruiterSignUp;
import com.tool.RecruitXpert.DTO.RecruiterDto.UpdateRecruiterDto;
import com.tool.RecruitXpert.Entities.Recruiter;
import com.tool.RecruitXpert.Service.RecruiterService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


//    login to recruiter ?: case : if recruiter approved then only he can able to access the portal

@RestController
@RequestMapping("/recruiter")
@Slf4j
public class RecruiterController {

    @Autowired
    RecruiterService service;

    @PostMapping("/sign-up")
    public ResponseEntity<?> recruiterSignUp(@RequestBody RecruiterSignUp signUpDto){
        try {
            String response = service.signUp(signUpDto);
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            log.error(e.getMessage());
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/login")
    public ResponseEntity<?> recruiterSignIn(@RequestBody RecruiterSignUp loginDto){
        try {
            String response = service.logIn(loginDto);
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            String response = "Incorrect organisation, email or password";
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/register")
    public ResponseEntity<?> addRecruiter(@RequestBody AddRecruiterDto dto) {
        try {
            String response = service.addRecruiter(dto);
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            log.error(e.getMessage());
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/update")
    public ResponseEntity<?> updateRecruiter(@RequestBody UpdateRecruiterDto dto) {
        try {
            String response = service.updateRecruiter(dto);
            return new ResponseEntity<>(response, HttpStatus.ACCEPTED);
        } catch (Exception e) {
            log.error(e.getMessage());
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    // just getting id from UI side
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> addRecruiter(@PathVariable("id") int id) {
        try {
            String response = service.deleteById(id);
            return new ResponseEntity<>(response, HttpStatus.ACCEPTED);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/dashboard/{id}")
    public ResponseEntity recruiterDashboard(@PathVariable int id){
        RecruiterHomepageResponseDTO recruiterHomepageResponseDTO = service.recruiterDashboard(id);
        return new ResponseEntity<>(recruiterHomepageResponseDTO,HttpStatus.ACCEPTED);
    }

<<<<<<< HEAD



=======
>>>>>>> 9a7486afa8fc40465507b6c54c76c4eccce87a56
}

