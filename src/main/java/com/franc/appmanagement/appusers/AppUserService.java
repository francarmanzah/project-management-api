package com.franc.appmanagement.appusers;

import java.util.Optional;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.franc.appmanagement.applicationuser.ApplicationUser;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AppUserService {

    private final AppUserRepository appUserRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    // public AppUser createOne(AppUser appUser) {
    //     ApplicationUser applicationUser = appUser.getApplicationUser();
    //     String hashPassword = bCryptPasswordEncoder.encode(applicationUser.getPassword());
    //     applicationUser.setPassword(hashPassword);
    //     return appUserRepository.save(appUser);
    // }

    public AppUser createOne(AppUser appUser) {
        String username = appUser.getUsername();
        String email = appUser.getEmail();

        // Check if username already exists
        if (appUserRepository.existsByUsername(username)) {
            throw new IllegalArgumentException("Username already exists");
        }

        // Check if email already exists
        if (appUserRepository.existsByEmail(email)) {
            throw new IllegalArgumentException("Email already exists");
        }

        ApplicationUser applicationUser = appUser.getApplicationUser();
        String hashPassword = bCryptPasswordEncoder.encode(applicationUser.getPassword());
        applicationUser.setPassword(hashPassword);
        return appUserRepository.save(appUser);
    }
    
    public Optional<AppUser> findByUsernameOrEmail(String usernameOrEmail) {
        return appUserRepository.findByUsernameOrEmail(usernameOrEmail);
    }
    
    // public AppUser updateImageUrl(AppUser appUser, byte[] imageData) {
    //     Image image = appUser.getImage();
    //     if (image == null) {
    //         image = new Image();
    //         appUser.setImage(image);
    //         image.setAppUser(appUser);
    //     }
    //     image.setData(imageData);
    
    //     return appUserRepository.save(appUser);
    // }
    
    public AppUser update(AppUser appUser, byte[] imageData) {
        appUser.setImageData(imageData);
        return appUserRepository.save(appUser);
    }

    public AppUser findOneById(Long id) {
        return null;
    }

    public void updateImage(AppUser appUser, byte[] imageData) {
    }

}
