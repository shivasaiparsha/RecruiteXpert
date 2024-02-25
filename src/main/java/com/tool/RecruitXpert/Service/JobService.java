package com.tool.RecruitXpert.Service;

import com.tool.RecruitXpert.DTO.JobDTO.JobCreationDTO;
import com.tool.RecruitXpert.DTO.JobDTO.UpdateJobDto;
import com.tool.RecruitXpert.Entities.JobsApplication;
import com.tool.RecruitXpert.Repository.JobRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;
import java.util.OptionalInt;

@Service
public class JobService {

    @Autowired
    private JobRepository jobRepository;

    public String createJob(JobCreationDTO dto) {
        JobsApplication jobsApplication =
                new JobsApplication(dto.getJobTitle(), dto.getJobDescription(), dto.getExperience(),
                        dto.getCTC(), dto.getLocation(), dto.getVacancy());
        jobRepository.save(jobsApplication);
        return "job successfully created";
    }

    public List<JobsApplication> findAllJobLists() {
        List<JobsApplication> jobsList = jobRepository.findAll();
        return jobsList;
    }

    public String updateJob(UpdateJobDto dto) {
        Optional<JobsApplication> op = jobRepository.findById(dto.getJobId());

        JobsApplication job = op.get();
        job.setJobTitle(dto.getJobTitle()); job.setVacancies(dto.getVacancy());
        job.setJobDescription(dto.getJobDescription());
        job.setExperience(dto.getExperience());
        job.setCTC(dto.getCTC());
        job.setLocation(dto.getLocation());
        jobRepository.save(job);
        return "you're job is up-to date";
    }

    public String deleteJob(long jobId) {
        jobRepository.deleteById(jobId);
        return "job deleted successfully";
    }
}
