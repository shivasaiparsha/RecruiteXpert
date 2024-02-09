package com.tool.RecruitXpert.Controller;

import com.tool.RecruitXpert.DTO.AddRecruiterDto;
import com.tool.RecruitXpert.DTO.UpdateRecruiterDto;
import com.tool.RecruitXpert.Service.RecruiterService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;



//    login to recruiter ?: case : if recruiter approved then only he can able to access the portal

    @RestController
    @RequestMapping("/recruiter")
    @Slf4j
    public class RecruiterController {

        @Autowired
        RecruiterService service;

        @PostMapping("/new-recruiter")
        public ResponseEntity<?> addRecruiter(@RequestBody AddRecruiterDto dto){
            try {
                String response = service.addRecruiter(dto);
                return new ResponseEntity<>(response, HttpStatus.OK);
            }
            catch (Exception e){
                log.error(e.getMessage());
                return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
            }
        }

        @PutMapping("/update-recruiter")
        public ResponseEntity<?> updateRecruiter(@RequestBody UpdateRecruiterDto dto){
            try {
                String response = service.updateRecruiter(dto);
                return new ResponseEntity<>(response, HttpStatus.ACCEPTED);
            }
            catch (Exception e){
                log.error(e.getMessage());
                return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
            }
        }

        // just getting id from UI side
        @DeleteMapping("/delete/{id}")
        public ResponseEntity<?> addRecruiter(@RequestParam("id") int id){
            try {
                String response = service.deleteById(id);
                return new ResponseEntity<>(response, HttpStatus.ACCEPTED);
            }
            catch (Exception e){
                log.error(e.getMessage());
                return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
            }
        }

    }

