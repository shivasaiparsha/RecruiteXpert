package com.tool.RecruitXpert.Controller;

import com.tool.RecruitXpert.DTO.AdminDTO.UpdateRecruiterStatus;
import com.tool.RecruitXpert.DTO.RecruiterDto.*;

import com.tool.RecruitXpert.Entities.JobsApplication;
import com.tool.RecruitXpert.Entities.Recruiter;
import com.tool.RecruitXpert.Service.RecruiterService;
import com.tool.RecruitXpert.Service.ResumeATS;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;


//    login to recruiter ?: case : if recruiter approved then only he can able to access the portal

@RestController
@RequestMapping("/recruiter")
@Slf4j
@CrossOrigin(origins =" https://recruiterexperttest.netlify.app")

public class RecruiterController {

    @Autowired
    RecruiterService service;

    @Autowired
    ResumeATS resumeATS;

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
    public ResponseEntity<?> recruiterDashboard(@PathVariable int id){
        RecruiterHomepageResponseDTO recruiterHomepageResponseDTO = service.recruiterDashboard(id);
        return new ResponseEntity<>(recruiterHomepageResponseDTO,HttpStatus.ACCEPTED);
    }


    @GetMapping("/user-Lists-who-Applied-to-recruiter/{id}")
    public ResponseEntity<?> getListOfUsersAppliedToJob(@PathVariable("id") long jobId) {
        try {
            List<UserAppliedJobstoRecruiterDto> list = service.getListOfUsersAppliedToJob(jobId);
            return new ResponseEntity<>(list, HttpStatus.ACCEPTED);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/get-user-score")
    public ResponseEntity<?> resumePercentage(@RequestParam("jd") MultipartFile jd, @RequestParam("resume") MultipartFile resume){

         String score= resumeATS.getThePercentageOfresumeWithJd(jd, resume);

         return new ResponseEntity<>(score, HttpStatus.OK);
    }
}

