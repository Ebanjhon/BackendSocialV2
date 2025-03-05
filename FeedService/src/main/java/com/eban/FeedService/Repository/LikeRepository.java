package com.eban.FeedService.Repository;

import com.eban.FeedService.Model.Feed;
import com.eban.FeedService.Model.Like;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import java.util.List;

public interface LikeRepository extends JpaRepository<Like, Long> {

}
