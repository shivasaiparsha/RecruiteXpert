package com.tool.RecruitXpert.Service;

import com.tool.RecruitXpert.DTO.AdminDTO.UpdateRecruiterStatus;
import com.tool.RecruitXpert.DTO.RecruiterDto.*;

import com.tool.RecruitXpert.DTO.RecruiterDto.responseDto.AssignRecruiterResponse;
import com.tool.RecruitXpert.Entities.Admin;
import com.tool.RecruitXpert.Entities.JobsApplication;
import com.tool.RecruitXpert.Entities.Recruiter;
import com.tool.RecruitXpert.Entities.User;
import com.tool.RecruitXpert.Enums.EntityRoles;
import com.tool.RecruitXpert.Enums.Status;
import com.tool.RecruitXpert.Repository.JobRepository;
import com.tool.RecruitXpert.Repository.RecruiterRepository;
import com.tool.RecruitXpert.Security.UserInfoDto;
import com.tool.RecruitXpert.Security.UserInfoService;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.sql.Date;
import java.util.Optional;

@Service
public class RecruiterService {

    @Autowired
    RecruiterRepository repository;
    @Autowired private UserInfoService userInfoService;

    @Autowired
    private JavaMailSender mailSender;
    @Autowired JobRepository jobRepository;
    @Autowired private PasswordEncoder passwordEncoder;

    // signup recruiter :
    public String signUp(RecruiterSignUp dto) throws Exception{

//        validation : check unique email
        boolean check = repository.existsByEmail(dto.getEmail());
        if(check) throw new RuntimeException("Email already present, Enter valid email");

//        check unique org
        boolean uniqueOrg = repository.existsByOrganisation(dto.getOrganisationName());
//        if(uniqueOrg) throw new RuntimeException("organization already present, Enter new one");

        Recruiter recruiter = new Recruiter(dto.getEmail(), dto.getOrganisationName(), EntityRoles.RECRUITER);
        recruiter.setPassword(passwordEncoder.encode(dto.getPassword()));
        repository.save(recruiter);

        UserInfoDto userStore = new UserInfoDto();
        userStore.setEmail(dto.getEmail());
        userStore.setPassword(dto.getPassword());
        userStore.setName("-");
        userStore.setRoles(EntityRoles.RECRUITER.name());
        userInfoService.addUser(userStore);
        return "signup successfully";
    }

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


    // getting list of recruiters whoes status is null for user
    public List<Recruiter> getListForNullStatus(){

        List<Recruiter> list = repository.findAll();
        List<Recruiter> ans = new ArrayList<>();

        for(Recruiter recruiter : list){
            if(recruiter.getRecruiterStatus() == null)
                ans.add(recruiter);
        }
        return ans;
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
    public String updateStatus(UpdateRecruiterStatus status) {
        Optional<Recruiter> op = repository.findById(status.getId());
        if(!op.isPresent()) throw new RuntimeException("please update correct recruiter");

        Recruiter recruiter = op.get();
        recruiter.setRecruiterStatus(status.getRecruiterStatus());
        repository.save(recruiter);
        return "Status updated successful";

    // adminController me email integration - status return karna direct email me
//        SimpleMailMessage mailMessage = new SimpleMailMessage();
//        String msg = "Hi Welcome to Recruit Expert portal! "+"\n get placed with our recruiters.";
//        String body1 = msg + "\n Admin updated you're profiles status to : " + status.getRecruiterStatus();
//        mailMessage.setSubject("Recruit Xpert");
//        mailMessage.setFrom("shivasaiparsha@gmail.com");
//        mailMessage.setTo(recruiter.getEmail());
//        mailMessage.setText(body1);
//        mailSender.send(mailMessage);
    }

    // getting the list who is approved
    public List<Recruiter> getApprovedList(){
        List<Recruiter> list = repository.findAll();
        List<Recruiter> ans = new ArrayList<>();

        for(Recruiter recruiter : list){
            if(recruiter.getRecruiterStatus()!=null &&
                    recruiter.getRecruiterStatus().equals(Status.APPROVED))
                ans.add(recruiter);
        }
        return ans;
    }

    public List<AssignRecruiterResponse> getAllListOfRecruiters() {
        List<Recruiter> list = repository.findAll();
        List<AssignRecruiterResponse> returnList = new ArrayList<>();

        for(Recruiter recruiter : list){
            AssignRecruiterResponse assign = new AssignRecruiterResponse(recruiter.getId(),
                    recruiter.getFirstname(), recruiter.getJobRole());

            returnList.add(assign);
        }
        return returnList;
    }

    public List<UserAppliedJobstoRecruiterDto> getListOfUsersAppliedToJob(long jobId) {

        List<UserAppliedJobstoRecruiterDto> list = new ArrayList<>();
        Optional<JobsApplication> op = jobRepository.findById(jobId);
        List<User> userList = op.get().getUsersApplied();

        for (User user : userList){
            UserAppliedJobstoRecruiterDto userApplied = new UserAppliedJobstoRecruiterDto();
            userApplied.setUserId(user.getUserId());
            userApplied.setFirstName(user.getFirstName());
            userApplied.setExperienceInYears(user.getExperienceInYears());
            userApplied.setCurrentJobTitle(user.getCurrentJobTitle());

            list.add(userApplied);
        }
        return list;
    }

    // recruiter can change the status of user like [commenter | reviewer | all actions ]

}

