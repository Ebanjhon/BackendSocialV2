package com.eban.FeedService.Service.ServiceImpl;

import com.example.comment.CommentServiceGrpc;
import com.example.comment.FeedIdRequest;
import net.devh.boot.grpc.client.inject.GrpcClient;
import org.springframework.stereotype.Service;

@Service
public class DeleteCommentGrpc {
    @GrpcClient("comment-service")
    private CommentServiceGrpc.CommentServiceBlockingStub cmtStub;

    public void deleteComment(String feedId){
        FeedIdRequest request = FeedIdRequest.newBuilder()
                .setFeedId(feedId)
                .build();

        cmtStub.deleteCommentsByFeedId(request);
    }
}
