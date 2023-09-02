package com.feeham.blog.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.stereotype.Component;

@Data
@Component
@AllArgsConstructor
public class CommentUpdateDto {
    private Integer commentId;
    private String content;
}
