package com.tool.RecruitXpert.Security;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;


@Data
@AllArgsConstructor
@NoArgsConstructor @ToString
public class JwtRequest {
    private String email;
    private String password;
}
