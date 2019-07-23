package com.insuk.bookapi.book.repository;

import com.insuk.bookapi.book.domain.MyHistory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MyHistoryRepository extends JpaRepository<MyHistory, Long> {
    Page<MyHistory> findAllByUserIdOrderBySearchDateDesc(Pageable pageable, String userId);
}
