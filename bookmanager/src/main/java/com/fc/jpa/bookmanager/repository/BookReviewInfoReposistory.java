package com.fc.jpa.bookmanager.repository;

import com.fc.jpa.bookmanager.domain.BookReviewInfo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookReviewInfoReposistory extends JpaRepository<BookReviewInfo, Long> {
}
