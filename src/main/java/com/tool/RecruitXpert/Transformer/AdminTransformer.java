package com.tool.RecruitXpert.Transformer;

import com.tool.RecruitXpert.DTO.AdminDTO.AdminRequest;
import com.tool.RecruitXpert.DTO.AdminDTO.AdminResponse;
import com.tool.RecruitXpert.Entities.Admin;

public class AdminTransformer {

    public static Admin AdminRequestToAdmin(AdminRequest adminRequest){
        return Admin.builder()
                .firstname(adminRequest.getFirstname())
                .lastname(adminRequest.getLastname())
                .email(adminRequest.getEmail())
                .password(adminRequest.getPassword())
                .adminRole(adminRequest.getRole())
                .adminImg(adminRequest.getAdminImg())
                .address(adminRequest.getAddress())
                .location(adminRequest.getLocation())
                .companyName(adminRequest.getCompanyName())
                .website(adminRequest.getWebsite())
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
