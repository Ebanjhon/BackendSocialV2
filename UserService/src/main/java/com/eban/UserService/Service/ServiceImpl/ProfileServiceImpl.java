package com.eban.UserService.Service.ServiceImpl;

import com.eban.UserService.Model.Gender;
import com.eban.UserService.Model.Profile;
import com.eban.UserService.Repository.ProfileRepository;
import com.eban.UserService.Service.ProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ProfileServiceImpl implements ProfileService {

    @Autowired
    private ProfileRepository profileRepository;

    @Override
    public Profile saveProfile(Profile data) {
        return profileRepository.save(data);
    }

    @Override
    public void editProfile(String userId, String bio, String phone, Gender gender) {
        Optional<Profile> result = profileRepository.findById(userId);
        if (result.isPresent()) {
            Profile profile = result.get();
            profile.setBio(bio);
            profile.setPhoneNumber(phone);
            profile.setGender(gender);
            profileRepository.save(profile);
        }
    }

}
