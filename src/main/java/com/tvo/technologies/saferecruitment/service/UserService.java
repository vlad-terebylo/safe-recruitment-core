package com.tvo.technologies.saferecruitment.service;

import com.tvo.technologies.saferecruitment.model.user.User;
import com.tvo.technologies.saferecruitment.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

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
