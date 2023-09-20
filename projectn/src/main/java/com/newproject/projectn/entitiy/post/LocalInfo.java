package com.newproject.projectn.entitiy.post;


import com.newproject.projectn.entitiy.address.City;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class LocalInfo extends Post{// 지역관련 이야기를 나누는 POST 하위 엔티티

    @ManyToOne // 지역정보
    City city;
}
