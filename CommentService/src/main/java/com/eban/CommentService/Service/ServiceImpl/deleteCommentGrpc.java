package com.eban.CommentService.Service.ServiceImpl;

import com.example.comment.CommentServiceGrpc;
import com.example.comment.FeedIdRequest;
import com.example.comment.SimpleResponse;
import io.grpc.stub.StreamObserver;
import net.devh.boot.grpc.server.service.GrpcService;
import org.springframework.beans.factory.annotation.Autowired;

@GrpcService
public class deleteCommentGrpc extends CommentServiceGrpc.CommentServiceImplBase {

    @Autowired
    private CommentServiceImpl commentService;

    @Override
    public void deleteCommentsByFeedId(FeedIdRequest request, StreamObserver<SimpleResponse> responseObserver) {
        commentService.DeleteCommentByFeedId(request.getFeedId());
        SimpleResponse response = SimpleResponse.newBuilder()
                .setMessage("Success!")
                .build();

        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }
}
