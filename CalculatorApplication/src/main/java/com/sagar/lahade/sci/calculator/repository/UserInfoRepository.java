package com.sagar.lahade.sci.calculator.repository;

import com.sagar.lahade.sci.calculator.model.UserInfo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserInfoRepository extends JpaRepository<UserInfo, Long> {
    UserInfo findByUsername(String username);
}
 