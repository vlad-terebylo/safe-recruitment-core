package com.tvo.technologies.saferecruitment.integration;

import com.tvo.technologies.saferecruitment.exception.*;
import com.tvo.technologies.saferecruitment.integration.config.repository.inmemory.TestInMemoryUserRepository;
import com.tvo.technologies.saferecruitment.model.user.EducationLevel;
import com.tvo.technologies.saferecruitment.model.user.User;
import com.tvo.technologies.saferecruitment.service.UserService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Objects;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class UserServiceIntegrationTest extends AbstractServiceTest {
    private static final String PRESENT_ID = "1";
    private static final String NOT_PRESENT_ID = "-1";

    @Autowired
    private TestInMemoryUserRepository userRepository;

    @Autowired
    private UserService userService;

    @AfterEach
    public void cleanUp() {
        userRepository.clear();
    }

    @Test
    void should_get_user_by_id() {
        User expectedUser = new User("blablabla@gmail.com", "putinchujlolalalalalala");
        String newId = userRepository.addNewUser(expectedUser);

        User actualUser = userService.getUser(newId);

        assertEquals(expectedUser, actualUser);
    }

    @Test
    void should_throw_exception_if_user_id_is_null() {
        assertThrows(InvalidUserIdException.class, () -> userService.getUser(null));
    }

    @Test
    void should_throw_exception_if_user_id_is_wrong() {
        User expectedUser = new User("blablabla@gmail.com", "putinchujlolalalalalala");
        userRepository.addNewUser(expectedUser);

        assertThrows(UserNotFoundException.class, () -> userService.getUser(NOT_PRESENT_ID));
    }

    @Test
    void should_update_user_info() {
        User user = new User(
                "Nick",
                "Ray",
                5,
                EducationLevel.BACHELOR,
                "Senior Java Developer",
                "Quick learner. Worked in fintech companies for 3 years"
        );

        String newId = userRepository.addNewUser(user);
        user.setName("Vlad");

        boolean isUpdated = userService.updateUser(newId, user);

        assertTrue(isUpdated);
    }

    @Test
    void should_not_update_user_info_if_user_id_is_null() {
        User user = new User(
                "Nick",
                "Ray",
                5,
                EducationLevel.BACHELOR,
                "Senior Java Developer",
                "Quick learner. Worked in fintech companies for 3 years"
        );

        assertThrows(InvalidUserIdException.class, () -> userService.updateUser(null, user));
    }

    @Test
    void should_not_update_user_info_if_user_is_invalid() {
        assertThrows(UpdateUserFailedException.class, () -> userService.updateUser(PRESENT_ID, null));
    }

    @Test
    void should_successfully_change_password() {
        User user = new User("tvotech@mail.com",
                "qwas78/*KIUH");

        String newId = userRepository.addNewUser(user);

        String newPassword = "liopkjhfgqew_789/*-+";

        boolean newPasswordSaved = userService.changePsswd(newId, newPassword);

        assertTrue(newPasswordSaved);
    }

    @Test
    void should_throw_exception_if_new_password_is_the_same_than_the_old_one() {
        User user = new User("tvotech@mail.com",
                "qwas78/*KIUH");

        String newId = userRepository.addNewUser(user);

        String newPassword = user.getPassword();

        assertThrows(ChangingPasswordFailedException.class, () -> userService.changePsswd(newId, newPassword));
    }

    @Test
    void should_add_new_user() {
        User user = new User("tvotech@mail.com",
                "qwas78/*KIUH");

        String newId = userRepository.addNewUser(user);

        assertTrue(Objects.nonNull(newId));
    }

    @Test
    void should_throw_exception_if_user_exists() {
        User user = new User("tvotech@mail.com",
                "qwas78/*KIUH");

        userRepository.addNewUser(user);

        assertThrows(UserAlreadyExistsException.class, () -> userService.addNewUser(user));
    }

    @Test
    void should_throw_exception_if_user_is_invalid() {
        User user = new User(null,
                "qwas78/*KIUH");

        userRepository.addNewUser(user);

        assertThrows(InvalidUserException.class, () -> userService.addNewUser(user));
    }
}
