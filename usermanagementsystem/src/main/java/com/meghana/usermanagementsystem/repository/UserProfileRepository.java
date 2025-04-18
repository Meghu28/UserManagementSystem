package com.meghana.usermanagementsystem.repository;

import java.util.List;

import com.meghana.usermanagementsystem.model.UserProfile;

public interface UserProfileRepository {

    UserProfile createUserProfile(UserProfile userProfile);

    List<UserProfile> getUserProfileByEmail(String email);

    List<UserProfile> getAllUserProfiles();

    UserProfile updateUserProfile(UserProfile edittedUserProfile);

    boolean deleteUserProfile(List<UserProfile> deleteUserProfiles);
}
