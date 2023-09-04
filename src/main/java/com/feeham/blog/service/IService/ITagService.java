package com.feeham.blog.service.IService;

import com.feeham.blog.DTO.PostReadDTO;
import com.feeham.blog.entity.Tag;

import java.util.List;

public interface ITagService {

    /**
     * Tag Service:
     * -
     * This interface defines methods for managing tag-related operations, including creating,
     * reading, updating, and deleting tags, as well as retrieving a list of all tags and
     * retrieving posts by tag ID or tag name.
     *  -
     * Note: Optional is not used as the null-pointer exception is checked in the service layer
     * and then thrown as custom exceptions, which are handled by a global exception handler.
     */

    void create(String tag);
    Tag read(Integer tagId);
    void update(Integer tagId, String tag);
    void delete(Integer tagId);
    List<Tag> readAll();
    List<PostReadDTO> getPostsByTagId(Integer tagId);
    List<PostReadDTO> getPostsByTagName(String tag);
}
