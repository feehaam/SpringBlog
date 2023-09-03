package com.feeham.blog.helper;

import com.feeham.blog.DTO.PostReadDTO;
import com.feeham.blog.entity.Post;
import org.springframework.stereotype.Service;

@Service
public class ManualMapper {
    public PostReadDTO postToPostReadDTO(Post post){

        PostReadDTO result = new PostReadDTO();
        result.setId(post.getId());
        result.setTitle(post.getTitle());
        result.setContent(post.getContent());
        result.setTimeCreated(post.getTimeCreated());
        result.setTimeLastModified(post.getTimeLastModified());
        result.setUserID(post.getAuthor().getId());
        result.setUserEmail(post.getAuthor().getEmail());
        result.setUserFullName(post.getAuthor().getFirstName() + " " + post.getAuthor().getLastName());
        result.setTags(post.getTags());
        result.setComments(post.getComments());

        return result;
    }
}
