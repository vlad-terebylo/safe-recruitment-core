package com.tvo.technologies.saferecruitment.service;

import com.tvo.technologies.saferecruitment.exception.*;
import com.tvo.technologies.saferecruitment.model.user.User;
import com.tvo.technologies.saferecruitment.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public User getUser(String id) {
        if (Objects.isNull(id) || id.isEmpty()) {
            log.error("User does not exists, or id field is empty");
            throw new InvalidUserIdException("The user id is invalid");
        }

        log.info("Getting user by id");
        return userRepository.getUser(id);
    }

    public boolean addNewUser(User user) {
        if (isInvalidForCreate(user)) {
            log.error("Some of user's params are null");
            throw new InvalidUserException("User is invalid for creation");
        }

        log.info("Adding new user");
        userRepository.addNewUser(user);

        return true;
    }

    private boolean isInvalidForCreate(User user) {
        return Objects.isNull(user)
                || Objects.isNull(user.getEmail())
                || Objects.isNull(user.getPassword());
    }

    public boolean updateUser(String id, User user) {
        if (Objects.isNull(id) || id.isEmpty()) {
            log.error("User does not exists, or id field is empty");
            throw new InvalidUserIdException("The user id is invalid");
        }

        if (isInvalidForUpdate(user)) {
            log.error("Some of user's params are null");
            throw new UpdateUserFailedException("User is invalid");
        }

        log.info("Updating user");
        return userRepository.updateUser(id, user);
    }

    private boolean isInvalidForUpdate(User user) {
        return Objects.isNull(user)
                || Objects.isNull(user.getName())
                || Objects.isNull(user.getSurname())
                || Objects.isNull(user.getEducation())
                || Objects.isNull(user.getTargetPosition())
                || Objects.isNull(user.getAdditionalInformation());
    }

    public boolean changePsswd(String id, String psswd) {
        User user = userRepository.getUser(id);

        if (user.getPassword().equals(psswd)) {
            log.error("The new password must not be same as the old one");
            throw new ChangingPasswordFailedException("The password is invalid");
        }

        log.info("Changing password");
        return userRepository.updateUser(id, user.withPassword(psswd));
    }
}
