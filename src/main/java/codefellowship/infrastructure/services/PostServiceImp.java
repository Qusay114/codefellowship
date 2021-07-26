package codefellowship.infrastructure.services;

import codefellowship.domain.Post;
import codefellowship.infrastructure.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("PostService")
public class PostServiceImp implements PostService{
    @Autowired
    private PostRepository postRepository ;

    @Override
    public Post createPost(Post post) {
        return postRepository.save(post);
    }
}
