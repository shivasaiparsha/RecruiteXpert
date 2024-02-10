package com.tool.RecruitXpert.DTO.AdminDTO;

import jakarta.persistence.Column;
import jakarta.persistence.UniqueConstraint;
import lombok.*;
import lombok.experimental.FieldDefaults;

@FieldDefaults(level = AccessLevel.PRIVATE)
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AdminSignUp {

    String email;

    String organization;

    String password;
}
