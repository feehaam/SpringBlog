package com.feeham.blog.service.ServiceImpl;

import com.feeham.blog.DTO.PostReadDTO;
import com.feeham.blog.entity.Post;
import com.feeham.blog.entity.Tag;
import com.feeham.blog.exceptions.NoRecordException;
import com.feeham.blog.exceptions.ResourceNotFoundException;
import com.feeham.blog.repository.TagRepository;
import com.feeham.blog.service.IService.ITagService;
import com.feeham.blog.service.ServiceImpl.helper.ManualMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class TagService implements ITagService {

    // Constructor injection of repositories and services
    private final TagRepository tagRepository;
    private final ManualMapper manualMapper;

    public TagService(TagRepository tagRepository, ManualMapper manualMapper){
        this.tagRepository = tagRepository;
        this.manualMapper = manualMapper;
    }

    /**
     * Create a new tag.
     * @param tagName The name of the tag to create.
     */
    @Override
    public void create(String tagName) {
        Tag tag = new Tag(tagName);
        tagRepository.save(tag);
    }

    /**
     * Read a tag by its ID.
     * @param tagId The ID of the tag to read.
     * @return The read Tag.
     * @throws ResourceNotFoundException if the tag does not exist.
     */
    @Override
    public Tag read(Integer tagId) throws ResourceNotFoundException {
        Optional<Tag> tagOptional = tagRepository.findById(tagId);
        if(tagOptional.isPresent()) {
            return tagOptional.get();
        }
        else throw new ResourceNotFoundException("Tag not found", "Get tag", "Tag with ID " + tagId + " does not exist.");
    }

    /**
     * Update the name of a tag.
     * @param tagId The ID of the tag to update.
     * @param tagName The new name for the tag.
     * @throws ResourceNotFoundException if the tag does not exist.
     */
    @Override
    public void update(Integer tagId, String tagName) throws ResourceNotFoundException {
        Tag tag = read(tagId);
        tag.setTag(tagName);
        tagRepository.save(tag);
    }

    /**
     * Delete a tag by its ID.
     * @param tagId The ID of the tag to delete.
     * @throws ResourceNotFoundException if the tag does not exist.
     */
    @Override
    public void delete(Integer tagId) throws ResourceNotFoundException {
        Tag tag = read(tagId);
        tagRepository.delete(tag);
    }

    /**
     * Read all tags.
     * @return A list of all tags.
     * @throws NoRecordException if no tags are found.
     */
    @Override
    public List<Tag> readAll() throws NoRecordException {
        List<Tag> tags = tagRepository.findAll();
        if (tags.isEmpty()) {
            throw new NoRecordException("No records found", "List of tags", "No tags in the database");
        }
        return tags;
    }

    /**
     * Get a list of posts associated with a tag by its ID.
     * @param tagId The ID of the tag to retrieve posts for.
     * @return A list of PostReadDTOs associated with the tag.
     * @throws ResourceNotFoundException if the tag does not exist.
     * @throws NoRecordException if no posts are found for the tag.
     */
    @Override
    public List<PostReadDTO> getPostsByTagId(Integer tagId) throws ResourceNotFoundException, NoRecordException {
        Optional<Tag> tagOptional = tagRepository.findById(tagId);
        if(tagOptional.isPresent()){
            Tag tag = tagOptional.get();
            List<PostReadDTO> result = new ArrayList<>();
            for(Post post: tag.getPosts()){
                result.add(manualMapper.postToPostReadDTO(post));
            }
            if(result.isEmpty()){
                throw new NoRecordException("No records found", "List of posts by tag", "No post was created with the tag: "+tag.getTag());
            }
            return result;
        }
        else throw new ResourceNotFoundException("Tag not found", "Get all posts by tag", "Tag with ID " + tagId + " does not exist.");
    }

    /**
     * Get a list of posts associated with a tag by its name.
     * @param tag The name of the tag to retrieve posts for.
     * @return A list of PostReadDTOs associated with the tag.
     * @throws ResourceNotFoundException if the tag does not exist.
     * @throws NoRecordException if no posts are found for the tag.
     */
    @Override
    public List<PostReadDTO> getPostsByTagName(String tag) throws ResourceNotFoundException, NoRecordException {
        for(Tag existing: tagRepository.findAll()){
            if (existing.getTag().equals(tag)){
                return getPostsByTagId(existing.getId());
            }
        }
        throw new ResourceNotFoundException("Tag not found", "Get posts by tag", "Tag " + tag + " does not exist.");
    }
}
