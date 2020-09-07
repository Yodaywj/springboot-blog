package com.roderland.blog.service.impl;

import com.roderland.blog.dao.TagRepository;
import com.roderland.blog.exception.NotFoundException;
import com.roderland.blog.po.Tag;
import com.roderland.blog.service.TagService;
import com.roderland.blog.utils.MD5Utils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/*
    @author: Roderland
    @create: 2020-09-06---8:02
*/
@Service
public class TagServiceImpl implements TagService {

    @Autowired
    private TagRepository tagRepository;

    @Transactional
    @Override
    public Tag saveTag(Tag tag) {
        return tagRepository.save(tag);
    }

    @Override
    public Tag getTag(Long id) {
        return tagRepository.getOne(id);
    }

    @Override
    public Page<Tag> listTag(Pageable pageable) {
        return tagRepository.findAll(pageable);
    }

    @Override
    public List<Tag> listTag() {
        return tagRepository.findAll();
    }

    @Override
    public List<Tag> listTag(String ids) {
        return tagRepository.findAllById(stringToLongList(ids));
    }

    @Override
    public List<Tag> listTag(Integer size) {
        Sort.Order order = new Sort.Order(Sort.Direction.DESC, "blogList.size");
        Pageable pageable = PageRequest.of(0, size, Sort.by(order));
        return tagRepository.findTop(pageable);
    }

    private static List<Long> stringToLongList(String string) {
        List<Long> list = new ArrayList<>();
        if (string!=null && !"".equals(string)) {
            String[] strings = string.split(",");
            for (String s : strings) {
                list.add(new Long(s));
            }
        }
        return list;
    }

    @Transactional
    @Override
    public Tag updateTag(Long id, Tag tag) {
        Tag t = tagRepository.getOne(id);
        if (t==null) {
            throw new NotFoundException("标签不存在");
        }
        BeanUtils.copyProperties(tag, t);
        return tagRepository.save(t);
    }

    @Transactional
    @Override
    public void deleteTag(Long id) {
        tagRepository.deleteById(id);
    }
}
