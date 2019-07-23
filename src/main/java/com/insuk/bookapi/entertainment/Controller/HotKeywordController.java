package com.insuk.bookapi.entertainment.Controller;

import com.insuk.bookapi.entertainment.domain.HotKeyword;
import com.insuk.bookapi.entertainment.service.HotKeywordService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@EnableCaching
@Slf4j
@RequestMapping("/hot/keyword")
public class HotKeywordController {
    @Autowired
    private HotKeywordService hotKeywordService;

    @RequestMapping(value = "/list", produces = {"application/json; charset=UTF-8"})
    public ResponseEntity<List<HotKeyword>> hotkewordList(){
        List<HotKeyword> hotkewordList = hotKeywordService.getHotkeywordList();

        return new ResponseEntity<>(hotkewordList, HttpStatus.OK);
    }

}
