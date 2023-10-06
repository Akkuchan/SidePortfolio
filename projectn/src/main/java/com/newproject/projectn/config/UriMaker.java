package com.newproject.projectn.config;

import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
@Component
public class UriMaker {//HATEOS 적용을 위한 URI 작성

    public String uriMaker(@Nullable String prefixUriPath, @Nullable String endUriPath){

        return ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("/" + prefixUriPath)
                .path("/"+ endUriPath)
                .toUriString();
    }
}
