package com.insuk.bookapi.entertainment.service;

import com.insuk.bookapi.book.domain.SearchBook;
import com.insuk.bookapi.entertainment.domain.HotKeyword;
import com.insuk.bookapi.entertainment.repository.HotKeywordRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicInteger;

@Service
@Slf4j
public class HotKeywordService {
    @Autowired
    private HotKeywordRepository hotKeywordRepository;

    // 인기 키워드 등록 및 업데이트
    public HotKeyword saveHotKeyword(SearchBook searchBook){
        HotKeyword savedHotkeyword;
        Optional<HotKeyword> existsHotKeyword = hotKeywordRepository.findByKeyword(searchBook.getSearchKeyword());
        if (existsHotKeyword.isPresent()){
            // 이미 keyword 가 있으면 카운트 증가 후 업데이트
            HotKeyword hotKeyword = existsHotKeyword.get();
            hotKeyword.setCount(new AtomicInteger(hotKeyword.getCount().incrementAndGet()));

            savedHotkeyword = hotKeywordRepository.save(hotKeyword);
        }else{
            // keyword 가 없으면 등록
            HotKeyword hotKeyword = new HotKeyword(searchBook.getSearchKeyword(), new AtomicInteger(1));
            savedHotkeyword = hotKeywordRepository.save(hotKeyword);
        }

        return savedHotkeyword;
    }

    // 나의 히스토리 가져오기
    public List<HotKeyword> getHotkeywordList(){
        List<HotKeyword> hotKeywordList = hotKeywordRepository.findTop10ByOrderByCountDesc();
        return hotKeywordList;
    }
}
