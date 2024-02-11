package com.tool.RecruitXpert.DTO.RecruiterDto;

import com.tool.RecruitXpert.Enums.Status;
import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateRecruiterDto {

    int id;

    String firstname;

    String lastname;

    String email;

    String password;

    MultipartFile recruiterImg;

    String jobRole;
}
