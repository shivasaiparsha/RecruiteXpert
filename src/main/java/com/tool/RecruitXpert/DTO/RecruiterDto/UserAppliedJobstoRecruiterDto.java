package com.tool.RecruitXpert.DTO.RecruiterDto;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;


@FieldDefaults(level= AccessLevel.PRIVATE)
@Data
@AllArgsConstructor @NoArgsConstructor
public class UserAppliedJobstoRecruiterDto {

    int userId;
    String firstName;
    int experienceInYears;
    String currentJobTitle;

}
