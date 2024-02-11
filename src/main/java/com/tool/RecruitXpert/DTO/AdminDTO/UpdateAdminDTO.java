package com.tool.RecruitXpert.DTO.AdminDTO;

import lombok.*;
import lombok.experimental.FieldDefaults;
import org.springframework.web.multipart.MultipartFile;

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

    MultipartFile adminImg;

//    String adminImg;

    String companyName;

}
