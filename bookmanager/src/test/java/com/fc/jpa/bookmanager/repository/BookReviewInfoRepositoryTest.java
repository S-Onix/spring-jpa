package com.fc.jpa.bookmanager.repository;

import com.fc.jpa.bookmanager.domain.Book;
import com.fc.jpa.bookmanager.domain.BookReviewInfo;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;


@SpringBootTest
class BookReviewInfoRepositoryTest {

    @Autowired
    private BookRepository bookRepository;
    @Autowired
    private BookReviewInfoRepository bookReviewInfoRepository;

    @Test
    public void crudTest(){
        BookReviewInfo bookReviewInfo = new BookReviewInfo();
        bookReviewInfo.setBookId(1L);
        bookReviewInfo.setAverageReviewScore(4.5f);
        bookReviewInfo.setReviewCount(2);

        bookReviewInfoRepository.save(bookReviewInfo);
        bookReviewInfoRepository.findAll().forEach(System.out::println);
    }

    @Test
    public void crudTest2(){
        Book book = new Book();
        book.setName("test");
        book.setAuthorId(1L);
        book.setPublisherId(1L);

        bookRepository.save(book);

        BookReviewInfo bookReviewInfo = new BookReviewInfo();
        bookReviewInfo.setBookId(1L);
        bookReviewInfo.setAverageReviewScore(4.5f);
        bookReviewInfo.setReviewCount(2);

        bookReviewInfoRepository.save(bookReviewInfo);

        System.out.println(bookReviewInfoRepository.findAll());

        //Optional이기 때문에 반드시 orElseThrow를 해줘야함
        Book result = bookRepository.findById(
            bookReviewInfoRepository
                    .findById(1L)
                    .orElseThrow(RuntimeException::new)
                    .getBookId()
        ).orElseThrow(RuntimeException::new);


        System.out.println(result);
    }
}