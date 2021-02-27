package com.tts.techtalentblog.controller;

import com.tts.techtalentblog.model.BlogPost;
import com.tts.techtalentblog.repo.BlogPostRepository;
import com.tts.techtalentblog.service.BlogPostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
public class BlogPostController {

    @Autowired
    private BlogPostService service;
    private static List<BlogPost> posts = new ArrayList<>();

    @GetMapping(value = "/")
    public String index(BlogPost blogPost, Model model) {
        posts = service.listAll();
        model.addAttribute("posts", posts);
        return "blogpost/index";
    }

    private BlogPost blogPost;

    @GetMapping(value = "/blogpost/CreatePost")
    public String newBlog(BlogPost blogPost) {
        return "blogpost/CreatePost";
    }


    @PostMapping(value = "/blogpost")
    public String addNewBlogPost(BlogPost blogPost, Model model) {
        service.addBlogPost(new BlogPost(
                blogPost.getTitle(),
                blogPost.getAuthor(),
                blogPost.getBlogEntry()));
        posts = service.listAll();
        model.addAttribute("title", blogPost.getTitle());
        model.addAttribute("author", blogPost.getAuthor());
        model.addAttribute("blogEntry", blogPost.getBlogEntry());
        return "blogpost/result";

    }

    @RequestMapping(value = "/blogpost/{id}", method = RequestMethod.DELETE)
    public String deletePostWithId(@PathVariable Long id, BlogPost blogPost, Model model) {
        service.deleteBlogPost(id);
        posts = service.listAll();
        model.addAttribute("posts", posts);
        return "blogpost/delete";
    }

    @GetMapping(value = "/blogpost/{id}")
    public String editPostWithId(@PathVariable Long id, BlogPost blogPost, Model model) {
        Optional<BlogPost> post = service.findById(id);
        if (post.isPresent()) {
            BlogPost actualPost = post.get();
            model.addAttribute("blogPost", actualPost);
        }
        posts = service.listAll();
        return "blogpost/edit";
    }

    @RequestMapping(value = "blogpost/update/{id}")
    public String updateExistingPost(@PathVariable Long id, BlogPost blogPost, Model model) {
        Optional<BlogPost> post = service.findById(id);
        if (post.isPresent()) {
            BlogPost actualPost = post.get();
            actualPost.setTitle(blogPost.getTitle());
            actualPost.setAuthor(blogPost.getAuthor());
            actualPost.setBlogEntry(blogPost.getBlogEntry());
            service.addBlogPost(actualPost);
            model.addAttribute("blogPost", actualPost);
            model.addAttribute("title", blogPost.getTitle());
            model.addAttribute("author", blogPost.getAuthor());
            model.addAttribute("blogEntry", blogPost.getBlogEntry());
        }
        return "blogpost/result";
    }
}
//    @RequestMapping(value = "blogpost/delete/{id}")
//    public String deletePostById(@PathVariable Long id, BlogPost blogPost) {
//        blogPostRepository.deleteById(id);
//        return "blogpost/delete";
//    }







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



