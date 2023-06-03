package com.franc.appmanagement.appusers;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AppUserResponse {

    private Long id;
    private String name;
    private String email;
    private String username;
    private String imageUrl;

}
