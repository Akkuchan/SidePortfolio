package com.newproject.projectn.dto.kindergarten;

import com.newproject.projectn.entitiy.address.Address;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalTime;

@NoArgsConstructor
@Getter
@Setter
@AllArgsConstructor
public class PatchKindergartenDto {
    Long kindergartenId;
    String name;
    String type;// 운영타입(민간, 국공립, 사회복지법인, 법인-단체 등, 직장, 가정
     Long cityId;// 주소 + 우편번호
     String details;
    String zipcode;
    Boolean available;// 정상 운영여부
    String number;// 전화번호
    LocalTime startTime;
    LocalTime endTime;
    LocalDate authorizationDate;// 인가날짜
    Boolean shuttleBus;// 통학차량 운영여부
    String homepageUrl;// 홈페이지 주소
    LocalDate abolitionDate;// 폐지일자(운영여부가 폐지일 경우에만 데이터 존재)


    Integer classNum; //보육실수
    Integer classArea;// 보육실 면적
    Integer playGround;// 놀이터 수
    Integer cctv;//CCTV 설치수
    Integer teacherNum;// 선생님 수
    Integer capacity;// 수용인원
    Integer quota;//현원

}
