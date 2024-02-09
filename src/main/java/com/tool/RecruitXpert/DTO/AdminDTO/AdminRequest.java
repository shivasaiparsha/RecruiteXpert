package com.tool.RecruitXpert.DTO.AdminDTO;

import lombok.*;
import lombok.experimental.FieldDefaults;

@FieldDefaults(level = AccessLevel.PRIVATE)
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AdminRequest {

    String firstname;

    String lastname;

    String email;

    String password;

    String address;

    String location;

    String website;

    String adminImg;

    String companyName;

    String role;
}
