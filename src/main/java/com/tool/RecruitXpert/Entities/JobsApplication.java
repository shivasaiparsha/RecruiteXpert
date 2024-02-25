package com.tool.RecruitXpert.Entities;

import com.tool.RecruitXpert.Enums.JobCategories;
import com.tool.RecruitXpert.Enums.JobLocation;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.CreationTimestamp;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@FieldDefaults(level= AccessLevel.PRIVATE)
@Entity
@NoArgsConstructor
@AllArgsConstructor @Data @Table
@Builder
public class JobsApplication {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long jobid;

    String jobTitle; // enter the job title ex : software dev

//    // set this each time when we set the job
//    @Enumerated(EnumType.STRING)
//    JobCategories jobCategory; // frontEnd backend, ML

    @Column(name = "description")
    String jobDescription;

    double experience;

    long CTC;

    String location;

    int vacancies;

    @CreationTimestamp
    Date lastDate;

    // many-to-many to user - jobs application
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "user_jobs",
            joinColumns = @JoinColumn(name = "job_id"),
            inverseJoinColumns = @JoinColumn(name = "user_id"))
    List<User> usersApplied = new ArrayList<>();


    @OneToOne @JoinColumn
    Recruiter recruiter;

    public JobsApplication(String jobTitle, String jobDescription, double experience, long CTC, String location, int vacancies) {
        this.jobTitle = jobTitle;
        this.jobDescription = jobDescription;
        this.experience = experience;
        this.CTC = CTC;
        this.location = location;
        this.vacancies = vacancies;
    }
}
