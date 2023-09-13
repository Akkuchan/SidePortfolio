package com.newproject.projectn.entitiy;


import com.newproject.projectn.entitiy.address.Address;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Kindergarten {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long kindergartenId;

    String name;

    Enum Type;// 운영타입(민간, 국공립, 사회복지법인, 법인-단체 등, 직장, 가정
    @OneToOne
    @JoinColumn(name = "address_id")
    Address address;// 주소 + 우편번호

    Double latitude;//위도
    Double longitude;//경도

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

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }


     enum Type{
        // 운영타입(민간, 국공립, 사회복지법인, 법인-단체 등, 직장, 가정
        CHUNGBUK("민간"),
        CHUNGNAM("국공립"),
        GYEONGBUK("법인-단체 등"),
        BYEONGNAM("직장"),
        JEONNAM("가정"),
        ;


        private String type;

        Type(String type) {
            this.type = type;
        }
    }

}
