package com.fc.jpa.bookmanager.domain;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Address {
    private Long id;

    public void setId(Long id) {
        this.id = id;
    }

    @Id
    public Long getId() {
        return id;
    }
}
