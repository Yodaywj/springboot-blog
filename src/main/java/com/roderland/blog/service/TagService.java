package com.roderland.blog.service;

import com.roderland.blog.po.Tag;
import com.roderland.blog.po.Type;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/*
    @author: Roderland
    @create: 2020-09-06---8:02
*/
public interface TagService {

    Tag saveTag(Tag tag);
    Tag getTag(Long id);
    Page<Tag> listTag(Pageable pageable);
    Tag updateTag(Long id, Tag tag);
    void deleteTag(Long id);

}
