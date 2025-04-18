package com.meghana.usermanagementsystem.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.meghana.usermanagementsystem.model.UserProfile;
import com.meghana.usermanagementsystem.repository.UserProfileRepository;

@Service
public class UserProfileService {

    @Autowired
    private UserProfileRepository userProfileRepository;

    public UserProfile createUserProfile(UserProfile userProfile){
        return userProfileRepository.createUserProfile(userProfile);
    }

    public List<UserProfile> getUserProfileByEmail(String email){
        return userProfileRepository.getUserProfileByEmail(email);
    }

    public List<UserProfile> getAllUserProfiles(){
        return userProfileRepository.getAllUserProfiles();
    }

    public UserProfile updateUserProfile(UserProfile edittedUserProfile){
        return userProfileRepository.updateUserProfile(edittedUserProfile);
    }

    public boolean deleteUserProfile(String email){
        List<UserProfile> userProfiles = getUserProfileByEmail(email);
        if (userProfiles.isEmpty()) {
            return false;
        }
        return userProfileRepository.deleteUserProfile(userProfiles);
    }
}
