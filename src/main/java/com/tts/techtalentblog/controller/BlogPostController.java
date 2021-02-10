package com.tts.techtalentblog.controller;

import com.tts.techtalentblog.model.BlogPost;
import com.tts.techtalentblog.repo.BlogPostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class BlogPostController {

    @Autowired
    private BlogPostRepository blogPostRepository;

    @GetMapping(value="/")
    public String index(BlogPost blogPost){
        // since we are utilizing thymeleaf
        // our output will be generated in a template
        // returning a reference to said template
        // will allow us to show the data that we want
        return "blogPost/index";
    }

    @PostMapping("/")
    public String addNewBlogPost(BlogPost blogPost, Model model){

    }


}
