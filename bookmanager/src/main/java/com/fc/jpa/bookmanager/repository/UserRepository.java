package com.fc.jpa.bookmanager.repository;

import com.fc.jpa.bookmanager.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

public interface UserRepository extends JpaRepository<User, Long/*테이블의 PK 타입*/> {

}
