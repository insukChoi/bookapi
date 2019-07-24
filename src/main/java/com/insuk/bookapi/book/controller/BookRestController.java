package com.insuk.bookapi.book.controller;

import com.insuk.bookapi.book.domain.SearchBook;
import com.insuk.bookapi.book.dto.ApiDtoParents;
import com.insuk.bookapi.book.dto.kakao.ApiDto;
import com.insuk.bookapi.book.service.BookService;
import com.insuk.bookapi.book.service.MyHistoryService;
import com.insuk.bookapi.entertainment.service.HotKeywordService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.security.Principal;

@RestController
@EnableCaching
@Slf4j
@RequestMapping("/book/v1")
public class BookRestController {
    @Autowired
    private BookService bookService;

    @Autowired
    private MyHistoryService myHistoryService;

    @Autowired
    private HotKeywordService hotKeywordService;

    @RequestMapping(value = "/search", produces = {"application/json; charset=UTF-8"})
    public ResponseEntity<ApiDtoParents> bookSearch(@ModelAttribute @Valid SearchBook searchBook,
                                             @RequestParam(value = "enterSearch", required = false, defaultValue = "false") boolean isEnterSearch,
                                             BindingResult result, Principal user){
        log.info("keyword = " + searchBook.getSearchKeyword());
        log.info("userId = " + user.getName());

        // 입력 파라미터 검증
        if (result.hasErrors()){
            return ResponseEntity.badRequest().body(ApiDto.EMPTY);
        }

        if (isEnterSearch){
            // 나의 히스토리 등록
            myHistoryService.saveMyHistory(user, searchBook);
            // 핫 키워드 등록
            hotKeywordService.saveHotKeyword(searchBook);
        }

        // 책 검색 API 호출
        ApiDtoParents apiDto = bookService.getSearchBookApi(searchBook);

        return new ResponseEntity<>(apiDto, HttpStatus.OK);
    }
}
