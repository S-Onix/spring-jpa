package com.fc.jpa.bookmanager.domain;

import com.fc.jpa.bookmanager.domain.listener.Auditable;
import com.fc.jpa.bookmanager.domain.listener.MyEntityListener;
import com.fc.jpa.bookmanager.domain.listener.UserEntityListener;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@RequiredArgsConstructor
@Data
@Builder
@Entity // JPA에서 관리하고 있는 객체임을 선언 , DB테이블과 연결될 자바객체라는 것을 선언해주는 어노테이션
@Table(name ="USER_TABLE", indexes = {@Index(columnList = "name")}, uniqueConstraints = {@UniqueConstraint(columnNames = "email")})
@EntityListeners(value = {MyEntityListener.class, UserEntityListener.class}) //UserHistory 와 비교해보자
public class User extends BaseEntity{
    @Id // 테이블의 primary key로 지정
    @GeneratedValue(strategy = GenerationType.IDENTITY)  //숫자는 자동으로 증가됨 index의 역할
    private long id;

    @NonNull
    private String name;

    @NonNull
    private String email;

    //Enum을 사용하는 경우 반드시 타입을 EnumType.STRING로 변경한다 안그러면 Index 번호로 저장됨 (출력도 index에 따라 맞춰나옴)
    @Enumerated(value = EnumType.STRING)
    private Gender gender;

    @Column(updatable = false) // update시 해당 컬럼은 반영하지 않음을 의미
    private LocalDateTime createdAt;

    @Column(insertable = true) // insert시 해당 컬럼은 반영하지 않음을 의미
    private LocalDateTime updatedAt;

    @Transient //영속성 처리에서 제외됨 따라서 DB Data에 반영되지 않고 해당 객체의 생명주기를 같이하는 값임을 의미함
    private String testData;

/*    @OneToMany(fetch = FetchType.EAGER)
    private List<Address> addresses;*/
}
