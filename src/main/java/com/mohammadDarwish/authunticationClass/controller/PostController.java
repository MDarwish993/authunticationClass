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

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class PostController {
    @Autowired
    PostRepository postRepository;
    @Autowired
    SiteUserRepository siteUserRepository;

    @PostMapping("/add-post")
    public RedirectView addPost(HttpServletRequest request,String textContent){
        HttpSession session=request.getSession();

    String username=session.getAttribute("username").toString();

        SiteUser siteUser=siteUserRepository
                .findByUsername(username);

        Post post=new Post(textContent,siteUser);

            postRepository.save(post);

        return new RedirectView("/postList");
    }

    @GetMapping ("/postList")
    public String getAllPosts(HttpServletRequest request,Model viewModel){
        HttpSession session=request.getSession();
        String username=session.getAttribute("username").toString();
        viewModel.addAttribute("username",username);

        SiteUser siteUser=siteUserRepository.findByUsername(username);
            if(siteUser !=null){
                List<Post> listOfPost=siteUser.getPostList();
                viewModel.addAttribute("listOfPost",listOfPost);
            }

        return "/postList";
    }

//    @DeleteMapping("/delete/{PostId}")
//    public RedirectView deletePost(@PathVariable Long PostId){
//       postRepository.deleteById(PostId);
//
//        return new RedirectView("/postList");
//    }



}
