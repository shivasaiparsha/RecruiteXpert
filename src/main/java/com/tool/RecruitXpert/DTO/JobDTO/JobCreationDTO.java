package com.tool.RecruitXpert.DTO.JobDTO;

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
    String jobrole;
    int vacancies;
    String jobdescription;
    String ctc;
    String location;
}
