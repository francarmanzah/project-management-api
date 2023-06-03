package com.franc.appmanagement.appusers;

import java.io.IOException;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;

@SecurityRequirement(name = "bearerAuth")
@RestController
@RequiredArgsConstructor
public class AppUserController {
    
    private final AppUserService appUserService;

    @PostMapping("/register")
    public ResponseEntity<AppUserResponse> createOne(@RequestBody AppUserRequest appUserRequest){
        AppUser appUser = appUserRequest.convertToEntity();
        AppUser newAppUser = appUserService.createOne(appUser);
        AppUserResponse appUserResponse = newAppUser.convertToResponse();
        return ResponseEntity.status(201).body(appUserResponse);       
    }

    @SecurityRequirement(name = "bearerAuth")
    @GetMapping("/appusers")
    public ResponseEntity<AppUserResponse> getByUsernameOrEmail(@RequestParam("search") String usernameOrEmail) {
    Optional<AppUser> appUserOptional = appUserService.findByUsernameOrEmail(usernameOrEmail);
    if (appUserOptional.isPresent()) {
        AppUser appUser = appUserOptional.get();
        AppUserResponse appUserResponse = appUser.convertToResponse();
        return ResponseEntity.ok(appUserResponse);
    } else {
        return ResponseEntity.notFound().build();
    }
    }



//     @GetMapping("/AppUser/{id}/images")
//     public ResponseEntity<byte[]> getOneImageByAppUser(@PathVariable("id") Long id){
//         AppUser appUser = this.appUserService.findOneById(id);
//         Image image = appUser.getImage();

//         return ResponseEntity.ok().contentType(MediaType.valueOf(image.getType())).body(ImageUtility.decompressImage(image.getImage()));
        
//     }

// @SecurityRequirement(name = "bearerAuth")
// @PatchMapping("/AppUser/{id}/image")
//     public ResponseEntity<AppUserResponse> updateImageUrl(@PathVariable("id") Long id, @RequestBody UpdateImageUrlRequest request) throws IOException {
//         AppUser appUser = appUserService.findOneById(id);

//         if (appUser != null) {
        
//             byte[] imageData = ImageUtility.compressImage(request.getImageUrl());
//             appUser.setImageUrl(request.getImageUrl()); // Update the image URL
//             AppUser updatedAppUser = appUserService.update(appUser, imageData);
//             AppUserResponse appUserResponse = updatedAppUser.convertToResponse();
//             return ResponseEntity.ok(appUserResponse);
//         } else {
//             return ResponseEntity.notFound().build();
//         }
//     }

    @PostMapping("/{id}/uploadImage")
    public ResponseEntity<String> uploadImage(@PathVariable("id") Long id, @RequestParam("file") MultipartFile file) {
        try {
            // Check if the user exists
            AppUser appUser = appUserService.findOneById(id);
            if (appUser == null) {
                return ResponseEntity.notFound().build();
            }

            // Validate file type (optional)
            // You can check the file type or perform additional validation if needed

            // Save the image
            byte[] imageData = file.getBytes();
            appUserService.updateImage(appUser, imageData);

            return ResponseEntity.ok().build();
        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

}
