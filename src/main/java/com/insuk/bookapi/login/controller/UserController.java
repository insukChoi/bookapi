package com.insuk.bookapi.login.controller;

import com.insuk.bookapi.login.domain.User;
import com.insuk.bookapi.login.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class UserController {
    @Autowired
    UserService userService;

    @PostMapping("/userRegister")
    public String createUser(User user, RedirectAttributes redirectAttributes){
        User savedUser = userService.SaveUser(user);

        String message = "중복되는 아이디입니다.";
        if (!savedUser.equals(User.EMPTY)) message = "회원가입 완료하였습니다.";
        redirectAttributes.addAttribute("message", message);
        
        return "redirect:/login";
    }

}
