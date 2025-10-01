package com.tvo.technologies.saferecruitment.unit;

import com.tvo.technologies.saferecruitment.exception.*;
import com.tvo.technologies.saferecruitment.model.dto.UserUpdateDto;
import com.tvo.technologies.saferecruitment.model.user.EducationLevel;
import com.tvo.technologies.saferecruitment.model.user.User;
import com.tvo.technologies.saferecruitment.repository.UserRepository;
import com.tvo.technologies.saferecruitment.service.UserService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {

    private static final String PRESENT_ID = "1";
    private static final String NOT_PRESENT_ID = "-1";

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserService userService;

    @Test
    void should_get_user_by_id() {
        User expexctedUser = new User("tvotech@mail.com",
                "qwas78/*KIUH");

        when(userRepository.getUser(PRESENT_ID)).thenReturn(expexctedUser);

        User user = userService.getUser(PRESENT_ID);

        assertEquals(expexctedUser, user);
    }

    @Test
    void should_throw_exception_if_user_id_is_null() {
        assertThrows(InvalidUserIdException.class, () -> userService.getUser(null));
    }

    @Test
    void should_throw_exception_if_user_id_is_wrong() {
        when(userRepository.getUser(NOT_PRESENT_ID)).thenThrow(UserNotFoundException.class);
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

        user.setName("Vlad");
        when(userRepository.updateUser(PRESENT_ID, user)).thenReturn(true);

        boolean isUpdated = userService.updateUser(PRESENT_ID, user);

        assertTrue(isUpdated);

        verify(userRepository, times(1)).updateUser(PRESENT_ID, user);
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

        when(userRepository.getUser(PRESENT_ID)).thenReturn(user);

        String newPassword = "liopkjhfgqew_789/*-+";

        when(userRepository.changePsswd(PRESENT_ID, newPassword)).thenReturn(true);

        boolean newPasswordSaved = userService.changePsswd(PRESENT_ID, newPassword);

        user.setPassword(newPassword);
        assertTrue(newPasswordSaved);
    }

    @Test
    void should_throw_exception_if_new_password_is_the_same_than_the_old_one() {
        User user = new User("tvotech@mail.com",
                "qwas78/*KIUH");

        when(userRepository.getUser(PRESENT_ID)).thenReturn(user);

        String newPassword = user.getPassword();
        user.setPassword(newPassword);

        assertThrows(ChangingPasswordFailedException.class, () -> userService.changePsswd(PRESENT_ID, newPassword));
    }

    @Test
    void should_add_new_user() {
        User user = new User("tvotech@mail.com",
                "qwas78/*KIUH");

        when(userRepository.addNewUser(user)).thenReturn(true);

        boolean isSaved = userService.addNewUser(user);

        assertTrue(isSaved);

        verify(userRepository, times(1)).addNewUser(user);
    }

    @Test
    void should_throw_exception_if_user_exists() {
        User user = new User("tvotech@mail.com",
                "qwas78/*KIUH");

        when(userRepository.addNewUser(user)).thenThrow(UserAlreadyExistsException.class);

        assertThrows(UserAlreadyExistsException.class, () -> userService.addNewUser(user));
    }

    @Test
    void should_throw_exception_if_user_is_invalid() {
        User user = new User("tvotech@mail.com",
                null);

        assertThrows(InvalidUserException.class, () -> userService.addNewUser(user));
    }
}
