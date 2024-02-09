package com.tool.RecruitXpert.DTO.UserDTO;

import lombok.*;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.UpdateTimestamp;

import java.util.Date;

@FieldDefaults(level = AccessLevel.PRIVATE)
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class UserRequest {

    String firstName;

    String lastName;

    String mobileNo;

    String email;

    String password;

    int experienceInYears;

    int relevantExp;

    String highestQualification;

    String  skillSet;

    String currentOrg;

    String currentJobTitle;

    String location;

    String city;

    String zipCode;

    @UpdateTimestamp
    Date lastActiveDate;
}
