package com.meghana.usermanagementsystem.repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.stereotype.Repository;

import com.meghana.usermanagementsystem.model.UserProfile;

@Repository
public class NonPersistentUserProfileRepository implements UserProfileRepository{

    private Map<Long,UserProfile> userProfileMap = new HashMap<>();
    private long idCounter = 1;

    public synchronized Long getNewId(){
        return idCounter++;
   }

    @Override
    public UserProfile createUserProfile(UserProfile userProfile){
        if(userProfile.getId() == null){
            userProfile.setId(getNewId());
        }
        userProfileMap.put(userProfile.getId(), userProfile);
        return userProfile;
    }

    @Override
    public List<UserProfile> getUserProfileByEmail(String email){
        List<UserProfile> userProfiles = new ArrayList<>();
        userProfiles = userProfileMap.values().stream().filter(profile -> profile.getPrimaryEmail().equalsIgnoreCase(email)
        || profile.getSecondaryEmail().equalsIgnoreCase(email)).collect(Collectors.toList());
        return userProfiles;
    }

    @Override
    public List<UserProfile> getAllUserProfiles(){
        return new ArrayList<>(userProfileMap.values());
    }

    @Override
    public UserProfile updateUserProfile(UserProfile edittedUserProfile){
        UserProfile existingUserProfile = null;
        for (UserProfile userProfile : userProfileMap.values()) {
            if (edittedUserProfile.getPrimaryEmail().equals(userProfile.getPrimaryEmail()) ||
            edittedUserProfile.getPrimaryEmail().equals(userProfile.getSecondaryEmail()) ||
            edittedUserProfile.getSecondaryEmail().equals(userProfile.getPrimaryEmail()) ||
            edittedUserProfile.getSecondaryEmail().equals(userProfile.getSecondaryEmail())) {
                existingUserProfile = userProfile;
                break;
            }
        }
        if (existingUserProfile == null) {
            return null;
        }

        if(edittedUserProfile.getFirstName() != null){
            existingUserProfile.setFirstName(edittedUserProfile.getFirstName());
        }

        if(edittedUserProfile.getLastName() != null){
            existingUserProfile.setLastName(edittedUserProfile.getLastName());
        }

        if(edittedUserProfile.getTitle() != null){
            existingUserProfile.setTitle(edittedUserProfile.getTitle());
        }

        if(edittedUserProfile.getAddressLine1() != null){
            existingUserProfile.setAddressLine1(edittedUserProfile.getAddressLine1());
        }

        if(edittedUserProfile.getAddressLine2() != null){
            existingUserProfile.setAddressLine2(edittedUserProfile.getAddressLine2());
        }

        if(edittedUserProfile.getAddressLine3() != null){
            existingUserProfile.setAddressLine3(edittedUserProfile.getAddressLine3());
        }

        if(edittedUserProfile.getPrimaryEmail() != null){
            existingUserProfile.setPrimaryEmail(edittedUserProfile.getPrimaryEmail());
        }

        if(edittedUserProfile.getSecondaryEmail() != null){
            existingUserProfile.setSecondaryEmail(edittedUserProfile.getSecondaryEmail());
        }

        if(edittedUserProfile.getHomePhone() != null){
            existingUserProfile.setHomePhone(edittedUserProfile.getHomePhone());
        }

        if(edittedUserProfile.getMobile() != null){
            existingUserProfile.setMobile(edittedUserProfile.getMobile());
        }

        userProfileMap.put(existingUserProfile.getId(), existingUserProfile);

        return existingUserProfile;
    }

    @Override
    public boolean deleteUserProfile(List<UserProfile> deleteUserProfiles){
        for (UserProfile userProfile : deleteUserProfiles) {
            userProfileMap.remove(userProfile.getId());
        }
        return true;
    }
}
