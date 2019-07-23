package com.insuk.bookapi.entertainment.repository;

import com.insuk.bookapi.entertainment.domain.HotKeyword;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface HotKeywordRepository extends JpaRepository<HotKeyword, Long> {
    List<HotKeyword> findTop10ByOrderByCountDesc();
    Optional<HotKeyword> findByKeyword(String keyword);
}
