package com.feeham.blog.DTO;

import org.springframework.stereotype.Component;

import java.util.List;

// Comment data transfer object for Updating a Post
@Component
public class PostUpdateDTO {
    private Integer id;
    private String title;
    private String content;
    private List<Integer> tagIdList;

    public PostUpdateDTO(){

    }

    public PostUpdateDTO(Integer id, String title, String content, List<Integer> tagIdList) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.tagIdList = tagIdList;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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
