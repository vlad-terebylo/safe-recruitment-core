package com.tvo.technologies.saferecruitment.unit;

import com.tvo.technologies.saferecruitment.repository.UserRepository;
import com.tvo.technologies.saferecruitment.service.UserService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserService userService;

    @Test
    void should_get_user_by_id() {

    }

    @Test
    void should_throw_exception_if_user_id_is_null() {

    }

    @Test
    void should_throw_exception_if_user_id_is_wrong() {

    }

    @Test
    void should_update_user_info() {

    }

    @Test
    void should_not_update_user_info_if_user_id_is_null() {

    }

    @Test
    void should_not_update_user_info_if_user_is_null() {

    }

    @Test
    void should_successfully_change_password() {

    }

    @Test
    void should_throw_exception_if_new_password_is_the_same_than_the_old_one() {

    }

    @Test
    void should_add_new_user() {

    }

    @Test
    void should_throw_exception_if_user_exists() {

    }
}
