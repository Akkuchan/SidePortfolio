package com.newproject.projectn.entitiy;


import com.newproject.projectn.entitiy.Enum.address.Address;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
public class Kindergarten {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long kindergartenId;

    String name;

    String type;// 운영타입(민간, 국공립, 사회복지법인, 법인-단체 등, 직장, 가정
    @OneToOne
    @JoinColumn(name = "address_id")
    Address address;// 주소 + 우편번호
    long latitude;//위도
    long longtitude;//경도

    boolean available;// 정상 운영여부
    String number;// 전화번호
    LocalDateTime startTime;
    LocalDateTime endTime;
    LocalDate authorizationDate;// 인가날짜
    boolean shuttleBus;// 통학차량 운영여부
    String homepageUrl;// 홈페이지 주소
    LocalDate abolitionDate;// 폐지일자(운영여부가 폐지일 경우에만 데이터 존재)


    int classNum; //보육실수
    int classArea;// 보육실 면적
    int playGround;// 놀이터 수
    int cctv;//CCTV 설치수
    int teacherNum;// 선생님 수
    int capacity;// 수용인원
    int quota;//현원

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }


}
