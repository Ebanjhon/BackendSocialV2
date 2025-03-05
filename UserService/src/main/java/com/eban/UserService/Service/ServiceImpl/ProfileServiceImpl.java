package com.eban.UserService.Service.ServiceImpl;

import com.eban.UserService.Model.Profile;
import com.eban.UserService.Repository.ProfileRepository;
import com.eban.UserService.Service.ProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProfileServiceImpl implements ProfileService {

    @Autowired
    private ProfileRepository profileRepository;

    @Override
    public Profile saveProfile(Profile data) {
        return profileRepository.save(data);
    }

    @Override
    public Boolean editProfile(Profile profile) {
        int updatedRows = profileRepository.updateProfileByUserId(
                profile.getUserId(),
                profile.getBio(),
                profile.getGender(),
                profile.getPhoneNumber(),
                profile.getBirthDate()
        );

        return updatedRows != 0;
    }

}
