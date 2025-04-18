package com.meghana.usermanagementsystem.controller;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.meghana.usermanagementsystem.model.UserProfile;
import com.meghana.usermanagementsystem.service.UserProfileService;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
@EnableAutoConfiguration(exclude = DataSourceAutoConfiguration.class)
public class UserProfileControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Mock
    private UserProfileService userProfileService;

    @InjectMocks
    private UserProfileController userProfileController;

    private UserProfile userProfile;
    private List<UserProfile> userProfiles;

    @BeforeEach
    public void setUp() {
        userProfile = new UserProfile();
        userProfile.setFirstName("Alpha");
        userProfile.setLastName("Grade");
        userProfile.setPrimaryEmail("alpha@email.com");
        userProfile.setSecondaryEmail("grade@email.com");
        userProfile.setTitle("Mrs");
        userProfile.setAddressLine1("123a Main Road, Glasgow, G1 1AA");
        userProfile.setAddressLine2("123b Main Road, Glasgow G1 1AA");
        userProfile.setAddressLine3("123c Main Road, Glasgow G1 1AA");
        userProfile.setHomePhone("01411234567");
        userProfile.setMobile("0779900099987");

        userProfiles = Arrays.asList(userProfile);
    }

    @Test
    public void testCreateUserProfile() throws Exception {
        when(userProfileService.createUserProfile(any(UserProfile.class))).thenReturn(userProfile);

        mockMvc.perform(post("/users/profiles/createProfile")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{"
                        + "\"firstName\":\"Alpha\","
                        + "\"lastName\":\"Grade\","
                        + "\"title\":\"Mrs\","
                        + "\"primaryEmail\":\"alpha@email.com\","
                        + "\"secondaryEmail\":\"grade@email.com\","
                        + "\"addressLine1\":\"123a Main Road, Glasgow, G1 1AA\","
                        + "\"addressLine2\":\"123b Main Road, Glasgow G1 1AA\","
                        + "\"addressLine3\":\"123c Main Road, Glasgow G1 1AA\","
                        + "\"homePhone\":\"01411234567\","
                        + "\"mobile\":\"0779900099987\""
                        + "}"))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.firstName").value("Alpha"))
                .andExpect(jsonPath("$.primaryEmail").value("alpha@email.com"));
    }

    @Test
    public void testGetUserProfileByEmail() throws Exception {
        when(userProfileService.getUserProfileByEmail("alpha@email.com")).thenReturn(userProfiles);

        mockMvc.perform(get("/users/profiles/searchByEmail")
                .param("email", "alpha@email.com"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].primaryEmail").value("alpha@email.com"));
    }

    @Test
    public void testGetAllUserProfiles() throws Exception {
        when(userProfileService.getAllUserProfiles()).thenReturn(userProfiles);

        mockMvc.perform(get("/users/profiles/listProfiles"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].primaryEmail").value("alpha@email.com"));
    }

    @Test
    public void testUpdateUserProfile() throws Exception {
        UserProfile updatedProfile = new UserProfile();
        updatedProfile.setPrimaryEmail("alpha@email.com");
        updatedProfile.setFirstName("Jane");
        updatedProfile.setLastName("Smith");
        updatedProfile.setTitle("Ms");
        updatedProfile.setAddressLine1("123b Main Road, Glasgow");
        updatedProfile.setHomePhone("01411234567");
        updatedProfile.setMobile("0779900099987");

        when(userProfileService.updateUserProfile(any(UserProfile.class))).thenReturn(updatedProfile);

        mockMvc.perform(put("/users/profiles/updateProfile")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{"
                        + "\"firstName\":\"Kappa\","
                        + "\"lastName\":\"Data\","
                        + "\"title\":\"Ms\","
                        + "\"primaryEmail\":\"alpha@email.com\","
                        + "\"addressLine1\":\"123b Main Road, Glasgow\","
                        + "\"homePhone\":\"01411234567\","
                        + "\"mobile\":\"0779900099987\""
                        + "}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.lastName").value("Data"));
    }

    @Test
    public void testDeleteUserProfile() throws Exception {
        when(userProfileService.deleteUserProfile("alpha@email.com")).thenReturn(true);

        mockMvc.perform(delete("/users/profiles/deleteProfiles")
                .param("email", "alpha@email.com"))
                .andExpect(status().isNoContent())
                .andExpect(content().string("Successfully Deleted User Profiles"));
    }

    @Test
    public void testDeleteUserProfileNotFound() throws Exception {
        when(userProfileService.deleteUserProfile("nonexistent@email.com")).thenReturn(false);

        mockMvc.perform(delete("/users/profiles/deleteProfiles")
                .param("email", "nonexistent@email.com"))
                .andExpect(status().isNotFound())
                .andExpect(content().string("No User Profiles To Delete"));
    }
}