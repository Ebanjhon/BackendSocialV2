package com.eban.UserService.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.eban.UserService.Model.Profile;

public interface ProfileRepository extends JpaRepository<Profile, String> {

}
