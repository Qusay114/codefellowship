package codefellowship.infrastructure.services;

import codefellowship.domain.Post;

import java.util.List;


public interface PostService {
    Post createPost(Post post);
    List<Post> getAllPosts();

}
