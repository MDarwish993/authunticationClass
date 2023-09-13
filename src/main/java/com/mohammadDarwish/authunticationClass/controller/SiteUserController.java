package com.mohammadDarwish.authunticationClass.controller;

import com.mohammadDarwish.authunticationClass.models.SiteUser;
import com.mohammadDarwish.authunticationClass.repository.SiteUserRepository;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.view.RedirectView;

@Controller
public class SiteUserController {

    @Autowired
    SiteUserRepository siteUserRepository;


    @GetMapping("/login")
    public String getLoginPage(){
        return "/login.html";
    }

    @GetMapping("/signup")
    public String getSignUpPage(){
        return "/signup.html";
    }

    @PostMapping("/signup")
    public RedirectView signUpUser(String username,String password){
        String hashedPassword= BCrypt.hashpw(password,BCrypt.gensalt(12));
        SiteUser siteUser=new SiteUser(username,hashedPassword);
        siteUserRepository.save(siteUser);
        return new RedirectView("/login");
    }

    @PostMapping("/login")
    public RedirectView signInUser(String username,String password){
        SiteUser userFromDb=siteUserRepository.findByUsername(username);

        if ((userFromDb ==null)||
                (!BCrypt.checkpw(password,userFromDb.getPassword()))){
            return new RedirectView("/login");
        }
        return new RedirectView("/");
    }

}
