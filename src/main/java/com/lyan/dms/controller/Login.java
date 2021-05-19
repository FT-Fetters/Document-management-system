package com.lyan.dms.controller;

import com.lyan.dms.bean.LoginUser;
import com.lyan.dms.utils.CookieUtils;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.*;
import sun.security.provider.MD5;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.nio.charset.StandardCharsets;

@RestController
public class Login {

    @PostMapping("/login")
    public String login(@RequestParam String username, @RequestParam String password, HttpSession session, HttpServletRequest request, HttpServletResponse response){
        System.out.println(username+password);
        if (username.equals("admin") && password.equals("admin")){
            String loginId = DigestUtils.md5DigestAsHex((System.currentTimeMillis() + password).getBytes(StandardCharsets.UTF_8));
            LoginUser lg = new LoginUser(loginId);
            CookieUtils.writeCookie(response,"username","admin");
            CookieUtils.writeCookie(response,"loginId",loginId);
            LoginUser.addLoginUser(username,lg);
            return "yes";
        }else {
            return "false";
        }

    }
}
