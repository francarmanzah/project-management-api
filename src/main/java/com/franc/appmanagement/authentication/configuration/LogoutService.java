// package com.franc.appmanagement.authentication.configuration;

// import org.springframework.security.core.Authentication;
// import org.springframework.security.web.authentication.logout.LogoutHandler;
// import org.springframework.stereotype.Service;

// import jakarta.servlet.http.HttpServletRequest;
// import jakarta.servlet.http.HttpServletResponse;
// import lombok.RequiredArgsConstructor;

// @Service
// @RequiredArgsConstructor
// public class LogoutService implements LogoutHandler {

//     @Override
//     public void logout(HttpServletRequest request, 
//     HttpServletResponse response, 
//     Authentication authentication) {
//     }
//     final String getJwt(HttpServletRequest request) {
//         String authorizationHeader = request.getHeader("Authorization");

//         if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
//             return authorizationHeader.replace("Bearer ", "");
//         }
//         return null;
//     }
// }
