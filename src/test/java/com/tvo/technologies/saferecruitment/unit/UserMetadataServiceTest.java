package com.tvo.technologies.saferecruitment.unit;

import com.tvo.technologies.saferecruitment.exception.*;
import com.tvo.technologies.saferecruitment.model.user.EducationLevel;
import com.tvo.technologies.saferecruitment.model.user.UserMetadata;
import com.tvo.technologies.saferecruitment.repository.UserMetadataRepository;
import com.tvo.technologies.saferecruitment.service.UserMetadataService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class UserMetadataServiceTest {
    private static final String PRESENT_ID = "1";
    private static final String NOT_PRESENT_ID = "-1";

    @Mock
    private UserMetadataRepository userMetadataRepository;

    @InjectMocks
    private UserMetadataService userMetadataService;

    @Test
    void should_get_user_by_id() {
        UserMetadata expexctedUserMetadata = new UserMetadata("tvotech@mail.com");

        when(userMetadataRepository.getUser(PRESENT_ID)).thenReturn(expexctedUserMetadata);

        UserMetadata userMetadata = userMetadataService.getUser(PRESENT_ID);

        assertEquals(expexctedUserMetadata, userMetadata);
    }

    @Test
    void should_throw_exception_if_user_id_is_null() {
        assertThrows(InvalidUserIdException.class, () -> userMetadataService.getUser(null));
    }

    @Test
    void should_throw_exception_if_user_id_is_wrong() {
        when(userMetadataRepository.getUser(NOT_PRESENT_ID)).thenThrow(UserNotFoundException.class);
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

        userMetadata.setName("Vlad");
        when(userMetadataRepository.updateUser(PRESENT_ID, userMetadata)).thenReturn(true);

        boolean isUpdated = userMetadataService.updateUser(PRESENT_ID, userMetadata);

        assertTrue(isUpdated);

        verify(userMetadataRepository, times(1)).updateUser(PRESENT_ID, userMetadata);
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
    void should_not_update_user_info_if_user_is_invalid() {
        assertThrows(UpdateUserFailedException.class, () -> userMetadataService.updateUser(PRESENT_ID, null));
    }

    @Test
    void should_add_new_user() {
        UserMetadata userMetadata = new UserMetadata("tvotech@mail.com");

        when(userMetadataRepository.addNewUser(userMetadata)).thenReturn(PRESENT_ID);

        boolean isSaved = userMetadataService.addNewUser(userMetadata);

        assertTrue(isSaved);

        verify(userMetadataRepository, times(1)).addNewUser(userMetadata);
    }

    @Test
    void should_throw_exception_if_user_exists() {
        UserMetadata userMetadata = new UserMetadata("tvotech@mail.com");

        when(userMetadataRepository.addNewUser(userMetadata)).thenThrow(UserAlreadyExistsException.class);

        assertThrows(UserAlreadyExistsException.class, () -> userMetadataService.addNewUser(userMetadata));
    }

    @Test
    void should_throw_exception_if_user_is_invalid() {
        UserMetadata userMetadata = new UserMetadata(null);

        assertThrows(InvalidUserException.class, () -> userMetadataService.addNewUser(userMetadata));
    }
}
