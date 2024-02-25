package com.tool.RecruitXpert.DTO.AdminDTO;

import jakarta.persistence.Lob;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.springframework.web.multipart.MultipartFile;

@FieldDefaults(level = AccessLevel.PRIVATE)
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class FormAdminDTO {

    long adminId;

    String firstname;

    String expectedCTC;

    String website;

    MultipartFile adminImg;

//    String adminImg;

    String companyName;

    String role;

}
