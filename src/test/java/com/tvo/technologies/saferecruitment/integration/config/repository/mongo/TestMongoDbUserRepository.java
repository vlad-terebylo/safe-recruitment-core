package com.tvo.technologies.saferecruitment.integration.config.repository.mongo;

import com.tvo.technologies.saferecruitment.exception.UserAlreadyExistsException;
import com.tvo.technologies.saferecruitment.exception.UserNotFoundException;
import com.tvo.technologies.saferecruitment.model.user.User;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class TestMongoDbUserRepository {
    private final List<User> allUsers = new ArrayList<>();

    public User getUser(String id) {
        return allUsers.stream()
                .filter(user -> user.getId().equals(id))
                .findAny()
                .orElseThrow(() -> new UserNotFoundException("User with id %s was not found".formatted(id)));
    }

    public boolean updateUser(String id, User user) {
        User currentUser = getUser(id);
        allUsers.remove(currentUser);

        return allUsers.add(user);
    }


    public boolean addNewUser(User user) {
        boolean emailIsAlreadyPresent = allUsers.stream()
                .map(User::getEmail)
                .anyMatch(email -> email.equals(user.getEmail()));

        if (emailIsAlreadyPresent) {
            throw new UserAlreadyExistsException("The user is already exists");
        }

        String newId = UUID.randomUUID().toString();
        return allUsers.add(user.withId(newId));
    }

    public void clear() {
        allUsers.clear();
    }
}
