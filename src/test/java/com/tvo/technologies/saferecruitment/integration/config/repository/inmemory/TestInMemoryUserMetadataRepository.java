package com.tvo.technologies.saferecruitment.integration.config.repository.inmemory;

import com.tvo.technologies.saferecruitment.exception.UserAlreadyExistsException;
import com.tvo.technologies.saferecruitment.exception.UserNotFoundException;
import com.tvo.technologies.saferecruitment.model.user.UserMetadata;
import com.tvo.technologies.saferecruitment.repository.UserMetadataRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class TestInMemoryUserMetadataRepository implements UserMetadataRepository {
    private final List<UserMetadata> allUserMetadata = new ArrayList<>();

    public UserMetadata getUser(String id) {
        return allUserMetadata.stream()
                .filter(user -> user.getId().equals(id))
                .findAny()
                .orElseThrow(() -> new UserNotFoundException("User with id %s was not found".formatted(id)));
    }

    public boolean updateUser(String id, UserMetadata userMetadata) {
        UserMetadata currentUserMetadata = getUser(id);
        allUserMetadata.remove(currentUserMetadata);

        return allUserMetadata.add(userMetadata);
    }


    public String addNewUser(UserMetadata userMetadata) {
        boolean emailIsAlreadyPresent = allUserMetadata.stream()
                .map(UserMetadata::getEmail)
                .anyMatch(email -> email.equals(userMetadata.getEmail()));

        if (emailIsAlreadyPresent) {
            throw new UserAlreadyExistsException("The user is already exists");
        }

        String newId = UUID.randomUUID().toString();
        allUserMetadata.add(userMetadata.withId(newId));

        return newId;
    }

    public void clear() {
        allUserMetadata.clear();
    }
}
