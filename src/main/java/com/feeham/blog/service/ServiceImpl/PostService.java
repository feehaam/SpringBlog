package com.feeham.blog.service.ServiceImpl;

import com.feeham.blog.DTO.PostCreateDTO;
import com.feeham.blog.DTO.PostReadDTO;
import com.feeham.blog.DTO.PostUpdateDTO;
import com.feeham.blog.entity.Post;
import com.feeham.blog.entity.Tag;
import com.feeham.blog.entity.User;
import com.feeham.blog.exceptions.NoRecordException;
import com.feeham.blog.exceptions.ResourceNotFoundException;
import com.feeham.blog.repository.PostRepository;
import com.feeham.blog.repository.UserRepository;
import com.feeham.blog.service.IService.IPostService;
import com.feeham.blog.service.ServiceImpl.helper.ManualMapper;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PostService implements IPostService {
    private final PostRepository postRepository;
    private final ModelMapper autoMapper;
    private final UserRepository userRepository;
    private final TagService tagService;
    private final ManualMapper manualMapper;

    public PostService(PostRepository postRepository, ModelMapper modelMapper, ManualMapper manualMapper,
                       UserRepository userRepository, TagService tagService) {
        this.postRepository = postRepository;
        this.autoMapper = modelMapper;
        this.userRepository = userRepository;
        this.tagService = tagService;
        this.manualMapper = manualMapper;
    }

    @Override
    public void create(PostCreateDTO postCreateDTO) throws ResourceNotFoundException {
        Post post = new Post();
        Optional<User> userOptional = userRepository.findById(postCreateDTO.getAuthorId());
        if (userOptional.isPresent()) {
            User user = userOptional.get();

            post.setTitle(postCreateDTO.getTitle());
            post.setContent(postCreateDTO.getContent());
            post.setTimeCreated(LocalDateTime.now());
            post.setTimeLastModified(LocalDateTime.now());
            post.setComments(new ArrayList<>());
            post.setTags(new ArrayList<>());
            for (Integer tagId : postCreateDTO.getTagIdList()) {
                Tag tag = tagService.read(tagId);
                post.getTags().add(tag);
            }
            post.setAuthor(user);
            user.getPosts().add(post);

            postRepository.save(post);
        } else {
            throw new ResourceNotFoundException("User not found", "Create post",
                    "User with ID " + postCreateDTO.getAuthorId() + " not found.");
        }
    }

    @Override
    public PostReadDTO read(Integer postId) throws ResourceNotFoundException {
        Optional<Post> postOptional = postRepository.findById(postId);
        return postOptional.map(manualMapper::postToPostReadDTO)
                .orElseThrow(() -> new ResourceNotFoundException("Post not found", "Get post",
                        "Post with ID " + postId + " does not exist."));
    }

    @Override
    public void update(PostUpdateDTO postUpdateDTO) throws ResourceNotFoundException {
        Optional<Post> postOptional = postRepository.findById(postUpdateDTO.getId());
        if (postOptional.isPresent()) {
            Post post = postOptional.get();
            autoMapper.map(postUpdateDTO, post);
            postRepository.save(post);
        } else {
            throw new ResourceNotFoundException("Post not found", "Update post",
                    "Post with ID " + postUpdateDTO.getId() + " does not exist.");
        }
    }

    @Override
    public void delete(Integer postId) throws ResourceNotFoundException {
        if (!postRepository.existsById(postId)) {
            throw new ResourceNotFoundException("Failed to delete", "Delete post",
                    "Post with ID " + postId + " does not exist.");
        }
        postRepository.deleteById(postId);
    }

    @Override
    public List<PostReadDTO> readAll() throws NoRecordException {
        List<Post> posts = postRepository.findAll();
        if (posts.isEmpty()) {
            throw new NoRecordException("No records found", "List of posts", "No posts in the database");
        }
        return posts.stream()
                .map(manualMapper::postToPostReadDTO)
                .collect(Collectors.toList());
    }

    @Override
    public void addTagToPost(Integer postId, Integer tagId) throws ResourceNotFoundException{
        Tag tag = tagService.read(tagId);
        Optional<Post> postOptional = postRepository.findById(postId);
        if(postOptional.isEmpty()){
            throw new ResourceNotFoundException("Post not found", "Add tag to post",
                    "Post with ID " + postId + " does not exist.");
        }
        postOptional.get().getTags().add(tag);
    }
}
