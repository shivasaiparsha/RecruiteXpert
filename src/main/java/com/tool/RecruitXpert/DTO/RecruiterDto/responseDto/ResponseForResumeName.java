package com.tool.RecruitXpert.DTO.RecruiterDto.responseDto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @NoArgsConstructor
public class ResponseForResumeName {
    int id;
    String resumeName;

    public ResponseForResumeName(int id, String resumeName) {
        this.id = id;
        this.resumeName = resumeName;
    }
}
