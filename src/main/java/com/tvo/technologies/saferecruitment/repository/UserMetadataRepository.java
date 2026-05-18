package com.tvo.technologies.saferecruitment.repository;

import com.tvo.technologies.saferecruitment.model.user.UserMetadata;

public interface UserMetadataRepository {

    UserMetadata getUser(String id);

    boolean updateUser(String id, UserMetadata userMetadata);

    String addNewUser(UserMetadata userMetadata);
}
