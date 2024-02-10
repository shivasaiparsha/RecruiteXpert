package com.tool.RecruitXpert.Service;

import com.tool.RecruitXpert.DTO.AdminDTO.AdminResponse;
import com.tool.RecruitXpert.DTO.JobDTO.JobCreationDTO;
import com.tool.RecruitXpert.Entities.JobsApplication;
import com.tool.RecruitXpert.Repository.JobRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class JobService {

    @Autowired
    private JobRepository jobRepository;

    @Autowired
    private ModelMapper modelMapper;


    public String createJob(JobCreationDTO jobCreationDTO) {
        JobsApplication jobsApplication = new JobsApplication();
        JobsApplication mappedApplication = modelMapper.map(jobCreationDTO, JobsApplication.class);
        jobRepository.save(mappedApplication);
        return "successfully";
    }

    public List<JobsApplication> findAllJobLists() {
        List<JobsApplication> jobsList = jobRepository.findAll();
        return jobsList;
    }
}
