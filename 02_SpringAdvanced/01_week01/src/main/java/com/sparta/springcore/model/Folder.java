package com.sparta.springcore.model;
import com.sparta.springcore.dto.ProductRequestDto;
import com.sparta.springcore.validator.ProductValidator;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Setter
@Getter // get 함수를 일괄적으로 만들어줍니다.
@NoArgsConstructor // 기본 생성자를 만들어줍니다.
@Entity // DB 테이블 역할을 합니다.
public class Folder {

    // ID가 자동으로 생성 및 증가합니다.
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    private Long id;

    @Column(nullable = false)
    private String name;

    @ManyToOne
    @JoinColumn(name="USER_ID",nullable = false)
    private User user;

    //생성할 폴더이름과 사용자정보를 가져온다.
    public Folder(String name, User user) {
        this.name = name;
        this.user = user;
    }
}