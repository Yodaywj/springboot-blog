package com.roderland.blog.service.impl;

import com.roderland.blog.dao.BlogRepository;
import com.roderland.blog.exception.NotFoundException;
import com.roderland.blog.po.Blog;
import com.roderland.blog.po.Type;
import com.roderland.blog.service.BlogService;
import com.roderland.blog.utils.MarkdownUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/*
    @author: Roderland
    @create: 2020-09-06---10:35
*/
@Service
public class BlogServiceImpl implements BlogService {

    @Autowired
    private BlogRepository blogRepository;

    @Override
    public Blog getBlog(Long id) {
        return blogRepository.getOne(id);
    }

    @Override
    public Page<Blog> listBlog(Pageable pageable, Blog blog) {
        return blogRepository.findAll(new Specification<Blog>() {
            @Override
            public Predicate toPredicate(Root<Blog> root, CriteriaQuery<?> cQuery, CriteriaBuilder cBuilder) {
                List<Predicate> predicates = new ArrayList<>();
                if (blog.getTitle()!=null && !"".equals(blog.getTitle())) {
                    predicates.add(cBuilder.like(root.<String>get("title"), "%" + blog.getTitle() + "%"));
                }
                if (blog.getType()!=null && blog.getType().getId()!=null) {
                    predicates.add(cBuilder.equal(root.<Type>get("type"), blog.getType().getId()));
                }
                if (blog.isRecommend()) {
                    predicates.add(cBuilder.equal(root.<Boolean>get("recommend"), blog.isRecommend()));
                }
                cQuery.where(predicates.toArray(new Predicate[predicates.size()]));
                return null;
            }
        }, pageable);
    }

    @Override
    public Page<Blog> listBlog(Pageable pageable) {
        return blogRepository.findAll(pageable);
    }

    @Override
    public List<Blog> listBlog(Integer size) {
        Sort.Order order = new Sort.Order(Sort.Direction.DESC, "updateTime");
        Pageable pageable = PageRequest.of(0, size, Sort.by(order));
        return blogRepository.findTop(pageable);
    }

    @Override
    public Page<Blog> listBlog(Pageable pageable, String query) {
        return blogRepository.findByQuery(pageable, query);
    }

    @Transactional
    @Override
    public Blog saveBlog(Blog blog) {
        return blogRepository.save(blog);
    }

    @Transactional
    @Override
    public Blog updateBlog(Long id, Blog blog) {
        Blog b = blogRepository.getOne(id);
        if (b==null) {
            throw new NotFoundException("博客不存在");
        }
        BeanUtils.copyProperties(blog, b);
        return blogRepository.save(b);
    }

    @Transactional
    @Override
    public void deleteBlog(Long id) {
        blogRepository.deleteById(id);
    }

    @Override
    public Blog getAndConvert(Long id) {
        Blog blog = blogRepository.getOne(id);
        if (blog==null) {
            throw new NotFoundException("博客找不到");
        }
        Blog b = new Blog();
        BeanUtils.copyProperties(blog, b);
        String content = b.getContent();
        b.setContent(MarkdownUtils.markdownToHtmlExtensions(content));
        blogRepository.updateView(id);
        return b;
    }
}
