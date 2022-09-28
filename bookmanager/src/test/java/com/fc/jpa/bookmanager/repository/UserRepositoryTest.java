package com.fc.jpa.bookmanager.repository;

import com.fc.jpa.bookmanager.domain.User;
import org.assertj.core.util.Lists;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.*;

import java.util.ArrayList;
import java.util.List;

import static org.springframework.data.domain.ExampleMatcher.GenericPropertyMatchers.endsWith;

@SpringBootTest
class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;

    public void init(){
        List<User> saveUsers = new ArrayList<>();
        for(int i = 0; i < 20; i++) {
            User user = new User();
            user.setName("test"+((i+1)*10));
            user.setEmail(user.getName()+"@naver.com");
            saveUsers.add(user);
        }
        userRepository.saveAll(saveUsers);

    }

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

        userRepository.deleteAll(); // data의 개수만큼 select 후 삭제하기 때문에 성능 이슈가 발생함 (대용량의 데이터일 경우)
        userRepository.deleteAllInBatch(); // delete 쿼리만 한번 실행함
    }

    //페이징처리 방법
    @Test
    public void paging(){
        init();
        //0베이스로 시작하기 때문에 2 page가 리턴된다.
        Page<User> users = userRepository.findAll(PageRequest.of(1, 3));
        System.out.println("page : " + users);
        System.out.println("total Elements : " + users.getTotalElements()); // 총갯수
        System.out.println("total pages : " + users.getTotalPages());
        System.out.println("number of elements : " + users.getNumberOfElements());
        System.out.println("sort : " + users.getSort());
        System.out.println("size : " + users.getSize());
        users.getContent().forEach(System.out::println);
    }

    @Test //query by example(QBE) : entity를 example로 만들고 matcher를 추가하여 선언함으로 필요한 쿼리를 만드는 방법
    public void qbe(){
        init();
        ExampleMatcher matcher = ExampleMatcher.matching()
                .withIgnorePaths("name")
                .withMatcher("email", endsWith());
        //ExampleMatcher가 있을 경우 like 검색, 없을경우 exact 검생
        Example<User> example = Example.of(new User("sonix", "naver.com"), matcher); //probe(첫번째 파라미터)는 matcher 에 따라 조회할 조건을 의미하는 가짜 Entity
        userRepository.findAll(example).forEach(System.out::println);


    }

}