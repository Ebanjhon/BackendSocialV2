package com.eban.UserService.Service;

import com.eban.UserService.Model.Gender;
import org.springframework.stereotype.Service;

import com.eban.UserService.Model.Profile;

@Service
public interface ProfileService {
    Profile saveProfile(Profile profile);

    void editProfile(String userId, String bio, String phone, Gender gender);
}
