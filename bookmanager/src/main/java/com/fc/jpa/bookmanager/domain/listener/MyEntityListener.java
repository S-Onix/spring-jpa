package com.fc.jpa.bookmanager.domain.listener;

import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import java.time.LocalDateTime;

public class MyEntityListener {
    /*
    @PrePersist // insert 메서드가 호출되기 전에 실행
    @PreUpdate // update 메서드 호출되기 전에 실행
    @PreRemove // delete 메서드가 호출되기 전에 실행
    @PostPersist // insert 메서드가 호출된 직후에 실행
    @PostUpdate // update 메서드가 호출된 직후에 실행
    @PostRemove // delete 메서드가 호출된 직후에 실행
    @PostLoad // select 메서드가 호출된 직후에 실행

     */

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
