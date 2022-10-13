package com.fc.jpa.bookmanager.repository;

import com.fc.jpa.bookmanager.domain.BookReviewInfo;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;


@SpringBootTest
class BookReviewInfoReposistoryTest {

    @Autowired
    private BookReviewInfoReposistory bookReviewInfoReposistory;

    @Test
    public void crudTest(){
        BookReviewInfo bookReviewInfo = new BookReviewInfo();
        bookReviewInfo.setBookId(1L);
        bookReviewInfo.setAverageReviewScore(4.5f);
        bookReviewInfo.setReviewCount(2);

        bookReviewInfoReposistory.save(bookReviewInfo);

        bookReviewInfoReposistory.findAll().forEach(System.out::println);
    }
}