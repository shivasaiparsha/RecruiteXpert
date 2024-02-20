package com.tool.RecruitXpert.Security;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor @ToString
@Builder
public class JwtResponse {
    private String jwtToken;
    private String name;
}
