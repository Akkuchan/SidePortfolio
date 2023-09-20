package com.newproject.projectn.entitiy;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.newproject.projectn.entitiy.Enum.UserGrade;
import com.newproject.projectn.entitiy.address.Address;
import com.newproject.projectn.entitiy.basetime.BaseTimeEntity;
import com.newproject.projectn.entitiy.post.Post;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Pattern;
import lombok.*;

import java.util.*;
@Entity
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long userId;

    @Column
    private String username;// 로그인 아이디
    @Email
    @Column
    private String email;//유저의 이메일
    @Column
    private String nickName;//유저 닉네임
    @Column
    private String password;//유저 비밀번호
    @Pattern( regexp = "^01(?:0|1|[6-9])[.-]?(\\d{3}|\\d{4})[.-]?(\\d{4})$", message = "10 ~ 11 자리의 숫자만 입력 가능합니다.")
    @Column
    private String phoneNumber;

//    @Column
//    private LocalDate registrationDate;// 가입일
//    @Column
//    private LocalDateTime latestLogin; // 최근 로그일
    @Column
    private String image;//프로필 이미지

    @Enumerated(value = EnumType.STRING)
    @Column
    private UserGrade userType; // 유저타입, 학부모, 선생님, 관리자 Enum으로 변경필요


    @OneToOne
    @JoinColumn(name = "address_ID")
    @JsonIgnore
    private Address address;

    private Boolean emailAvailable;// 이메일 수신여부
    private Boolean smsAvailable;// sms 수신여부
    private Boolean isMarried;// 기혼여부
    private Boolean isPregnant;// 임신여부
    private Boolean hasChild;// 자녀여부

    @OneToMany
    private List<Waiting> waitingList;// 최대 3개까지 가능

    @OneToMany
    private List<Comment> CommentList;


    @OneToMany(fetch = FetchType.LAZY)
    private List<Post> postList = new ArrayList<>();




}
