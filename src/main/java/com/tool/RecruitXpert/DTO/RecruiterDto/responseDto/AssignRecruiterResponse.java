package com.tool.RecruitXpert.DTO.RecruiterDto.responseDto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;

@Data @AllArgsConstructor @NoArgsConstructor
public class AssignRecruiterResponse {

    private int id;
    private String name;
    private String jobRole;

}
