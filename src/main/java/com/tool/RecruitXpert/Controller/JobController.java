package com.tool.RecruitXpert.Controller;

import com.tool.RecruitXpert.DTO.JobDTO.JobCreationDTO;
import com.tool.RecruitXpert.DTO.JobDTO.UpdateJobDto;
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
        try{
            List<JobsApplication> jobs = jobService.findAllJobLists();
            return new ResponseEntity<>(jobs, HttpStatus.CREATED);
        }
        catch (Exception e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    // update and delete endpoint
    @PutMapping("/update-job")
    public ResponseEntity<?> updateJobs(@RequestBody UpdateJobDto dto){
        try{
            String msg = jobService.updateJob(dto);
            return new ResponseEntity<>(msg, HttpStatus.GONE);
        }
        catch (Exception e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/delete-job/{id}")
    public ResponseEntity<?> deleteJob(@PathVariable("id") long jobId){
        try{
            String msg = jobService.deleteJob(jobId);
            return new ResponseEntity<>(msg, HttpStatus.OK);
        }
        catch (Exception e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
}
