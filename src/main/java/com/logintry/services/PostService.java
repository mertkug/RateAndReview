package com.logintry.services;

import com.logintry.models.Post;
import com.logintry.repository.PostRepository;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class PostService {
    private final PostRepository postRepository;

    public PostService(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    public void createPost(Post post) {
        // post.setOwner();
        postRepository.save(post);
    }

    public Post getPost(Post post) {
        return post;
    }

    public Set<Post> getPostsByOwner(Integer id) {
        return postRepository.findPostsByOwnerId(id);
    }
}
