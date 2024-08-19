package com.sagar.lahade.sci.calculator.service;

import com.sagar.lahade.sci.calculator.model.UserInfo;
import com.sagar.lahade.sci.calculator.repository.UserInfoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserInfoRepository userInfoRepository;

    public UserInfo registerUser(String username, String password) {
        UserInfo user = new UserInfo();
        user.setUsername(username);
        user.setPassword(password); // In a real application, use password encoding
        return userInfoRepository.save(user);
    }

    public UserInfo authenticateUser(String username, String password) {
        UserInfo user = userInfoRepository.findByUsername(username);
        if (user != null && user.getPassword().equals(password)) {
            return user;
        }
        return null;
    }
}
