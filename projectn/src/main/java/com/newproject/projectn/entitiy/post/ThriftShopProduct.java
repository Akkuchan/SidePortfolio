package com.newproject.projectn.entitiy.post;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ThriftShopProduct extends Post {

    String productName;// 제품명
    String howToDeliver;// 거래방식, 착불, 직거래
    int price;
    Boolean onSale;// 판매중 or 마감
}
