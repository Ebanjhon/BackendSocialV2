package com.eban.UserService.Service.ServiceImpl;

import java.util.Optional;

import com.eban.UserService.Service.ServiceGRPC.NotiGrpc;
import com.eban.notification.grpc.TypeNoti;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.eban.UserService.Model.Follow;
import com.eban.UserService.Repository.FollowRepository;
import com.eban.UserService.Service.FollowService;

@Service
public class FollowServiceImpl implements FollowService {

    @Autowired
    private FollowRepository followRepository;

    @Autowired
    private NotiGrpc notiGrpcService;

    @Override
    public Follow follow(String userId, String userTarget) {
        Follow follow = new Follow(userId, userTarget);
        notiGrpcService.sendNotification(userTarget, userId, TypeNoti.FOLLOW);
        return followRepository.save(follow);
    }

    @Override
    public void unFollow(String userId, String userTarget) {
        Optional<Follow> follow = followRepository.findFollow(userId, userTarget);
        follow.ifPresent(f -> followRepository.deleteById(f.getFollowId()));
    }

    @Override
    public Long countFollowing(String userId) {
        return followRepository.countFollowingByUserId(userId);
    }

    @Override
    public Long countFollower(String userId) {
        return followRepository.countFollowersByUserId(userId);
    }

}
