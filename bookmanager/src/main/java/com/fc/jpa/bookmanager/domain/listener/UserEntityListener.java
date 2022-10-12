package com.fc.jpa.bookmanager.domain.listener;

import com.fc.jpa.bookmanager.domain.User;
import com.fc.jpa.bookmanager.domain.UserHistory;
import com.fc.jpa.bookmanager.repository.UserHistoryRepository;
import com.fc.jpa.bookmanager.support.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;

public class UserEntityListener {

    @PrePersist
    @PreUpdate
    public void preUpdate(Object o){
        //bean 설정을 통해 spring에 등록시켜줌
        UserHistoryRepository userHistoryRepository = BeanUtils.getBeans(UserHistoryRepository.class);


        User user = (User)o;
        UserHistory userHistory = new UserHistory();
        userHistory.setUserId(user.getId());
        userHistory.setName(user.getName());
        userHistory.setEmail(user.getEmail());

        userHistoryRepository.save(userHistory);
    }
}
