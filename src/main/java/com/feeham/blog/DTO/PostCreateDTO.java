package com.feeham.blog.DTO;

import org.springframework.stereotype.Component;

import java.util.List;

// Comment data transfer object for Creating a Post
@Component
public class PostCreateDTO {
    private String title;
    private String content;
    private Integer authorId;
    private List<Integer> tagIdList;

    public PostCreateDTO(){

    }

    public PostCreateDTO(String title, String content, Integer authorId, List<Integer> tagIdList) {
        this.title = title;
        this.content = content;
        this.authorId = authorId;
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

    public Integer getAuthorId() {
        return authorId;
    }

    public void setAuthorId(Integer authorId) {
        this.authorId = authorId;
    }

    public List<Integer> getTagIdList() {
        return tagIdList;
    }

    public void setTagIdList(List<Integer> tagIdList) {
        this.tagIdList = tagIdList;
    }
}
