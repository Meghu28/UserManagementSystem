package com.meghana.usermanagementsystem.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.meghana.usermanagementsystem.model.UserProfile;
import com.meghana.usermanagementsystem.service.UserProfileService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/users/profiles")
public class UserProfileController {

    @Autowired
    private UserProfileService userProfileService;

    /**
     * Post request to create UserProfile.
     * If successfull it will send CREATED 201 status.
     * @param userProfile
     * @return ResponseEntity with created UserProfile.
    */
    @PostMapping("/createProfile")
    public ResponseEntity<UserProfile> createUserProfile(@RequestBody @Valid UserProfile userProfile){
        UserProfile createdProfile = userProfileService.createUserProfile(userProfile);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdProfile);
    }

    /**
     * Get request to search list of UserProfiles by email.
     * It will send 200 OK with list if successfull, 404 if not.
     * @param email
     * @return ResponseEntity with list of UserProfiles else 404
    */
    @GetMapping("/searchByEmail")
    public ResponseEntity<List<UserProfile>> getUserProfileByEmail(@RequestParam String email){
        List<UserProfile> userProfiles = userProfileService.getUserProfileByEmail(email);
        if (userProfiles.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.status(HttpStatus.OK).body(userProfiles);
    }

    /**
     * Get request to read list of userProfiles
     * If successfull it will send OK 200 status.
     * @return ResponseEntity with list of UserProfiles else 404
    */
    @GetMapping("/listProfiles")
    public ResponseEntity<List<UserProfile>> getAllUserProfiles(){
        List<UserProfile> userProfilesList = userProfileService.getAllUserProfiles();
        if(userProfilesList.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.status(HttpStatus.OK).body(userProfilesList);
    }

    /**
     * Put request to update existing userProfile.
     * If successfull it will send OK 200 status else 404.
     * @param userProfile
     * @return ResponseEntity with updated UserProfile else 404
    */
    @PutMapping("/updateProfile")
    public ResponseEntity<UserProfile> updateUserProfile(@RequestBody @Valid UserProfile userProfile){
        UserProfile updatedUserProfile = userProfileService.updateUserProfile(userProfile);
        if(updatedUserProfile == null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.status(HttpStatus.OK).body(updatedUserProfile);
    }

    /**
     * delete request to delete userProfile
     * @param email
     * @return
    */
    @DeleteMapping("/deleteProfiles")
    public ResponseEntity<String> deleteUserProfile(@RequestParam String email){
        boolean deleted = userProfileService.deleteUserProfile(email);
        if (deleted) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body("Successfully Deleted User Profiles");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No User Profiles To Delete");   
        }
    }
}
