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
@RequestMapping("/jobs")
public class JobController {

    @Autowired
    private JobService jobService;

    @PostMapping("/create")
    public ResponseEntity createJob(@RequestBody JobCreationDTO jobCreationDTO){
        String message = jobService.createJob(jobCreationDTO);
        return new ResponseEntity(message, HttpStatus.CREATED);
    }

    @GetMapping("admin/jobApplication/allListOfJobs")
    public ResponseEntity findAllJobLists(){
        List<JobsApplication> jobs = jobService.findAllJobLists();
        return new ResponseEntity<>(jobs, HttpStatus.CREATED);
    }
}
