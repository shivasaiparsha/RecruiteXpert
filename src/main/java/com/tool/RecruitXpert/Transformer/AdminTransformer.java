package com.tool.RecruitXpert.Transformer;

import com.tool.RecruitXpert.DTO.AdminDTO.FormAdminDTO;
import com.tool.RecruitXpert.DTO.AdminDTO.AdminResponse;
import com.tool.RecruitXpert.Entities.Admin;

import java.io.IOException;

public class AdminTransformer {

    public static Admin AdminRequestToAdmin(FormAdminDTO formAdminDTO) throws IOException {
        return Admin.builder()
                .firstname(formAdminDTO.getFirstname())
                .adminRole(formAdminDTO.getRole())
                .adminImg(formAdminDTO.getAdminImg().getBytes())
                .companyName(formAdminDTO.getCompanyName())
                .website(formAdminDTO.getWebsite())
                .adminRole((formAdminDTO.getRole()))
                .build();
    }

    public static AdminResponse AdminToAdminResponse(Admin admin){
        return AdminResponse.builder()
                .firstname(admin.getFirstname())
                .lastname(admin.getLastname())
                .email(admin.getEmail())
                .build();
    }
}