package com.tool.RecruitXpert.Controller;

import com.tool.RecruitXpert.DTO.JobDTO.JobCreationDTO;
import com.tool.RecruitXpert.Entities.JobsApplication;
import com.tool.RecruitXpert.Service.JobService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("jobs")
public class JobController {

    @Autowired
    private JobService jobService;


    // here we're just showing all the job list for user to apply
    @GetMapping("/showAllJobList")
    public ResponseEntity<?> findAllJobList(){
        List<JobsApplication> jobs = jobService.findAllJobLists();
        return new ResponseEntity<>(jobs, HttpStatus.CREATED);
    }

    // update and delete endpoint
    @GetMapping("/updateJobs")
    public ResponseEntity<?> updateJobs(){
        List<JobsApplication> jobs = jobService.findAllJobLists();
        return new ResponseEntity<>(jobs, HttpStatus.CREATED);
    }
// code pushed
}
