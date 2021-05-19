package com.lyan.dms.controller;


import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.lyan.dms.bean.LoginUser;
import com.lyan.dms.utils.CookieUtils;
import org.apache.logging.log4j.spi.NoOpThreadContextMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

@RestController
public class UploadFile {

    @RequestMapping("/upload")
    public static JSONObject uploadFile(@RequestParam MultipartFile file,@RequestParam JSONArray path,HttpServletRequest request, HttpServletResponse response){
        String username = CookieUtils.getCookie(request,"username");
        String loginId = CookieUtils.getCookie(request,"loginId");
        String uploadPath = "";
        for (Object o : path) {
            String tmp = (String) o;
            uploadPath += tmp+"\\";
        }
        System.out.println(username+","+loginId);
        //判断用户是否登录或者用户名和loginId不匹配
        if (username == null || loginId == null || !LoginUser.getLoginUsers().get(username).getLoginId().equals(loginId)){
            JSONObject notLogin = new JSONObject();
            notLogin.put("msg","Not login");
            return notLogin;
        }
        //判断上传的文件是否为空
        if (file.isEmpty()){
            JSONObject fileEmpty = new JSONObject();
            fileEmpty.put("msg","Empty file");
            return fileEmpty;
        }
        String current_date = new SimpleDateFormat("/yyyy/MM/dd/").format(new Date());
        String basePath = System.getProperty("user.dir") + "\\UserFile" + "\\"+username;
        String filePath = basePath +uploadPath+ file.getOriginalFilename();
        try{
            File destFile = new File(filePath);
            file.transferTo(destFile);
            JSONObject uploadSuccess = new JSONObject();
            uploadSuccess.put("msg","Upload success");
            return uploadSuccess;
        } catch (IOException e) {
            e.printStackTrace();
            JSONObject uploadError = new JSONObject();
            uploadError.put("msg","Upload Error");
            return uploadError;
        }


    }

}
