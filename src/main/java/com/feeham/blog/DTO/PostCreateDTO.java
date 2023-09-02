package com.feeham.blog.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.stereotype.Component;

import java.util.List;

@Data
@Component
@AllArgsConstructor
public class PostCreateDTO {
    private String title;
    private String content;
    private Integer authorId;
    private List<Integer> tagIdList;
}
