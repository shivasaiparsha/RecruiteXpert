package com.tool.RecruitXpert.Service;


import com.tool.RecruitXpert.DTO.AdminDTO.AdminRequest;
import com.tool.RecruitXpert.DTO.AdminDTO.AdminResponse;
import com.tool.RecruitXpert.Exceptions.AdminNotFoundException;
import com.tool.RecruitXpert.Repository.AdminRepository;
import com.tool.RecruitXpert.Transformer.AdminTransformer;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;

public class AdminService {
    @Autowired
    private AdminRepository adminRepository;
    public AdminResponse addAdmin(AdminRequest adminRequest) {

        Admin admin = AdminTransformer.AdminRequestToAdmin(adminRequest);

        Admin savedAdmin = adminRepository.save(admin);

        return AdminTransformer.AdminToAdminResponse(savedAdmin);
    }

    public String updateAdmin(AdminRequest adminRequest) {

        Optional<Admin> optionalAdmin = adminRepository.findByEmail(adminRequest.getEmail());
        if(optionalAdmin.isPresent()==false){
            throw new AdminNotFoundException("Admin not Found");
        }

        Admin admin = AdminTransformer.AdminRequestToAdmin(adminRequest);

        Admin savedAdmin = adminRepository.save(admin);


        return "Successfully Updated !!";
    }

    public String deleteAdmin(Long id) {
        Optional<Admin> optionalAdmin = adminRepository.findById(id);
        if(optionalAdmin.isPresent()==false){
            throw new AdminNotFoundException("Admin not Found");
        }
        adminRepository.deleteById(id);
        return "Successfully Deleted";
    }
}
