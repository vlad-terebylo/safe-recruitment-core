package com.tvo.technologies.saferecruitment.integration.config.repository;

import com.tvo.technologies.saferecruitment.model.user.User;

public class TestUserRepository {
    public User getUser(String id) {
        return null;
    }

    public boolean updateUser(String id, User user) {
        return false;
    }

    public boolean changePsswd(String id, String psswd) {
        return false;
    }

    public boolean addNewUser(User user) {
        return false;
    }
}
