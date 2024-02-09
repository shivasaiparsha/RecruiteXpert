package com.tool.RecruitXpert.DTO.SignUp;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@FieldDefaults(level = AccessLevel.PRIVATE)
@Data
public class UserSignUp {
    String name;
    String username;
    String email;
    String password;
}
