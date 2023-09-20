package com.newproject.projectn.entitiy.post;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import java.util.*;

@Entity
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ParentingProduct extends Post{//육아용품 공식 기업 홍보 구역

    List<String> urlList;//제품 판매 페이지 링크

}
