package com.insuk.bookapi.login.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table
public class User implements Serializable {
    final static public User EMPTY = new User(null, null, null);

    @Id
    @Column(name = "user_id", nullable = false, length = 50)
    private String userId;

    @Column(nullable = false)
    private String password;

    @Column
    private LocalDateTime createdDate;

    public User(String userId, String password) {
        this.userId = userId;
        this.password = password;
    }

    public User(String userId, String password, LocalDateTime createdDate) {
        this.userId = userId;
        this.password = password;
        this.createdDate = createdDate;
    }
}
