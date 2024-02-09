package com.tool.RecruitXpert.DTO.AdminDTO;

import lombok.*;
import lombok.experimental.FieldDefaults;

@FieldDefaults(level = AccessLevel.PRIVATE)
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AdminResponse {

    String firstname;

    String lastname;

    String email;
}
