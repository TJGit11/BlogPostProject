package com.tts.techtalentblog.controller;

import com.tts.techtalentblog.model.BlogPost;
import com.tts.techtalentblog.repo.BlogPostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
public class BlogPostController {

    @Autowired
    private BlogPostRepository blogPostRepository;
    private static List<BlogPost> post = new ArrayList<>();

    @GetMapping(value="/")
    public String index(BlogPost blogPost, Model model){
        // since we are utilizing thymeleaf
        // our output will be generated in a template
        // returning a reference to said template
        // will allow us to show the data that we want
        return "blogpost/index";
    }

    private BlogPost blogPost;

    @GetMapping(value = "/blogpost/CreatePost")
    public String newBlog (BlogPost blogPost) {
        return "blogpost/CreatePost";
    }


    @PostMapping(value = "/blogpost")
    public String addNewBlogPost(BlogPost blogPost, Model model){
        blogPostRepository.save(new BlogPost(
                blogPost.getTitle(),
                blogPost.getAuthor(),
                blogPost.getBlogEntry()));
                model.addAttribute("title", blogPost.getTitle());
                model.addAttribute("author", blogPost.getAuthor());
                model.addAttribute("blogEntry", blogPost.getBlogEntry());
        return "blogpost/result";

    }

//    @PostMapping("/")
//    public String addNewBlogPost(BlogPost blogPost, Model model){
//        blogPostRepository.save(new BlogPost(
//                blogPost.getTitle(),
//                blogPost.getAuthor(),
//                blogPost.getBlogEntry()));
//        model.addAttribute("title", blogPost.getTitle());
//        model.addAttribute("author", blogPost.getAuthor());
//        model.addAttribute("blogEntry", blogPost.getBlogEntry());
//        return "blogpost/result";
//
//    }


}
