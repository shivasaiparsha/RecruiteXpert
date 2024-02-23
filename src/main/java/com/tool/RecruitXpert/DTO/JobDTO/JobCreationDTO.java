package com.tool.RecruitXpert.DTO.JobDTO;

import com.tool.RecruitXpert.Enums.JobLocation;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

@FieldDefaults(level = AccessLevel.PRIVATE)
@Data
@AllArgsConstructor
@NoArgsConstructor

public class JobCreationDTO {

    String jobTitle;
    int vacancy;
    String jobDescription;
    double experience;
    long CTC;
    JobLocation location;

}
