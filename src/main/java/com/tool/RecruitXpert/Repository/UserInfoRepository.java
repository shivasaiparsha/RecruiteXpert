package com.tool.RecruitXpert.Repository;


import com.tool.RecruitXpert.Entities.UserInfo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserInfoRepository extends JpaRepository<UserInfo, Integer> {
    Optional<UserInfo> findByName(String username);


}
