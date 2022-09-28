package com.fc.jpa.bookmanager.repository;

import com.fc.jpa.bookmanager.domain.User;
import org.assertj.core.util.Lists;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Sort;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;

    @Test
    void crud(){
        for(int i = 0 ;i < 10; i++) {
            User user = new User();
            user.setName("test"+i);
            userRepository.save(user);
        }
        List<User> users = userRepository.findAll(Sort.by(Sort.Direction.DESC, "name"));  // order by name
        List<User> users2 = userRepository.findAllById(Lists.newArrayList(1L, 2L, 3L)); // id in (,2,3)

        // saveAll
        List<User> saveUsers = new ArrayList<>();
        for(int i = 0; i < 3; i++) {
            User user = new User();
            user.setName("test"+((i+1)*10));
            saveUsers.add(user);
        }
        userRepository.saveAll(saveUsers); // 객제를 전부 DB에 저장

        List<User> users3 = userRepository.findAll(Sort.by(Sort.Direction.DESC, "name"));  // order by name
        users3.forEach(System.out::println);

        userRepository.getOne(1L);
        User user = userRepository.findById(1L).orElse(null); //findById는 Optional로 Wrapping 되어 있음, orElse 를 붙이게 되면 Entity 객체 타입으로 변경해야함
        System.out.println(user.toString());

    }

    @Test
    public void saveAndFlush(){
        userRepository.saveAndFlush(new User("test", "test@namver.com"));
        userRepository.flush(); //DB 반영시점을 변경하는 역할

    }

    @Test
    public void count(){
        long count = userRepository.count();
        System.out.println(count);
    }

    @Test
    public void exist(){ //count 쿼리를 통해 처리됨
        boolean isExist = userRepository.existsById(1L);
        System.out.println(isExist);
    }

    @Test
    public void delete(){
        userRepository.delete(userRepository.findById(1L).orElseThrow(RuntimeException::new));
        
    }
}