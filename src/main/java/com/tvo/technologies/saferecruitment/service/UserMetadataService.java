package com.tvo.technologies.saferecruitment.service;

import com.tvo.technologies.saferecruitment.exception.*;
import com.tvo.technologies.saferecruitment.model.user.UserMetadata;
import com.tvo.technologies.saferecruitment.repository.UserMetadataRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserMetadataService {

    private final UserMetadataRepository userMetadataRepository;

    public UserMetadata getUser(String id) {
        if (Objects.isNull(id) || id.isBlank()) {
            log.error("User does not exists, or id field is empty");
            throw new InvalidUserIdException("The user id is invalid");
        }

        log.info("Getting user by id");
        return userMetadataRepository.getUser(id);
    }

    public boolean addNewUser(UserMetadata userMetadata) {
        if (isInvalidForCreation(userMetadata)) {
            log.error("Some of user's params are null");
            throw new InvalidUserException("User is invalid for creation");
        }

        log.info("Adding new user");
        userMetadataRepository.addNewUser(userMetadata);

        return true;
    }

    private boolean isInvalidForCreation(UserMetadata userMetadata) {
        return Objects.isNull(userMetadata)
                || Objects.isNull(userMetadata.getEmail());
    }

    public boolean updateUser(String id, UserMetadata userMetadata) {
        if (Objects.isNull(id) || id.isBlank()) {
            log.error("User does not exists, or id field is empty");
            throw new InvalidUserIdException("The user id is invalid");
        }

        if (isInvalidForUpdate(userMetadata)) {
            log.error("Some of user's params are null");
            throw new UpdateUserFailedException("User is invalid");
        }

        log.info("Updating user");
        return userMetadataRepository.updateUser(id, userMetadata);
    }

    private boolean isInvalidForUpdate(UserMetadata userMetadata) {
        return Objects.isNull(userMetadata)
                || Objects.isNull(userMetadata.getName())
                || Objects.isNull(userMetadata.getSurname())
                || Objects.isNull(userMetadata.getEducation())
                || Objects.isNull(userMetadata.getTargetPosition())
                || Objects.isNull(userMetadata.getAdditionalInformation());
    }
}
