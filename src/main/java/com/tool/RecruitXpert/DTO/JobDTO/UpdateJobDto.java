package com.tool.RecruitXpert.DTO.JobDTO;

import com.tool.RecruitXpert.Enums.JobLocation;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Autowired;

@Data @AllArgsConstructor @NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)

public class UpdateJobDto {

    Long jobId; // we're getting the job obj from here

    String jobTitle;
    int vacancy;
    String jobDescription;
    double experience;
    long CTC;
    String location;
}
