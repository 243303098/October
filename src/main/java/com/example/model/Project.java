package com.example.model;

import java.util.Date;
import javax.persistence.*;

@Table(name = "project")
public class Project {
    @Id
    private Integer id;

    private String name;

    @Column(name = "createTime")
    private Date createtime;

    @Column(name = "updateTime")
    private Date updatetime;

    /**
     * 1启动，0停用
     */
    private Integer status;

    @Column(name = "userId")
    private Integer userid;

    private String url;

    private String browsertype;

    private String mail;

    /**
     * @return id
     */
    public Integer getId() {
        return id;
    }

    /**
     * @param id
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * @return name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name
     */
    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    /**
     * @return createTime
     */
    public Date getCreatetime() {
        return createtime;
    }

    /**
     * @param createtime
     */
    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }

    /**
     * @return updateTime
     */
    public Date getUpdatetime() {
        return updatetime;
    }

    /**
     * @param updatetime
     */
    public void setUpdatetime(Date updatetime) {
        this.updatetime = updatetime;
    }

    /**
     * 获取1启动，0停用
     *
     * @return status - 1启动，0停用
     */
    public Integer getStatus() {
        return status;
    }

    /**
     * 设置1启动，0停用
     *
     * @param status 1启动，0停用
     */
    public void setStatus(Integer status) {
        this.status = status;
    }

    /**
     * @return userId
     */
    public Integer getUserid() {
        return userid;
    }

    /**
     * @param userid
     */
    public void setUserid(Integer userid) {
        this.userid = userid;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getBrowserType() {
        return browsertype;
    }

    public void setBrowserType(String browsertype) {
        this.browsertype = browsertype;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }
}