package com.tool.RecruitXpert.Service;


import com.tool.RecruitXpert.DTO.AdminDTO.AdminRequest;
import com.tool.RecruitXpert.DTO.AdminDTO.AdminResponse;
import com.tool.RecruitXpert.DTO.AdminDTO.UpdateAdminDTO;
import com.tool.RecruitXpert.Entities.Admin;
import com.tool.RecruitXpert.Exceptions.AdminNotFoundException;
import com.tool.RecruitXpert.Repository.AdminRepository;
import com.tool.RecruitXpert.Transformer.AdminTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
@Service
public class AdminService {
    @Autowired
    private AdminRepository adminRepository;
    public AdminResponse addAdmin(AdminRequest adminRequest) {

        Admin admin = AdminTransformer.AdminRequestToAdmin(adminRequest);
        Admin savedAdmin = adminRepository.save(admin);
        return AdminTransformer.AdminToAdminResponse(savedAdmin);
    }

    public String updateAdmin(UpdateAdminDTO adminRequest) {

        Optional<Admin> optionalAdmin = adminRepository.findById(adminRequest.getAdminId());

        if(!optionalAdmin.isPresent())
            throw new AdminNotFoundException("Enter correct ID, Admin not Found");

        Admin admin = optionalAdmin.get();
        admin.setFirstname(adminRequest.getFirstname());
        admin.setLastname(adminRequest.getLastname());
        admin.setAddress(adminRequest.getAddress());
        admin.setWebsite(adminRequest.getWebsite());
        admin.setAdminImg(adminRequest.getAdminImg());
        admin.setCompanyName(adminRequest.getCompanyName());

        adminRepository.save(admin);
        return "Successfully Updated !";
    }

    public String deleteAdmin(Long id) {
        Optional<Admin> optionalAdmin = adminRepository.findById(id);
        if(!optionalAdmin.isPresent()){
            throw new AdminNotFoundException("Admin not Found");
        }
        adminRepository.deleteById(id);
        return "Successfully Deleted";
    }
}
