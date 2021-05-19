package com.lyan.dms.bean;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

public class SubFile {
    private String name;
    private long size;//单位为KB
    private boolean folder;//是否为文件夹
    private long modificationDate;//修改日期

    public void setName(String name) {
        this.name = name;
    }

    public void setSize(long size) {
        this.size = size;
    }

    public void setModificationDate(long modificationDate) {
        this.modificationDate = modificationDate;
    }

    public void setFolder(boolean folder) {
        this.folder = folder;
    }


    public boolean isFolder() {
        return folder;
    }

    public String getName() {
        return name;
    }

    public long getSize() {
        return size;
    }

    public long getModificationDate() {
        return modificationDate;
    }
}
