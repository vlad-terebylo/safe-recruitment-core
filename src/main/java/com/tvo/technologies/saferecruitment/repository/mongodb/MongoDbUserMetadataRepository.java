package com.tvo.technologies.saferecruitment.repository.mongodb;

import com.tvo.technologies.saferecruitment.model.user.UserMetadata;
import com.tvo.technologies.saferecruitment.repository.UserMetadataRepository;

public class MongoDbUserMetadataRepository implements UserMetadataRepository {
    @Override
    public UserMetadata getUser(String id) {
        return null;
    }

    @Override
    public boolean updateUser(String id, UserMetadata userMetadata) {
        return false;
    }

    @Override
    public String addNewUser(UserMetadata userMetadata) {
        return " ";
    }
}
