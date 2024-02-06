package com.tool.RecruitXpert.Entities;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@FieldDefaults(level= AccessLevel.PRIVATE)
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data @Table
public class Admin {

    // HERE ADMIN HAS ACCESS TO USERS SO DON'T WANT TO HAVE MAPPINGS HERE
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long AdminId;

    String firstname;

    String lastname;

    @Column(unique = true, nullable = false)
    String email;

    String password;

    // we've to check if this is admin-role
    // then only we'll provide all the admin access

    String website;
    String adminImg;
    String companyName;
    String adminRole;

    @CreationTimestamp
    Date createdDate;

    @UpdateTimestamp
    Date lastActive;

    String address;

    String location;

    // admin has job list
    @OneToMany(mappedBy = "admin", cascade = CascadeType.ALL)
    private List<Jobs> jobsList = new ArrayList<>();

}
