package com.fc.jpa.bookmanager.repository;

import com.fc.jpa.bookmanager.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long/*테이블의 PK 타입*/> {
    //복잡한 where절을 수행하는 기능 만들기
    //naming 규칙을 잘 알고 있어야함 (접두어를 잘 파악하기) JPQL
    //User findByName(String name);
    //jpa에서 제공하지 않는 메소드를 사용시 혹은 잘못 작성하여 사용시 runtime 오류가 발생함 따라서 JPA에서 제공해주는 메소드는 테스트하여 실행해본다

    Optional<User> findByName(String name); //return type이 고정되어있지 않다 => 데이터의 return type 에 맞춰 변형가능하다
    User streamByName(String name);
    User searchByName(String name);
    User getByName(String name);
    User queryByName(String name);
    User readByName(String name);
    User findUserByName(String name);
    List<User> findTop1ByName(String name);
    List<User> findFirst1ByName(String name);

    //and , or 조건문
    List<User> findByEmailAndName(String email, String name);
    List<User> findByEmailOrName(String email, String name);
    List<User> findByCreatedAtAfter(LocalDateTime yesterday); // createdAt보다 큰 값(After)을 조회
    List<User> findByIdBefore(Long id); // createdAt보다 작은 값(Before)을 조회
    List<User> findByCreatedAtGreaterThan(LocalDateTime yesterday); // > 보다 크다
    List<User> findByCreatedAtGreaterThanEqual(LocalDateTime yesterday); // >= 크거나 같다
    List<User> findByCreatedAtBetween(LocalDateTime yesterday, LocalDateTime tomorrow);// between yesterday ~ tomorrow
    List<User> findByIdBetween(Long id1, Long id2);// between id1~id2
    
    List<User> findByIdIsNotNull(); // where id is not null 구문

    List<User> findByAddressesIsNotEmpty(); // exist 구문이다

    List<User> findByNameIn(List<String> names);

    List<User> findByNameNotIn(List<String> names);
}
