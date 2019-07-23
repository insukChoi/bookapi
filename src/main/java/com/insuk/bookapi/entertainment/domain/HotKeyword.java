package com.insuk.bookapi.entertainment.domain;

import com.insuk.bookapi.entertainment.domain.converter.AtomicIntConverter;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.concurrent.atomic.AtomicInteger;

@Setter
@Getter
@NoArgsConstructor
@Entity
@Table
public class HotKeyword {
    final static public HotKeyword EMPTY = new HotKeyword();

    @Id
    @Column(nullable = false)
    private String keyword;

    @Column
    @Convert(converter = AtomicIntConverter.class)
    private AtomicInteger count;

    public HotKeyword(String keyword, AtomicInteger count) {
        this.keyword = keyword;
        this.count = count;
    }
}
