package com.tool.RecruitXpert.DTO.RecruiterDto;

import com.tool.RecruitXpert.Enums.Status;
import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateRecruiterDto {

    int id;

    String firstname;

    String lastname;

    String email;

    String password;

    String recruiterImg;

    String jobRole;
}
