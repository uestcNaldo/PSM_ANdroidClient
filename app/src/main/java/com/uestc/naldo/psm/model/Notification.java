package com.uestc.naldo.psm.model;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.ToMany;

import java.util.List;
import org.greenrobot.greendao.annotation.Generated;

/**
 * Created by Naldo on 2017/5/11.
 */
@Entity
public class Notification {
    @Id
    private long id;
    private String title;
    private String content;
    private String datetime;

    private long adminId;

    @Generated(hash = 924305390)
    public Notification(long id, String title, String content, String datetime,
            long adminId) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.datetime = datetime;
        this.adminId = adminId;
    }

    @Generated(hash = 1855225820)
    public Notification() {
    }

    public long getId() {
        return this.id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitle() {
        return this.title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return this.content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getDatetime() {
        return this.datetime;
    }

    public void setDatetime(String datetime) {
        this.datetime = datetime;
    }

    public long getAdminId() {
        return this.adminId;
    }

    public void setAdminId(long adminId) {
        this.adminId = adminId;
    }


}
