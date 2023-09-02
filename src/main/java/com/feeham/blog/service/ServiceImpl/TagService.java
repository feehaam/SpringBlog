//package com.feeham.blog.service.ServiceImpl;
//
//import com.feeham.blog.entity.Tag;
//import com.feeham.blog.repository.TagRepository;
//import com.feeham.blog.service.IService.ITagService;
//import org.springframework.stereotype.Service;
//
//import java.util.List;
//import java.util.Optional;
//
//@Service
//public class TagService implements ITagService {
//
//    private final TagRepository tagRepository;
//
//    public TagService(TagRepository tagRepository){
//        this.tagRepository = tagRepository;
//    }
//
//    @Override
//    public void create(String tag) {
//        tagRepository.save(new Tag(tag));
//    }
//
//    @Override
//    public Optional<Tag> read(Integer tagId) {
//        return tagRepository.findById(tagId);
//    }
//
//    @Override
//    public void update(Integer tagId, String tag) {
//        Optional<Tag> existingTag = tagRepository.findById(tagId);
//        existingTag.ifPresent(t -> {
//            t.setTag(tag);
//            tagRepository.save(t);
//        });
//    }
//
//    @Override
//    public void delete(Integer tagId) {
//        tagRepository.deleteById(tagId);
//    }
//
//    @Override
//    public List<Tag> readAll() {
//        return tagRepository.findAll();
//    }
//}
