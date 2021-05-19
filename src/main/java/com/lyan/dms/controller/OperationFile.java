package com.lyan.dms.controller;


import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.lyan.dms.utils.FileUtils;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;
import java.io.IOException;

@RestController
public class OperationFile {


    /**
     * 删除文件api
     * @param loginId 用户id
     * @param username 用户名
     * @param data 传入的参数
     * @return 是否删除成功
     */
    @PostMapping("/DeleteFile")
    public boolean deleteFile(@CookieValue(value = "loginId",defaultValue = "")String loginId,
                              @CookieValue(value = "username",defaultValue = "")String username,
                              @RequestBody JSONObject data){
        JSONArray files = data.getJSONArray("files");
        String basePath = System.getProperty("user.dir") + "\\UserFile" + "\\"+username+"\\"+data.get("path");
        System.out.println(data.get("path"));
        for (Object file : files) {
            JSONObject fileName = (JSONObject) file;
            File tmp = new File(basePath + fileName.get("name"));
            System.out.println(tmp.getPath());
            if (!tmp.delete()) {
                return false;
            }
        }
        return true;
    }

    /**
     * 复制文件api
     * @param loginId 用户id
     * @param username 用户名
     * @param data 传入的数据
     * @return 复制是否成功
     */
    @PostMapping("/CopyFile")
    public boolean copyFile(@CookieValue(value = "loginId",defaultValue = "")String loginId,
                            @CookieValue(value = "username",defaultValue = "")String username,
                            @RequestBody JSONObject data){
        boolean shear = data.getBoolean("shear");
        JSONArray files = data.getJSONArray("files");
        String targetPath = data.getString("targetPath");
        targetPath = System.getProperty("user.dir") + "\\UserFile" + "\\"+username + "\\" + targetPath;
        String basePath = System.getProperty("user.dir") + "\\UserFile" + "\\"+username;
        for (Object file : files) {
            File tmpFile = new File(basePath + "\\" + file);
            String fileName = file.toString().substring(file.toString().lastIndexOf("/") == -1 ? 0 : file.toString().lastIndexOf("/") );
            File target = new File(targetPath + fileName);
            try {
                FileUtils.copy(tmpFile,target);
                if (shear){
                    tmpFile.delete();
                }
            } catch (IOException e) {
                e.printStackTrace();
                return false;
            }
        }
        return true;
    }




}
