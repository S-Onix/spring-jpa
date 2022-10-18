package com.fc.jpa.bookmanager.domain;

import com.fc.jpa.bookmanager.domain.listener.Auditable;
import com.fc.jpa.bookmanager.domain.listener.MyEntityListener;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@NoArgsConstructor
@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class Book extends BaseEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    private String name;
    private String category;
    private Long authorId;
    private Long publisherId;

}
