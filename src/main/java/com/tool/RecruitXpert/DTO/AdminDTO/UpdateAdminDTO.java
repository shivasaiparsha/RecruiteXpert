package com.tool.RecruitXpert.DTO.AdminDTO;

import lombok.*;
import lombok.experimental.FieldDefaults;

@FieldDefaults(level = AccessLevel.PRIVATE)
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UpdateAdminDTO {

    long adminId;

    String firstname;

    String lastname;

    String address;

    String website;

    String adminImg;

    String companyName;

}
