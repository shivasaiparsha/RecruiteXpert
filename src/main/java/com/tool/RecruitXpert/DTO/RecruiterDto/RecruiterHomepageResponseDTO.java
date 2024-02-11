package com.tool.RecruitXpert.DTO.RecruiterDto;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.sql.Date;
@FieldDefaults(level = AccessLevel.PRIVATE)
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RecruiterHomepageResponseDTO {

    String recruiterName;
    byte[] recruiterImg;
    String recruiterRole;
    String recruiterLocation;
    Date recruiterDate;
    String adminName;
    byte[] adminImg;
    String adminRole;
    Date adminDate;
}
