package com.tool.RecruitXpert.DTO.AdminDTO;

import com.tool.RecruitXpert.Enums.Status;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

@Data @NoArgsConstructor @AllArgsConstructor @FieldDefaults(level = AccessLevel.PRIVATE)
public class UpdateRecruiterStatus {

    int id;
    Status recruiterStatus;
}
