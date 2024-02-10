package com.tool.RecruitXpert.DTO.UserDTO;

import com.tool.RecruitXpert.Enums.Status;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@NoArgsConstructor
@AllArgsConstructor
@Data
public class UpdateUserStatus {

    int id;
    Status status;

}
