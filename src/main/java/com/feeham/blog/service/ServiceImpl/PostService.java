package com.feeham.blog.service.ServiceImpl;

import com.feeham.blog.DTO.PostCreateDTO;
import com.feeham.blog.DTO.PostReadDTO;
import com.feeham.blog.DTO.PostUpdateDTO;
import com.feeham.blog.entity.Post;
import com.feeham.blog.entity.Tag;
import com.feeham.blog.entity.User;
import com.feeham.blog.repository.PostRepository;
import com.feeham.blog.repository.TagRepository;
import com.feeham.blog.repository.UserRepository;
import com.feeham.blog.service.IService.IPostService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PostService implements IPostService {
    private final PostRepository postRepository;
    private final ModelMapper modelMapper;
    private final UserRepository userRepository;
    private final TagRepository tagRepository;

    public PostService(PostRepository postRepository, ModelMapper modelMapper,
                       UserRepository userRepository, TagRepository tagRepository) {
        this.postRepository = postRepository;
        this.modelMapper = modelMapper;
        this.userRepository = userRepository;
        this.tagRepository = tagRepository;
    }

    @Override
    public void create(PostCreateDTO postCreateDTO) {

        Post post = new Post();
        Optional<User> userOptional = userRepository.findById(postCreateDTO.getAuthorId());
        if(userOptional.isPresent()){
            User user = userOptional.get();

            post.setTitle(postCreateDTO.getTitle());
            post.setContent(postCreateDTO.getContent());
            post.setTimeCreated(LocalDateTime.now());
            post.setTimeLastModified(LocalDateTime.now());
            post.setComments(new ArrayList<>());
            post.setTags(new ArrayList<>());
            for(Integer tagId: postCreateDTO.getTagIdList()){
                Optional<Tag> tagOptional = tagRepository.findById(tagId);
                tagOptional.ifPresent(tag -> post.getTags().add(tag));
            }
            post.setAuthor(user);
            user.getPosts().add(post);

            postRepository.save(post);
        }
        postRepository.save(post);
    }

    @Override
    public Optional<PostReadDTO> read(Integer postId) {
        Optional<Post> postOptional = postRepository.findById(postId);
        return postOptional.map(post -> modelMapper.map(post, PostReadDTO.class));
    }

    @Override
    public void update(PostUpdateDTO postUpdateDTO) {
        Optional<Post> postOptional = postRepository.findById(postUpdateDTO.getId());
        if (postOptional.isPresent()) {
            Post post = postOptional.get();
            modelMapper.map(postUpdateDTO, post);
            postRepository.save(post);
        } else {
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
