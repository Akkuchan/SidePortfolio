package com.newproject.projectn.dto.kindergarten;


import com.newproject.projectn.entitiy.address.Address;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalTime;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class PostKindergartenDto {

    private String name;
    private String type;// 운영타입(민간, 국공립, 사회복지법인, 법인-단체 등, 직장, 가정
    private Address address;// 주소 + 우편번호
    private Double longitude;//경도
    private Double latitude;//위도
    private Boolean available;// 정상 운영여부
    private String number;// 전화번호
    private LocalTime startTime;
    private LocalTime endTime;
    private LocalDate authorizationDate;// 인가날짜
    private Boolean shuttleBus;// 통학차량 운영여부
    private String homepageUrl;// 홈페이지 주소
    private LocalDate abolitionDate;// 폐지일자(운영여부가 폐지일 경우에만 데이터 존재)
    private Integer classNum; //보육실수
    private Integer classArea;// 보육실 면적
    private Integer playGround;// 놀이터 수
    private Integer cctv;//CCTV 설치수
    private Integer teacherNum;// 선생님 수
    private Integer capacity;// 수용인원
    private Integer quota;//현원





}
