package com.feeham.blog.service.ServiceImpl;

import com.feeham.blog.DTO.PostCreateDTO;
import com.feeham.blog.DTO.PostReadDTO;
import com.feeham.blog.DTO.PostUpdateDTO;
import com.feeham.blog.entity.Post;
import com.feeham.blog.repository.PostRepository;
import com.feeham.blog.service.IService.IPostService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PostService implements IPostService {
    private final PostRepository postRepository;
    private final ModelMapper modelMapper;

    @Autowired
    public PostService(PostRepository postRepository, ModelMapper modelMapper) {
        this.postRepository = postRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public void create(PostCreateDTO postCreateDTO) {
        Post post = modelMapper.map(postCreateDTO, Post.class);

        postRepository.save(post);
    }

    @Override
    public Optional<PostReadDTO> read(Integer postId) {
        Optional<Post> postOptional = postRepository.findById(postId);
        return postOptional.map(post -> modelMapper.map(post, PostReadDTO.class));
    }

    @Override
    public void update(PostUpdateDTO postUpdateDTO) {
        // Fetch the existing post by ID
        Optional<Post> postOptional = postRepository.findById(postUpdateDTO.getId());
        if (postOptional.isPresent()) {
            Post post = postOptional.get();
            // Update the post entity with new values from DTO
            modelMapper.map(postUpdateDTO, post);
            // Save the updated post
            postRepository.save(post);
        } else {
            // Handle the case when the post does not exist
            throw new IllegalArgumentException("Post not found with ID: " + postUpdateDTO.getId());
        }
    }

    @Override
    public void delete(Integer postId) {
        postRepository.deleteById(postId);
    }

    @Override
    public List<PostReadDTO> readAll() {
        List<Post> posts = postRepository.findAll();
        return posts.stream()
                .map(post -> modelMapper.map(post, PostReadDTO.class))
                .collect(Collectors.toList());
    }
}
