package com.fc.jpa.bookmanager.domain.listener;

import com.fc.jpa.bookmanager.domain.Auditable;

import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import java.time.LocalDateTime;

public class MyEntityListener {
    @PrePersist // Listener에서는 메개변수가 반드시 필요하다.
    public void prePersist(Object o){
        if(o instanceof Auditable) {
            ((Auditable) o).setCreatedAt(LocalDateTime.now());
            ((Auditable) o).setUpdatedAt(LocalDateTime.now());
        }

    }

    @PreUpdate
    public void preUpdate(Object o){
        if(o instanceof Auditable){
            ((Auditable) o).setUpdatedAt(LocalDateTime.now());
        }
    }
}
