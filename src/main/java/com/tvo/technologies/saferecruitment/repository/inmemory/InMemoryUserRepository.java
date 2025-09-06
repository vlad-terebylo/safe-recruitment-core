package com.tvo.technologies.saferecruitment.repository.inmemory;

import com.tvo.technologies.saferecruitment.model.user.User;
import com.tvo.technologies.saferecruitment.repository.UserRepository;

public class InMemoryUserRepository implements UserRepository {
    @Override
    public User getUser(String id) {
        return null;
    }

    @Override
    public boolean updateUser(String id, User user) {
        return false;
    }

    @Override
    public boolean changePassword(String id, User user) {
        return false;
    }

    @Override
    public boolean addNewUser(User user) {
        return false;
    }
}
