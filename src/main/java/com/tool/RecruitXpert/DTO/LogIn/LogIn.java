package com.tool.RecruitXpert.DTO.LogIn;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@FieldDefaults(level = AccessLevel.PRIVATE)
@Data
public class LogIn {
    String email;
    String password;
}
