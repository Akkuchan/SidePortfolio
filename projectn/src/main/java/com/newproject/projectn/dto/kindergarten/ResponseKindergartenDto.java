package com.newproject.projectn.dto.kindergarten;

import lombok.*;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ResponseKindergartenDto {
    Long kindergartenId;
    String state;
    String city;
    String name;
    String type;
    String number;// 전화번호
    Double latitude;//위도
    Double longitude;//경도

}
