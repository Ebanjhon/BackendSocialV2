package com.eban.FeedService.Service.ServiceImpl;

import com.eban.FeedService.Model.Feed;
import com.eban.FeedService.Repository.FeedRepository;
import com.eban.FeedService.Service.FeedService;

import jakarta.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
public class FeedServiceImpl implements FeedService {
    @Autowired
    private FeedRepository feedRepo;

    @Autowired
    private DeleteCommentGrpc deleteCommentGrpc;

    public Feed saveFeed(Feed feed) {
        return feedRepo.save(feed);
    }

    @Override
    public void deleteFeedById(String feedId) {
        try{
            deleteCommentGrpc.deleteComment(feedId);
            feedRepo.deleteFeedById(feedId);
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    @Override
    public Feed getFeedById(String feedId) {
        return feedRepo.findFeedById(feedId);
    }

    @Override
    @Transactional
    public Feed updateFeed(Feed feed, String content) {
        try {
            feed.setContent(content);
            return feedRepo.save(feed);
        } catch (Exception e) {
            throw new RuntimeException("Lỗi khi cập nhật bài viết: " + e.getMessage());
        }
    }

    @Override
    public Page<Feed> getListFeedByAuthor(String authorId, int page, int size) {
        Pageable pageable = PageRequest.of(page, size, Sort.by("createDay").descending());
        return (Page<Feed>) feedRepo.findByAuthorId(authorId, pageable);
    }

    @Override
    public Page<String> getListFeed(int page, int size) {
        Page<String> feedIds = feedRepo.findAllFeedIds(PageRequest.of(page, size));
        return feedIds;
    }

    @Override
    public Long countFeedByUserId(String authorId) {
        return feedRepo.countFeedsByAuthorId(authorId);
    }

    @Override
    public Page<String> getListFeedByUserId(String authorId,int page, int size) {
        Page<String> feedIds = feedRepo.findFeedIdsByAuthorId(authorId,PageRequest.of(page, size));
        return feedIds;
    }

}
