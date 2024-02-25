package com.tool.RecruitXpert.DTO.AdminDTO.ResponseDto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GetAdminUpdateProfile {

    String username;
    String expectedCTC;
    String expectedJobRole;
    String location;

    byte[] adminImg;

}
