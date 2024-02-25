package com.tool.RecruitXpert.Security.EmailSender;

import lombok.Data;

@Data
public class Email {
    private String recipient;
    private String subject;
    private String message;
}
