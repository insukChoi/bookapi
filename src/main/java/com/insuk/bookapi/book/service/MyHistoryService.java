package com.insuk.bookapi.book.service;

import com.insuk.bookapi.book.domain.MyHistory;
import com.insuk.bookapi.book.domain.SearchBook;
import com.insuk.bookapi.book.domain.exception.CannotSaveMyHistory;
import com.insuk.bookapi.book.repository.MyHistoryRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.time.LocalDateTime;

@Service
@Slf4j
public class MyHistoryService {
    @Autowired
    private MyHistoryRepository myHistoryRepository;

    // 나의 히스토리 저장
    public MyHistory saveMyHistory(Principal user, SearchBook searchBook){
        MyHistory myHistory = MyHistory.builder()
                .userId(user.getName())
                .keyword(searchBook.getSearchKeyword())
                .searchDate(LocalDateTime.now()).build();

        MyHistory savedMyHistory = myHistoryRepository.save(myHistory);
        if (savedMyHistory == MyHistory.EMPTY) throw new CannotSaveMyHistory("나의 히스토리 등록에 실패하였습니다.");

        return savedMyHistory;
    }

    // 나의 히스토리 가져오기
    public Page<MyHistory> getMyHistoryList(Pageable pageable, Principal user){
        pageable = PageRequest.of(pageable.getPageNumber() <= 0 ? 0 : pageable.getPageNumber() -1
                , pageable.getPageSize());
        Page<MyHistory> myHistoryList = myHistoryRepository.findAllByUserIdOrderBySearchDateDesc(pageable, user.getName());
        return myHistoryList;
    }
}
