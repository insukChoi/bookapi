$(function () {
   "use strict";

   var bookapi = {};
   // 전역 객체로 선언
   window.bookapi = bookapi;

    /**
     * @description Null 여부 체크
     * @param data
     * @return Boolean
     */
   bookapi.isNull = function (data) {
       if (data === null || data === undefined) return true;
       else return false;
   };
    /**
     * @description data 가 null 이면 void 반환, 그렇지 않은 경우 data 그대로 반환
     * @param data
     * @return String
     */
   bookapi.nullToVoid = function (data) {
        return bookapi.isNull(data) ? "" : data;
   };
    /**
     * @description 3자리마다 콤마 함수
     * @param data
     * @return String
     */
   bookapi.comma = function(data) {
       data = bookapi.nullToVoid(data);
       if (Number(data) < 0) return "";
       data = String(data);
       return data.replace(/(\d)(?=(?:\d{3})+(?!\d))/g, '$1,');
   };
    /**
     * @description ajax call 공통 함수
     * @param targetUrl, data, callback
     */
    bookapi.ajax = function(targetUrl, data, callback){
        $.ajax({
            dataType: "json",
            url: targetUrl,
            type : "post",
            cache: false,
            data : data,
            success: function(response){
                callback(response);
            },
            error: function (msg) {
                alert("API 호출이 실패하였습니다. " + msg.toString());
            }
        });
    };
    /**
     * @description Date 날짜 형식 포맷 함수
     * @param date, format
     * @return format Date String
     */
    bookapi.formatDate = function (date, format) {
        if (bookapi.isNull(date)) return date;
        var d = new Date(date);
        var h;
        var weekName = ["일요일", "월요일", "화요일", "수요일", "목요일", "금요일", "토요일"];
        return format.replace(/(yyyy|yy|MM|dd|E|HH|hh|mm|SSS|ss|a\/p)/g, function($1) {
            switch ($1) {
                case "yyyy": return d.getFullYear();
                case "yy": return (d.getFullYear() % 1000).zf(2);
                case "MM": return (d.getMonth() + 1).zf(2);
                case "dd": return d.getDate().zf(2);
                case "E": return weekName[d.getDay()];
                case "HH": return d.getHours().zf(2);
                case "hh": return ((h = d.getHours() % 12) ? h : 12).zf(2);
                case "mm": return d.getMinutes().zf(2);
                case "ss": return d.getSeconds().zf(2);
                case "a/p": return d.getHours() < 12 ? "오전" : "오후";
                case "SSS":
                    var temp = "0000";
                    var milliSeconds = d.getMilliseconds();
                    temp = temp + milliSeconds;
                    temp = temp.substring ( temp.length - 3 , temp.length);
                    return temp;
                default: return $1;
            }
        });
    }

});

String.prototype.string = function (len) { var s = '', i = 0; while (i++ < len) { s += this; } return s; };
String.prototype.zf = function (len) { return "0".string(len - this.length) + this; };
Number.prototype.zf = function (len) { return this.toString().zf(len); };
