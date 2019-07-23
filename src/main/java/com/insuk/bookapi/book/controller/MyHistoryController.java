package com.insuk.bookapi.book.controller;

import com.insuk.bookapi.book.domain.MyHistory;
import com.insuk.bookapi.book.service.MyHistoryService;
import com.insuk.bookapi.login.domain.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
@EnableCaching
@Slf4j
@RequestMapping("/my/history")
public class MyHistoryController {
    @Autowired
    private MyHistoryService myHistoryService;

    @RequestMapping(value = "/list", produces = {"application/json; charset=UTF-8"})
    public ResponseEntity<Page<MyHistory>> myHistoryList(@PageableDefault Pageable pageable, Principal user){
        Page<MyHistory> myHistoryList = myHistoryService.getMyHistoryList(pageable, user);

        return new ResponseEntity<>(myHistoryList, HttpStatus.OK);
    }
}
