package com.eban.UserService.Service;

import org.springframework.stereotype.Service;

import com.eban.UserService.Model.Follow;

@Service
public interface FollowService {
    Follow follow(String userId, String userTarget);
    void unFollow(String userId, String userTarget);
    Long countFollowing(String userId);
    Long countFollower(String userId);
}
