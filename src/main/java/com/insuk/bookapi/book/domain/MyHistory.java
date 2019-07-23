package com.insuk.bookapi.book.domain;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table
public class MyHistory implements Serializable {
    final static public MyHistory EMPTY = new MyHistory();

    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idx;

    @Column(name = "user_id", nullable = false)
    private String userId;

    @Column(nullable = false)
    private String keyword;

    @Column
    private LocalDateTime searchDate;
}
