package com.feeham.blog.service.ServiceImpl;//package com.feeham.blog.service.ServiceImpl;

import com.feeham.blog.DTO.PostReadDTO;
import com.feeham.blog.entity.Post;
import com.feeham.blog.entity.Tag;
import com.feeham.blog.helper.ManualMapper;
import com.feeham.blog.repository.PostRepository;
import com.feeham.blog.repository.TagRepository;
import com.feeham.blog.service.IService.ITagService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class TagService implements ITagService {

    private final TagRepository tagRepository;
    private final PostRepository postRepository;
    private final ManualMapper manualMapper;

    public TagService(TagRepository tagRepository, PostRepository postRepository, ManualMapper manualMapper){
        this.tagRepository = tagRepository;
        this.postRepository = postRepository;
        this.manualMapper = manualMapper;
    }

    @Override
    public void create(String tag) {
        tagRepository.save(new Tag(tag));
    }

    @Override
    public Optional<Tag> read(Integer tagId) {
        return tagRepository.findById(tagId);
    }

    @Override
    public void update(Integer tagId, String tag) {
        Optional<Tag> existingTag = tagRepository.findById(tagId);
        existingTag.ifPresent(t -> {
            t.setTag(tag);
            tagRepository.save(t);
        });
    }

    @Override
    public void delete(Integer tagId) {
        tagRepository.deleteById(tagId);
    }

    @Override
    public List<Tag> readAll() {
        return tagRepository.findAll();
    }

    @Override
    public List<PostReadDTO> getPostsByTagId(Integer tagId) {
        Optional<Tag> tagOptional = tagRepository.findById(tagId);
        if(tagOptional.isPresent()){
            Tag tag = tagOptional.get();
            List<PostReadDTO> result = new ArrayList<>();
            for(Post post: tag.getPosts()){
                result.add(manualMapper.postToPostReadDTO(post));
            }
            return result;
        }
        return new ArrayList<>();
    }

    @Override
    public List<PostReadDTO> getPostsByTagName(String tag) {
        for(Tag existing: tagRepository.findAll()){
            if (existing.getTag().equals(tag)){
                return getPostsByTagId(existing.getId());
            }
        }
        return new ArrayList<>();
    }
}
