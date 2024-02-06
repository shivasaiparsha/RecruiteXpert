package com.tool.RecruitXpert.Entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.util.Date;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
public class ResumeEntity {

    int resumeId;

    @CreationTimestamp
    Date createdDate;

    // UI se aa jayega wo as DTO in post req of resume
    String docType;

    String resumeImg;

    String comment;

    // condition : resume should be upload less than 4 times total.
    @Lob  @Column(nullable = false)
    byte[] resume;


    @ManyToOne
    @JoinColumn
    private User userToResume;
}
