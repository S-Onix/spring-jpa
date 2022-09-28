package com.fc.jpa.bookmanager.domain;

import lombok.*;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.*;
import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@RequiredArgsConstructor
@Data
@Builder
@Entity // DB테이블과 연결될 자바객체라는 것을 선언해주는 어노테이션
@Table(name ="USER_TABLE")
public class User {
    @Id // 테이블의 primary key로 선언
    @GeneratedValue(strategy = GenerationType.IDENTITY)  //숫자는 자동으로 증가됨 index의 역할
    private long id;

    @NonNull
    private String name;

    @NonNull
    private String email;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAp;

}
