$(function () {
    _loginPage.onload();

    // 버튼 : 로그인 or 회원가입 클릭시
    $("#btn_login, #btn_register").off("click").on("click", function () {
        if ($(event.target).attr("id") === 'btn_login') {
            _loginPage.changeLoginForm();
        }else if ($(event.target).attr("id") === 'btn_register') {
            _loginPage.changeRegisterForm();
        }
    });
});


var _loginPage = {
    /**
     * onload시 호출되는 함수
     */
    onload : function () {

    },
    /**
     * Input 비우기
     */
    cleanInputTxt(){
        $("input[name='userId'] , input[name='password']").val("");
        $("h5").text("");
    },
    /**
     * 로그인 폼으로 변경
     */
    changeLoginForm : function () {
        _loginPage.cleanInputTxt();
        $("#div_header").html("Login");
        $("#btn_submit").text("Login");
        $("form").attr("action", "loginProcess");
    },
    /**
     * 회원가입 폼으로 변경
     */
    changeRegisterForm : function () {
        _loginPage.cleanInputTxt();
        $("#div_header").html("Register");
        $("#btn_submit").text("Register");
        $("form").attr("action", "userRegister");
    }
};