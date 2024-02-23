package com.tool.RecruitXpert.DTO.UserDTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @AllArgsConstructor @NoArgsConstructor
public class JobApplyDto {
    private int userId;
    private long jobId;
}
