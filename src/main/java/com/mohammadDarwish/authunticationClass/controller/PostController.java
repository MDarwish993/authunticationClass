package com.mohammadDarwish.authunticationClass.controller;

import com.mohammadDarwish.authunticationClass.models.Post;
import com.mohammadDarwish.authunticationClass.models.SiteUser;
import com.mohammadDarwish.authunticationClass.repository.PostRepository;
import com.mohammadDarwish.authunticationClass.repository.SiteUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.view.RedirectView;

import java.util.List;

@Controller
public class PostController {
    @Autowired
    PostRepository postRepository;
    @Autowired
    SiteUserRepository siteUserRepository;

    @PostMapping("/add-post")
    public RedirectView addPost(String textContent, Long siteUserId){
        SiteUser siteUser=siteUserRepository
                .findById(siteUserId)
                .orElseThrow(()-> new RuntimeException());

        Post post=new Post(textContent,siteUser);

            postRepository.save(post);

        return new RedirectView("/");
    }

    @GetMapping ("/posts")
    public String getAllPosts(Model viewModel,Long siteUserId ){
        List<Post> listOfPost=postRepository.findAll();

        viewModel.addAttribute("listOfPost",listOfPost);
        return "/";
    }






    @DeleteMapping("/delete/{PostId}")
    public RedirectView deletePost(@PathVariable Long PostId){
       postRepository.deleteById(PostId);

        return new RedirectView("/");
    }



}
