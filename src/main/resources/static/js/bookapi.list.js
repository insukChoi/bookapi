var _bookSearchPage;
var _myHistoryPage;
$(function () {
    _listPage.onload();

    // 버튼: 로그아웃
    $("#btn_logout").on("click", function () {
       _listPage.logout();
    });
    
    // 버튼: 책 API 검색
    $("#btn_searchBook").on("click", function () {
        $("#nav-bookSearch-tab").trigger("click");
        _listPage.getBookApi();
    });

    // 엔터 검색
    $("#input_keyword").on("keypress", function (event) {
        if (event.keyCode === 13){
            $("#nav-bookSearch-tab").trigger("click");
            _listPage.getBookApi();
        }
    });

    // Tab 이동
    $("#nav-bookSearch-tab, #nav-mykeyword-tab, #nav-hotkeyword-tab").on("click", function (e) {
        var _this = $(this);
        $(".nav-item").each(function () {
            $(this).removeClass("active");
            $(this).attr("aria-selected", "false");
        });
        _this.addClass("active");
        _this.attr("aria-selected", "true");
        $(".tab-pane").each(function () {
            if($(this).attr("aria-labelledby") === e.target.id){
                $(this).addClass("show");
                $(this).addClass("active");
            }else{
                $(this).removeClass("show");
                $(this).removeClass("active");
            }
        });
        /* 내검색 히스토리 */
        if (e.target.id === "nav-mykeyword-tab") {
            _listPage.getMyHistory();
        }
        /* 인기 키워드 */
        if (e.target.id === "nav-hotkeyword-tab") {
            _listPage.getHotKeyword();
        }
    });
});

var _pagingConst = {
    PAGE_PER_CNT : 10,  /* 한 페이지에 보여질 도서 수 */
    BLOCK_SIZE : 10 /* 페이징 한 블럭당 갯수 */
};


var _listPage = {
    /**
     * onload시 호출되는 함수
     */
    onload : function () {

    },
    /**
     * 로그아웃 시키기
     */
    logout : function () {
        var $form = $("<form></form>");
        $form.attr("action", "/logout");
        $form.attr("method", "get");
        $form.appendTo("body");
        $form.submit();
    },
    /**
     * 페이징을 통한 도서 검색
     */
    searchApi : function(page){
        $(".page-item.book").each(function () {
            if (page == $(this).find("a").text()) $(this).addClass("active");
            else $(this).removeClass("active");
        });
        _listPage.getBookApi(page);
    },
    /**
     * 도서 API 검색
     */
    getBookApi : function (page) {
        var enterSearch = false;
        if (bookapi.nullToVoid(page) === ""){
            enterSearch = true; // 엔터 검색일 경우
            page = 1;
        }
        var searchInput = bookapi.nullToVoid($("#input_keyword").val());
        if (searchInput === ""){
            alert("키워드를 입력하세요");
            return false;
        }
        _bookSearchPage = page;
        bookapi.ajax("/book/v1/search?keyword="+searchInput+"&enterSearch="+enterSearch+
            "&page="+page+"&size"+_pagingConst.PAGE_PER_CNT, '', _listPage.callbackGetBookApi);
    },
    /**
     * 콜백, 도서 API 검색
     */
    callbackGetBookApi : function (apiDto) {
        var tHtml = "";
        $.each(apiDto.documents, function (i,v) {
            tHtml += "<tr>";
            tHtml += "<td>"+((_bookSearchPage * _pagingConst.PAGE_PER_CNT) - _pagingConst.PAGE_PER_CNT + (i+1))+"</td>";
            tHtml += "<td><a href=\"javascript:_listPage.openPopup("+encodeURIComponent(JSON.stringify(v))+")\">"+bookapi.nullToVoid(v.title)+"</a></td>";
            tHtml += "<td>"+bookapi.nullToVoid(v.publisher)+"</td>";
            tHtml += "<td style='text-align: right'>"+bookapi.comma(v.price)+"</td>";
            tHtml += "</tr>";
        });
        $("#nav-bookSearch").find("tbody").html(tHtml);

        // 페이징 처리
        _listPage.drawPaging("BookSearch", apiDto.meta.pageable_count, _bookSearchPage);

    },
    /**
     * 페이징 처리
     */
    drawPaging : function (type, totalCnt, page) {
        if (bookapi.nullToVoid(page) === "") page = 1;
        var startBlock = (parseInt((page - 1) / _pagingConst.BLOCK_SIZE) * _pagingConst.BLOCK_SIZE + 1);
        var endBlock = parseInt(startBlock + _pagingConst.BLOCK_SIZE - 1);
        var totalBlock = parseInt(totalCnt / _pagingConst.PAGE_PER_CNT);
        if (totalCnt % _pagingConst.PAGE_PER_CNT > 0) totalBlock++;
        if (endBlock > totalBlock) endBlock = totalBlock;

        /* 도서 검색 페이징 */
        if (type === "BookSearch") {
            var _pagingHtml = "";
            if(page > _pagingConst.BLOCK_SIZE){
                _pagingHtml += "<li class=\"page-item book\"><a class=\"page-link\" href=\"javascript:_listPage.searchApi("+(startBlock - 1)+")\" tabindex=\"-1\">Previous</a></li>";
            }
            for (var i = startBlock; i <= endBlock; i++){
                var activeClass = "";
                if (i === page) activeClass = "active";
                _pagingHtml += "<li class=\"page-item book "+activeClass+"\"><a class=\"page-link\" href=\"javascript:_listPage.searchApi("+i+")\">"+i+"</a></li>";
            }
            if (startBlock + _pagingConst.BLOCK_SIZE < totalBlock) {
                _pagingHtml += "<li class=\"page-item book\"><a class=\"page-link\" href=\"javascript:_listPage.searchApi("+(startBlock + _pagingConst.BLOCK_SIZE)+")\">Next</a></li>";
            }

            $("#nav_bookSearch_paging").find(".pagination").html(_pagingHtml);
        }
        /*  내 검색 히스토리 페이징 */
        else if (type === "MyHistory"){
            var _pagingHtml = "";
            if(page > _pagingConst.BLOCK_SIZE){
                _pagingHtml += "<li class=\"page-item history\"><a class=\"page-link\" href=\"javascript:_listPage.searchMyhistory("+(startBlock - 1)+")\" tabindex=\"-1\">Previous</a></li>";
            }
            for (var i = startBlock; i <= endBlock; i++){
                var activeClass = "";
                if (i === page) activeClass = "active";
                _pagingHtml += "<li class=\"page-item history "+activeClass+"\"><a class=\"page-link\" href=\"javascript:_listPage.searchMyhistory("+i+")\">"+i+"</a></li>";
            }
            if (startBlock + _pagingConst.BLOCK_SIZE < totalBlock) {
                _pagingHtml += "<li class=\"page-item history\"><a class=\"page-link\" href=\"javascript:_listPage.searchMyhistory("+(startBlock + _pagingConst.BLOCK_SIZE)+")\">Next</a></li>";
            }

            $("#nav_myHistory_paging").find(".pagination").html(_pagingHtml);
        }
    },
    /**
     * 도서 상세 조회 팝업
     */
    openPopup : function (data) {
        var _bpop = $("#bpopup1");
        _bpop.find(".text-primary").text(data.title);
        _bpop.find(".card-img-right").attr("src",data.thumbnail);
        _bpop.find(".card-text").text(data.contents);
        _bpop.find(".text-muted.small").eq(0).text(data.isbn);
        _bpop.find(".text-muted.small").eq(1).text("출판일: "+bookapi.formatDate(data.datetime, 'yyyy-MM-dd'));
        _bpop.find(".mb-0").eq(0).text("저자: "+data.authors);
        _bpop.find(".mb-0").eq(1).text("출판사: "+data.publisher);
        _bpop.find(".mb-0").eq(2).text("정가: "+bookapi.comma(data.price));
        _bpop.find(".mb-0").eq(3).text("판매가: "+bookapi.comma(data.sale_price));

        $('#bpopup1').bPopup();
    },
    /**
     * 나의 히스토리 가져오기
     */
    getMyHistory : function (page) {
        _myHistoryPage = page;
        bookapi.ajax("/my/history/list?page="+page, '', _listPage.callbackgetMyHistory)
    },
    /**
     * 콜백, 나의 히스토리 가져오기
     */
    callbackgetMyHistory : function (myHistory) {
        var tHtml = "";
        $.each(myHistory.content, function (i,v) {
            tHtml += "<tr>";
            tHtml += "<td>"+bookapi.formatDate(v.searchDate, 'yyyy-MM-dd a/p hh:mm:ss')+"</td>";
            tHtml += "<td>"+v.keyword+"</td>";
            tHtml += "</tr>";
        });
        $("#nav-mykeyword").find("tbody").html(tHtml);

        // 페이징 처리
        _listPage.drawPaging("MyHistory", myHistory.totalElements, _myHistoryPage);
    },
    /**
     * 페이징을 통한 내 검색 히스토리
     */
    searchMyhistory : function(page){
        $(".page-item.history").each(function () {
            if (page == $(this).find("a").text()) $(this).addClass("active");
            else $(this).removeClass("active");
        });
        _listPage.getMyHistory(page);
    },
    /**
     * 핫 키워드 가져오기
     */
    getHotKeyword : function () {
        bookapi.ajax("/hot/keyword/list", '', _listPage.callbackgetHotKeyword)
    },
    /**
     * 콜백, 나의 히스토리 가져오기
     */
    callbackgetHotKeyword : function (hotkeword) {
        var tHtml = "";
        $.each(hotkeword, function (i,v) {
            tHtml += "<tr>";
            tHtml += "<td>"+v.keyword+"</td>";
            tHtml += "<td>"+bookapi.comma(v.count)+"</td>";
            tHtml += "</tr>";
        });
        $("#nav-hotkeyword").find("tbody").html(tHtml);
    }
};