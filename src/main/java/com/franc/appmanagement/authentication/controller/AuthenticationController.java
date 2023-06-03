// package com.franc.appmanagement.authentication.controller;

// import org.slf4j.Logger;
// import org.slf4j.LoggerFactory;
// import org.springframework.http.ResponseEntity;
// import org.springframework.security.authentication.AuthenticationManager;
// import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
// import org.springframework.security.core.Authentication;
// import org.springframework.security.core.context.SecurityContextHolder;
// import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
// import org.springframework.web.bind.annotation.PostMapping;
// import org.springframework.web.bind.annotation.RequestBody;
// import org.springframework.web.bind.annotation.RestController;

// import com.franc.appmanagement.authentication.configuration.LogoutService;
// import com.franc.appmanagement.authentication.jwt.JwtProvider;
// import com.franc.appmanagement.authentication.model.dto.JwtResponseDto;
// import com.franc.appmanagement.authentication.model.dto.LoginRequestDto;

// import jakarta.servlet.http.HttpServletRequest;
// import jakarta.servlet.http.HttpServletResponse;
// import lombok.RequiredArgsConstructor;

// @RestController
// @RequiredArgsConstructor
// public class AuthenticationController {
//     private final AuthenticationManager authenticationManager;

//     private final JwtProvider jwtProvider;

//     private static final Logger logger = LoggerFactory.getLogger(AuthenticationController.class);

//     @PostMapping("/login")
//     public ResponseEntity<JwtResponseDto> login(@RequestBody LoginRequestDto LoginRequestDto) {
//         logger.info("Attempt login to system: {}", LoginRequestDto);

//         Authentication authentication = authenticationManager.authenticate(
//                 new UsernamePasswordAuthenticationToken(LoginRequestDto.getUsernameOrEmail(), LoginRequestDto.getPassword()));
//         SecurityContextHolder.getContext().setAuthentication(authentication);

//         String jwt = jwtProvider.generateJwtToken(authentication);

//         return ResponseEntity.ok(new JwtResponseDto(jwt));
//     }

// //     @PostMapping("/logout")
// //     public ResponseEntity<String> logout(HttpServletRequest request, HttpServletResponse response) {
// //     Authentication auth = SecurityContextHolder.getContext().getAuthentication();
// //     if (auth != null) {
// //         new SecurityContextLogoutHandler().logout(request, response, auth);
// //     }
// //     return ResponseEntity.ok("Logged out successfully");
// // }

//         SecurityContextLogoutHandler logoutHandler = new SecurityContextLogoutHandler();

//         @PostMapping("/logout")
//         public String performLogout(Authentication authentication, HttpServletRequest request, HttpServletResponse response) {
//             LogoutService logoutService = new LogoutService();
//             logoutHandler.logout(request, response, authentication);
//             return "/login";
//         }
        

// }

package com.franc.appmanagement.authentication.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.franc.appmanagement.authentication.jwt.JwtProvider;
import com.franc.appmanagement.authentication.model.dto.JwtResponseDto;
import com.franc.appmanagement.authentication.model.dto.LoginRequestDto;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class AuthenticationController {
    private final AuthenticationManager authenticationManager;

    private final JwtProvider jwtProvider;

    private static final Logger logger = LoggerFactory.getLogger(AuthenticationController.class);

    @PostMapping("/login")
    public ResponseEntity<JwtResponseDto> login(@RequestBody LoginRequestDto loginRequestDto) {
        logger.info("Attempt login to system: {}", loginRequestDto);

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequestDto.getUsernameOrEmail(), loginRequestDto.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);

        String jwt = jwtProvider.generateJwtToken(authentication);

        return ResponseEntity.ok(new JwtResponseDto(jwt));
    }

    @PostMapping("/logout")
    public ResponseEntity<String> logout(HttpServletRequest request, HttpServletResponse response) {
        // Mengambil objek Authentication saat ini
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        // Melakukan logout menggunakan SecurityContextLogoutHandler
        SecurityContextLogoutHandler logoutHandler = new SecurityContextLogoutHandler();
        logoutHandler.logout(request, response, authentication);
        return ResponseEntity.ok("Logged out successfully");
    }


}
