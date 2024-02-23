package com.tool.RecruitXpert.DTO.JobDTO;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Autowired;

@Data @AllArgsConstructor @NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)

public class UpdateJobDto {
    String job_role;
    int vacancy;
    String jobDescription;
    String CTC;
    String location;
}
