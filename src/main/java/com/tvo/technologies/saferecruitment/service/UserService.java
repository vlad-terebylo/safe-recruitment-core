package com.tvo.technologies.saferecruitment.service;

import com.tvo.technologies.saferecruitment.model.user.User;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    public User getUser(String id) {
        return null;
    }

    public boolean updateUser(String id, User user) {
        return false;
    }

    public boolean changePassword(String id, User user) {
        return false;
    }

    public boolean addNewUser(User user) {
        return false;
    }
}
