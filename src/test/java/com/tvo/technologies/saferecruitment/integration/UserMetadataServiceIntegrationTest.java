package com.tvo.technologies.saferecruitment.integration;

import com.tvo.technologies.saferecruitment.exception.*;
import com.tvo.technologies.saferecruitment.integration.config.repository.inmemory.TestInMemoryUserMetadataRepository;
import com.tvo.technologies.saferecruitment.model.user.EducationLevel;
import com.tvo.technologies.saferecruitment.model.user.UserMetadata;
import com.tvo.technologies.saferecruitment.service.UserMetadataService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.jupiter.api.Assertions.*;

public class UserMetadataServiceIntegrationTest extends AbstractServiceTest {
    private static final String PRESENT_ID = "1";
    private static final String NOT_PRESENT_ID = "-1";

    @Autowired
    private TestInMemoryUserMetadataRepository userRepository;

    @Autowired
    private UserMetadataService userMetadataService;

    @AfterEach
    public void cleanUp() {
        userRepository.clear();
    }

    @Test
    void should_get_user_by_id() {
        UserMetadata expectedUserMetadata = new UserMetadata("blablabla@gmail.com");
        String newId = userRepository.addNewUser(expectedUserMetadata);

        UserMetadata actualUserMetadata = userMetadataService.getUser(newId);

        assertEquals(expectedUserMetadata, actualUserMetadata);
    }

    @Test
    void should_throw_exception_if_user_id_is_null() {
        assertThrows(InvalidUserIdException.class, () -> userMetadataService.getUser(null));
    }

    @ParameterizedTest
    @ValueSource(strings = {"", " ", "      "})
    void should_throw_exception_if_user_id_is_empty(String emptyId) {
        assertThrows(InvalidUserIdException.class, () -> userMetadataService.getUser(emptyId));
    }

    @Test
    void should_throw_exception_if_user_id_is_wrong() {
        UserMetadata expectedUserMetadata = new UserMetadata("blablabla@gmail.com");
        userRepository.addNewUser(expectedUserMetadata);

        assertThrows(UserNotFoundException.class, () -> userMetadataService.getUser(NOT_PRESENT_ID));
    }

    @Test
    void should_update_user_info() {
        UserMetadata userMetadata = new UserMetadata(
                "Nick",
                "Ray",
                5,
                EducationLevel.BACHELOR,
                "Senior Java Developer",
                "Quick learner. Worked in fintech companies for 3 years"
        );

        String newId = userRepository.addNewUser(userMetadata);
        userMetadata.setName("Vlad");

        boolean isUpdated = userMetadataService.updateUser(newId, userMetadata);

        assertTrue(isUpdated);
    }

    @Test
    void should_not_update_user_info_if_user_id_is_null() {
        UserMetadata userMetadata = new UserMetadata(
                "Nick",
                "Ray",
                5,
                EducationLevel.BACHELOR,
                "Senior Java Developer",
                "Quick learner. Worked in fintech companies for 3 years"
        );

        assertThrows(InvalidUserIdException.class, () -> userMetadataService.updateUser(null, userMetadata));
    }

    @Test
    void should_not_update_user_info_if_user_name_is_null() {
        UserMetadata userMetadata = new UserMetadata(
                null,
                "Ray",
                5,
                EducationLevel.BACHELOR,
                "Senior Java Developer",
                "Quick learner. Worked in fintech companies for 3 years"
        );

        assertThrows(UpdateUserFailedException.class, () -> userMetadataService.updateUser(PRESENT_ID, userMetadata));
    }

    @Test
    void should_not_update_user_info_if_user_surname_is_null() {
        UserMetadata userMetadata = new UserMetadata(
                "Nick",
                null,
                5,
                EducationLevel.BACHELOR,
                "Senior Java Developer",
                "Quick learner. Worked in fintech companies for 3 years"
        );

        assertThrows(UpdateUserFailedException.class, () -> userMetadataService.updateUser(PRESENT_ID, userMetadata));
    }

    @Test
    void should_not_update_user_info_if_user_education_is_null() {
        UserMetadata userMetadata = new UserMetadata(
                "Nick",
                "Ray",
                5,
                null,
                "Senior Java Developer",
                "Quick learner. Worked in fintech companies for 3 years"
        );

        assertThrows(UpdateUserFailedException.class, () -> userMetadataService.updateUser(PRESENT_ID, userMetadata));
    }

    @Test
    void should_not_update_user_info_if_user_target_position_is_null() {
        UserMetadata userMetadata = new UserMetadata(
                "Nick",
                "Ray",
                5,
                EducationLevel.BACHELOR,
                null,
                "Quick learner. Worked in fintech companies for 3 years"
        );

        assertThrows(UpdateUserFailedException.class, () -> userMetadataService.updateUser(PRESENT_ID, userMetadata));
    }

    @Test
    void should_not_update_user_info_if_user_additional_information_is_null() {
        UserMetadata userMetadata = new UserMetadata(
                "Nick",
                "Ray",
                5,
                null,
                "Senior Java Developer",
                null
        );

        assertThrows(UpdateUserFailedException.class, () -> userMetadataService.updateUser(PRESENT_ID, userMetadata));
    }


    @Test
    void should_not_update_user_info_if_user_is_invalid() {
        assertThrows(UpdateUserFailedException.class, () -> userMetadataService.updateUser(PRESENT_ID, null));
    }

    @Test
    void should_add_new_user() {
        UserMetadata userMetadata = new UserMetadata("tvotech@mail.com");

        String newId = userRepository.addNewUser(userMetadata);
        UserMetadata actualUserMetadata = userMetadataService.getUser(newId);

        assertNotNull(actualUserMetadata);
        assertEquals(userMetadata, actualUserMetadata);
    }

    @Test
    void should_throw_exception_if_user_exists() {
        UserMetadata userMetadata = new UserMetadata("tvotech@mail.com");

        userRepository.addNewUser(userMetadata);

        assertThrows(UserAlreadyExistsException.class, () -> userMetadataService.addNewUser(userMetadata));
    }

    @Test
    void should_throw_exception_if_user_is_invalid() {
        assertThrows(InvalidUserException.class, () -> userMetadataService.addNewUser(null));
    }

    @Test
    void should_throw_exception_if_user_email_is_invalid() {
        UserMetadata userMetadata = new UserMetadata(null);

        userRepository.addNewUser(userMetadata);

        assertThrows(InvalidUserException.class, () -> userMetadataService.addNewUser(userMetadata));
    }
}
