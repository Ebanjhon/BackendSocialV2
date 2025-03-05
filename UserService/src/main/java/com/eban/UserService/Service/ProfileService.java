package com.eban.UserService.Service;

import org.springframework.stereotype.Service;

import com.eban.UserService.Model.Profile;

@Service
public interface ProfileService {
    Profile saveProfile(Profile profile);

    Boolean editProfile(Profile profile);
}
