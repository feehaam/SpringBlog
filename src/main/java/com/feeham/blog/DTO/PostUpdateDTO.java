package com.feeham.blog.DTO;

import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class PostUpdateDTO {
    private String title;
    private String content;
    private List<Integer> tagIdList;

    public PostUpdateDTO(){

    }

    public PostUpdateDTO(String title, String content, List<Integer> tagIdList) {
        this.title = title;
        this.content = content;
        this.tagIdList = tagIdList;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public List<Integer> getTagIdList() {
        return tagIdList;
    }

    public void setTagIdList(List<Integer> tagIdList) {
        this.tagIdList = tagIdList;
    }
}
