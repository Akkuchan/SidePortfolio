package com.newproject.projectn.entitiy;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
public class Kindergarten {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    long kindergartenId;

    String name;
    long latitude;
    long longtitude;
    int capacity;// 수용인원
    int quota;//현원
    String number;
    LocalDateTime startTime;
    LocalDateTime endTime;
    String type; // 유형, 국공립, 민간, 사회복지법인, 법인단체 등, 가정 Enum 으로 변경
    String active; // 운영현황 (변수명 수정 필요)
    LocalDate authorizationDate;// 인가날짜
    boolean shuttleBus;// 통학차량 운영여부
    String homepageUrl;// 홈페이지 주소
    LocalDate abolitionDate;// 폐지일자(운영여부가 폐지일 경우에만 데이터 존재)
    int teacherNum;// 선생님 수



}
