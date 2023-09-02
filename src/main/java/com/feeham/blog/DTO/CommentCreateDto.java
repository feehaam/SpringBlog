package com.feeham.blog.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.stereotype.Component;

@Data
@Component
@AllArgsConstructor
public class CommentCreateDto {
    private Integer userId;
    private Integer postId;
    private Integer parentCommentId;
    private String content;
}
