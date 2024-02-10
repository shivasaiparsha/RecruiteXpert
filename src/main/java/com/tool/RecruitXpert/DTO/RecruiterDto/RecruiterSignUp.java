package com.tool.RecruitXpert.DTO.RecruiterDto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @AllArgsConstructor @NoArgsConstructor
public class RecruiterSignUp {

    private String email;
    private String organisationName;
    private String password;

}
