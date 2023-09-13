package com.mohammadDarwish.authunticationClass.repository;

import com.mohammadDarwish.authunticationClass.models.SiteUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SiteUserRepository extends JpaRepository<SiteUser,Long> {


         SiteUser findByUsername(String username);
}
