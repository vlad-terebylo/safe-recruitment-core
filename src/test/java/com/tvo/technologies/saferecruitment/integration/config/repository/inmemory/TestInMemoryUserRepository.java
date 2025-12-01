package com.tvo.technologies.saferecruitment.integration.config.repository.inmemory;

import com.tvo.technologies.saferecruitment.exception.UserAlreadyExistsException;
import com.tvo.technologies.saferecruitment.exception.UserNotFoundException;
import com.tvo.technologies.saferecruitment.model.user.User;
import com.tvo.technologies.saferecruitment.repository.UserRepository;
import com.tvo.technologies.saferecruitment.repository.inmemory.InMemoryUserRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class TestInMemoryUserRepository implements UserRepository {
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


    public String addNewUser(User user) {
        boolean emailIsAlreadyPresent = allUsers.stream()
                .map(User::getEmail)
                .anyMatch(email -> email.equals(user.getEmail()));

        if (emailIsAlreadyPresent) {
            throw new UserAlreadyExistsException("The user is already exists");
        }

        String newId = UUID.randomUUID().toString();
        allUsers.add(user.withId(newId));

        return newId;
    }

    public void clear() {
        allUsers.clear();
    }
}
