package com.tvo.technologies.saferecruitment.repository;

import com.tvo.technologies.saferecruitment.model.user.User;

public interface UserRepository {

    User getUser(String id);

    boolean updateUser(String id, User user);

    boolean addNewUser(User user);
}
