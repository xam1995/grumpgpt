package de.brightslearning.blog.service;

import de.brightslearning.blog.model.BlogEntry;
import de.brightslearning.blog.persistance.BlogDAO;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class BlogService {
private BlogDAO blogDAO;

    public BlogService(BlogDAO blogDAO) {
        this.blogDAO = blogDAO;
    }

    public Integer returnSize(){
        return blogDAO.findAll().size();
    }


    public List<BlogEntry> findAll() {
        return blogDAO.findAll();
    }

    public void save(BlogEntry entry){
        this.blogDAO.save(entry);
    }

    public void delete(Integer id){
        blogDAO.deleteById(id);
    }


    public Optional<BlogEntry> getById(Integer id){
    return this.blogDAO.findById(id);
    }
}

