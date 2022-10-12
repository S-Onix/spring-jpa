package com.fc.jpa.bookmanager.repository;

import com.fc.jpa.bookmanager.domain.Book;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class BookRepositoryTest {
    @Autowired
    BookRepository bookRepository;

    @Test
    void bookTest(){
        Book book = new Book();
        book.setName("book");
        book.setAuthor("test");

        bookRepository.save(book);
        System.out.println(bookRepository.findById(1L));
    }
}