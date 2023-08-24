package com.newproject.projectn.entitiy.Enum;


public enum UserGrade {
    NORMAL_USER("일반유저"),
    TEACHER_USER("교사유저"),
    PARENT_USER("학부모유저"),
    ADMIN_USER("관리자유저"),
    ;

    private String userType;

    UserGrade(String userType) {
        this.userType = userType;
    }
}
