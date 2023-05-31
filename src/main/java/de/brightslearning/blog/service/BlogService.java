package de.brightslearning.blog.service;

import de.brightslearning.blog.model.BlogEntry;
import jakarta.annotation.PostConstruct;

import java.time.LocalDateTime;
import java.util.ArrayList;



public class BlogService {

    ArrayList<BlogEntry> blogEntries = new ArrayList<>();



    @PostConstruct
    private void generateData(){
        this.blogEntries.add(new BlogEntry(1, "Warum Numa die tollste Hündin ist",
                "1. Sie ist süß. 2. Sie ist lieb. 3. Sie stinkt nicht. ",
                1, LocalDateTime.now()));

    }


    public ArrayList<BlogEntry> findAll() {
       return this.blogEntries;
    }

    public void save(BlogEntry entry){
        this.blogEntries.add(entry);
    }

    public void delete(Integer id){

        for(BlogEntry entry: blogEntries){
            if(entry.getId().equals(id)){
                this.blogEntries.remove(entry);
            }
        }
    }


    public BlogEntry getById(Integer id){
        for(BlogEntry entry: this.blogEntries){
            if(entry.getId().equals(id)){
               return entry;
            }
        }
    //TODO: als Stream umwandeln
    return null; //Null pointer Exception wird gefürchtet


    }
}

