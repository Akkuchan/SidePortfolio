package com.newproject.projectn.entitiy.Enum;

public enum Type {

        // 운영타입(민간, 국공립, 사회복지법인, 법인-단체 등, 직장, 가정
        PRIVATE("민간"),
        PUBLIC("국공립"),
        ORGANIZATION("법인-단체 등"),
        CORPORATE ("직장"),
        HOME("가정"),
        ;


        private String type;

        Type(String type) {
            this.type = type;
        }
}


