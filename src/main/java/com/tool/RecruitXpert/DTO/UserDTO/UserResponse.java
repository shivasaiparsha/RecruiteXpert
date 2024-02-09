package com.tool.RecruitXpert.DTO.UserDTO;

import lombok.*;
import lombok.experimental.FieldDefaults;

@FieldDefaults(level = AccessLevel.PRIVATE)
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class UserResponse {

    String firstName;

    String lastName;

    String mobileNo;

    String email;
}
