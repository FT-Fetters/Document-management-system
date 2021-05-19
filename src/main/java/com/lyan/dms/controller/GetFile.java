package com.lyan.dms.controller;

import com.lyan.dms.bean.LoginUser;
import com.lyan.dms.bean.SubFile;
import org.springframework.web.bind.annotation.*;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


@RestController
public class GetFile {
    @GetMapping("/GetFile")
    public List<SubFile> getFile(@CookieValue(value = "loginId",defaultValue = "")String loginId,
                          @CookieValue(value = "username",defaultValue = "")String username, @RequestParam String getPath){
        HashMap<String, LoginUser> loginUserHashMap = LoginUser.getLoginUsers();
        System.out.println("username:"+username+",loginId:"+loginId);
        if (loginUserHashMap.containsKey(username) && loginUserHashMap.get(username).getLoginId().equals(loginId)){
            String path = System.getProperty("user.dir") + "\\UserFile";
            File userFileDir = new File(path+"\\"+username);
            if (userFileDir.exists()){
                File getUserPath = new File(userFileDir.getPath() + getPath);
                File[] files = getUserPath.listFiles();
                List<SubFile> resFiles = new ArrayList<>()  ;
                assert files != null;
                for (File fFile : files) {
                    SubFile tmp = new SubFile();
                    tmp.setName(fFile.getName());
                    tmp.setSize(fFile.length());
                    tmp.setModificationDate(fFile.lastModified());
                    if (fFile.isDirectory())tmp.setFolder(true);
                    else tmp.setFolder(false);
                    resFiles.add(tmp);
                }
                return resFiles;
            }else {
                userFileDir.mkdir();
                return null;
            }
        }else {
            return null;
        }
    }
}
