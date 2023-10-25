package com.newproject.projectn.config.security.auth;

import lombok.*;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class AuthenticationRequest {

    String email;
    String password;
}
