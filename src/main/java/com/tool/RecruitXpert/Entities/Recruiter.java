package com.tool.RecruitXpert.Entities;

import com.tool.RecruitXpert.Enums.ActivateRecruiter;
import com.tool.RecruitXpert.Enums.RecruiterPermission;
import com.tool.RecruitXpert.Enums.Status;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

import java.util.ArrayList;
import java.util.List;


@FieldDefaults(level= AccessLevel.PACKAGE)
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
@Table
public class Recruiter {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;

    String firstname;

    String lastname;

    @Column(unique = true, nullable = false)
    String email;

    String password;

    String recruiterImg;

    String jobRole;




    @Enumerated(value = EnumType.STRING)
    Status recruiterStatus; // approved | dis-approve = cannot access |

    // this comes under manage part
    @Enumerated(value = EnumType.STRING)
    ActivateRecruiter activateRecruiter; // active | de-active

    // mapped for user to recruiter
    @OneToMany(mappedBy = "recruiter", cascade = CascadeType.ALL)
    private List<User> userList = new ArrayList<>();


}
