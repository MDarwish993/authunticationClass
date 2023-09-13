package com.mohammadDarwish.authunticationClass.repository;

import com.mohammadDarwish.authunticationClass.models.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post,Long> {


}
