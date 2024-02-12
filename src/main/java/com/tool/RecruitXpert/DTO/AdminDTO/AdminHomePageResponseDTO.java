package com.tool.RecruitXpert.DTO.AdminDTO;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.sql.Date;
    @FieldDefaults(level = AccessLevel.PRIVATE)
    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor

    public class AdminHomePageResponseDTO {

        String adminName;

        byte[] adminImg;

        String adminRole;

        Date adminDate;

        String location;

        String website;

        String companyName;
    }

