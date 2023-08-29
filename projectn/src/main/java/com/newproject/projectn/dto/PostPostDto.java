package com.newproject.projectn.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class PostPostDto {
    long userId;
    String title;
    String body;
    List<String> imgList;// 첨부 이미지
    List<String> tags;//태그 목록
}
