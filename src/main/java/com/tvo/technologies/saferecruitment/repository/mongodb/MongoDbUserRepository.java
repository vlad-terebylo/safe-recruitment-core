package com.tvo.technologies.saferecruitment.repository.mongodb;

import com.tvo.technologies.saferecruitment.model.user.User;
import com.tvo.technologies.saferecruitment.repository.UserRepository;

public class MongoDbUserRepository implements UserRepository {
    @Override
    public User getUser(String id) {
        return null;
    }

    @Override
    public boolean updateUser(String id, User user) {
        return false;
    }

    @Override
    public boolean addNewUser(User user) {
        return false;
    }
}
