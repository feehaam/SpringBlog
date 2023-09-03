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

    private final TagRepository tagRepository;
    private final ManualMapper manualMapper;

    public TagService(TagRepository tagRepository, ManualMapper manualMapper){
        this.tagRepository = tagRepository;
        this.manualMapper = manualMapper;
    }

    @Override
    public void create(String tagName) {
        Tag tag = new Tag(tagName);
        tagRepository.save(tag);
    }

    @Override
    public Tag read(Integer tagId) throws ResourceNotFoundException {
        Optional<Tag> tagOptional = tagRepository.findById(tagId);
        if(tagOptional.isPresent()) {
            return tagOptional.get();
        }
        else throw new ResourceNotFoundException("Tag not found", "Get tag", "Tag with ID " + tagId + " does not exist.");
    }

    @Override
    public void update(Integer tagId, String tagName) throws ResourceNotFoundException {
        Tag tag = read(tagId);
        tag.setTag(tagName);
        tagRepository.save(tag);
    }

    @Override
    public void delete(Integer tagId) throws ResourceNotFoundException {
        Tag tag = read(tagId);
        tagRepository.delete(tag);
    }

    @Override
    public List<Tag> readAll() throws NoRecordException {
        List<Tag> tags = tagRepository.findAll();
        if (tags.isEmpty()) {
            throw new NoRecordException("No records found", "List of tags", "No tags in the database");
        }
        return tags;
    }

    @Override
    public List<PostReadDTO> getPostsByTagId(Integer tagId) throws ResourceNotFoundException {
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
        else throw new ResourceNotFoundException("Tag not found", "Get all post by tag", "Tag with ID " + tagId + " does not exist.");
    }

    @Override
    public List<PostReadDTO> getPostsByTagName(String tag) throws ResourceNotFoundException {
        for(Tag existing: tagRepository.findAll()){
            if (existing.getTag().equals(tag)){
                return getPostsByTagId(existing.getId());
            }
        }
        throw new ResourceNotFoundException("Tag not found", "Get posts by tag", "Tag " + tag + " does not exist.");
    }
}
