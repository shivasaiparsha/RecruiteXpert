package com.tool.RecruitXpert.Service;

import com.tool.RecruitXpert.DTO.RecruiterDto.AddRecruiterDto;
import com.tool.RecruitXpert.DTO.RecruiterDto.RecruiterHomepageResponseDTO;
import com.tool.RecruitXpert.DTO.RecruiterDto.UpdateRecruiterDto;
import com.tool.RecruitXpert.Entities.Admin;
import com.tool.RecruitXpert.Entities.Recruiter;
import com.tool.RecruitXpert.Repository.RecruiterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.Arrays;
import java.util.Optional;

@Service
public class RecruiterService {

    @Autowired
    RecruiterRepository repository;

    // login to recruiter ?: case : if recruiter approved then only he can able to access the portal

    // add recruiter
    public String addRecruiter(AddRecruiterDto dto) throws Exception {

        // case this email already present - make @Unique in entity
        Recruiter recruiter = new Recruiter(dto.getFirstname(), dto.getLastname(),
                dto.getEmail(), dto.getPassword(), dto.getJobRole());

        repository.save(recruiter);
        return "Recruiter Added Successfully";
    }

    // update itself
    public String updateRecruiter(UpdateRecruiterDto dto) throws Exception {

        Optional<Recruiter> optional = repository.findById(dto.getId());
        Recruiter recruiter = optional.get();

        recruiter.setFirstname(dto.getFirstname());
        recruiter.setLastname(dto.getLastname());
        recruiter.setEmail(dto.getEmail());
        recruiter.setPassword(dto.getPassword());
        recruiter.setJobRole(dto.getJobRole());

        repository.save(recruiter);
        return "Recruiter Added Successfully";
    }

    // delete recruiter by id
    public String deleteById(int id) throws Exception {

        Optional<Recruiter> optional = repository.findById(id);
        if (!optional.isPresent()) throw new Exception("Delete correct recruiter, this id is not present");

        Recruiter recruiter = optional.get();
        repository.deleteById(recruiter.getId());

        return "Recruiter Deleted Successfully";
    }

    public RecruiterHomepageResponseDTO recruiterDashboard(int id) {
          Recruiter recruiter = repository.findById(id).get();
          Admin admin = recruiter.getAdmin();
        RecruiterHomepageResponseDTO recruiterHomepageResponseDTO = new RecruiterHomepageResponseDTO();

        recruiterHomepageResponseDTO.setRecruiterImg(recruiter.getRecruiterImg());
        recruiterHomepageResponseDTO.setRecruiterDate((Date) recruiter.getCreatedDate());
        recruiterHomepageResponseDTO.setRecruiterLocation(recruiter.getLocation());
        recruiterHomepageResponseDTO.setRecruiterName(recruiter.getFirstname());
        recruiterHomepageResponseDTO.setRecruiterRole(recruiter.getJobRole());
        recruiterHomepageResponseDTO.setAdminDate((Date) admin.getCreatedDate());
        recruiterHomepageResponseDTO.setAdminImg(admin.getAdminImg());
        recruiterHomepageResponseDTO.setAdminName(admin.getFirstname());
        recruiterHomepageResponseDTO.setAdminRole(admin.getAdminRole());
        return  recruiterHomepageResponseDTO;
    }


    // recruiter can approve or disapprove the user status


    // recruiter can change the status of user like [commenter | reviewer | all actions ]

}

