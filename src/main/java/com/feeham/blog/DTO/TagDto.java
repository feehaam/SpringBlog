package com.feeham.blog.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.stereotype.Component;

@Data
@Component
@AllArgsConstructor
public class TagDto {
    private Integer tagId;
    private String tag;
}
