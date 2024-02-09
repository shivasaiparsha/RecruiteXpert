package com.tool.RecruitXpert.DTO;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class AddRecruiterDto {
    String firstname;

    String lastname;

    @Column(unique = true, nullable = false)
    String email;

    String password;

//    String recruiterImg;

//    String recruiterPermission; // approve | comment | reviewer

    String jobRole;

}
