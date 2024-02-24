package com.tool.RecruitXpert.Service;


import com.tool.RecruitXpert.DTO.AdminDTO.*;
import com.tool.RecruitXpert.DTO.RecruiterDto.JobAssignDto;
import com.tool.RecruitXpert.DTO.RecruiterDto.responseDto.JobTitleList;
import com.tool.RecruitXpert.Entities.Admin;
import com.tool.RecruitXpert.Entities.JobsApplication;
import com.tool.RecruitXpert.Entities.Recruiter;
import com.tool.RecruitXpert.Exceptions.AdminNotFoundException;
import com.tool.RecruitXpert.Repository.AdminRepository;
import com.tool.RecruitXpert.Repository.JobRepository;
import com.tool.RecruitXpert.Repository.RecruiterRepository;
import com.tool.RecruitXpert.Repository.UserInfoRepository;
import com.tool.RecruitXpert.Security.UserInfo;
import com.tool.RecruitXpert.Security.UserInfoController;
import com.tool.RecruitXpert.Security.UserInfoDto;
import com.tool.RecruitXpert.Security.UserInfoService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Service
public class AdminService{

    @Autowired private AdminRepository adminRepository;
    @Autowired private RecruiterRepository recruiterRepository;
    @Autowired private PasswordEncoder encoder;
    @Autowired private UserInfoService userInfoService;
    @Autowired private JobRepository jobRepository;
    @Autowired ModelMapper modelMapper;

    public String addAdmin(FormAdminDTO formAdminDTO) throws IOException {
        Optional<Admin> optional = adminRepository.findById(formAdminDTO.getAdminId());
        Admin admin = optional.get();
        admin.setAdminImg(formAdminDTO.getAdminImg().getBytes());
        admin.setAdminRole(formAdminDTO.getRole());
        admin.setFirstname(formAdminDTO.getFirstname());
        admin.setLastname(formAdminDTO.getLastname());
        admin.setAddress(formAdminDTO.getAddress());
        admin.setLocation(formAdminDTO.getLocation());
        admin.setWebsite(formAdminDTO.getWebsite());
        admin.setCompanyName(formAdminDTO.getCompanyName());
        adminRepository.save(admin);
        return "Successfully added !!";
    }

    public String updateAdmin(UpdateAdminDTO adminRequest) throws IOException {
        Optional<Admin> optionalAdmin = adminRepository.findById(adminRequest.getAdminId());
        if (!optionalAdmin.isPresent())
            throw new AdminNotFoundException("Enter correct ID, Admin not Found");

        Admin admin = optionalAdmin.get();
        admin.setFirstname(adminRequest.getFirstname());
        admin.setLastname(adminRequest.getLastname());
        admin.setAddress(adminRequest.getAddress());
        admin.setWebsite(adminRequest.getWebsite());
        //admin.setAdminImg(adminRequest.getAdminImg().getBytes());
        admin.setCompanyName(adminRequest.getCompanyName());

        adminRepository.save(admin);
        return "Successfully Updated !";
    }

    public String deleteAdmin(Long id) {
        Optional<Admin> optionalAdmin = adminRepository.findById(id);
        if (!optionalAdmin.isPresent()) {
            throw new AdminNotFoundException("Admin not Found");
        }
        adminRepository.deleteById(id);
        return "Successfully Deleted";
    }


    public String adminSignUp(AdminSignUp dto) throws Exception {
//        validation : check unique email
        boolean check = adminRepository.existsByEmail(dto.getEmail());
        if (check) throw new RuntimeException("Email already present, Enter valid email");

//        check unique org
        boolean uniqueOrg = adminRepository.existsByOrganization(dto.getOrganization());
        if (uniqueOrg) throw new RuntimeException("organization already present, Enter new one");

        Admin admin = new Admin(dto.getEmail(), dto.getOrganization(), "ADMIN");
        admin.setPassword(encoder.encode(dto.getPassword()));

        UserInfoDto userStore = new UserInfoDto();
        userStore.setEmail(dto.getEmail());
        userStore.setPassword(dto.getPassword());
        userStore.setName("-");
        userStore.setRoles("ADMIN");
        userInfoService.addUser(userStore);
        adminRepository.save(admin);
        return "signup successfully";
    }

    // login to recruiter ?: case : if recruiter approved then only he can able to access the portal
    public String adminSignIn(AdminSignUp login) {
        Admin recruiter = adminRepository.findByEmail(login.getEmail()).get();

        if (recruiter.getEmail().equals(login.getEmail()) &&
                recruiter.getOrganization().equals(login.getOrganization()) &&
                recruiter.getPassword().equals(login.getPassword()))
            return "successful login";

        else return "Incorrect organisation, email or password";
    }

//    public String addMultipartFile(MultipartFile file, Long id) throws IOException{
//
//        Admin admin = adminRepository.findById(id).get();
//        //byte[] fileImage= file.getBytes();
//      //  admin.setAdminImg(fileImage);
//        adminRepository.save(admin);
//        return "success";
//    }

    public AdminHomePageResponseDTO adminDashboard(Long id) {

        Admin admin = adminRepository.findById(id).get();
        AdminHomePageResponseDTO adminHomepageResponseDTO = new AdminHomePageResponseDTO();

        adminHomepageResponseDTO.setAdminDate((Date) admin.getCreatedDate());
        adminHomepageResponseDTO.setAdminImg(admin.getAdminImg());
        adminHomepageResponseDTO.setAdminName(admin.getFirstname());
        adminHomepageResponseDTO.setAdminRole(admin.getAdminRole());
        adminHomepageResponseDTO.setWebsite(admin.getWebsite());
        adminHomepageResponseDTO.setLocation(admin.getLocation());
        adminHomepageResponseDTO.setCompanyName(admin.getCompanyName());
        return adminHomepageResponseDTO;
    }

    public String jobAssigned(JobAssignDto jobAssignDto) {

        Recruiter recruiter = recruiterRepository.findById(jobAssignDto.getRecruiterId()).get();


//        recruiter.setRecruiterStatus(jobAssignDto.getRecruiterRole().);

        return "status updated Successfully";
    }

    public String deleteJob(Long jobId) {

        return "job deleted successfully";
    }


    public List<JobTitleList> getJobList() {

        List<JobTitleList> list = new ArrayList<>();
        List<JobsApplication> applicationList = jobRepository.findAll();

        for (JobsApplication jobsApplication : applicationList){
            JobTitleList jobTitleList = new JobTitleList();
            jobTitleList.setJobId(jobsApplication.getJobid());
            jobTitleList.setJobTitle(jobsApplication.getJobTitle());

            list.add(jobTitleList);
        }
        return list;
    }
}

