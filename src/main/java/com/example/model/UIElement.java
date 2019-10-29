package com.example.model;

import java.util.Date;
import javax.persistence.*;

@Table(name = "ui_element")
public class UIElement{
    @Id
    private Integer id;

    private String name;

    @Column(name = "byType")
    private String bytype;

    private String path;

    @Column(name = "createTime")
    private Date createtime;

    private String comment;

    @Column(name = "projectId")
    private Integer projectid;

    private String projectName;

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
     * @return byType
     */
    public String getBytype() {
        return bytype;
    }

    /**
     * @param bytype
     */
    public void setBytype(String bytype) {
        this.bytype = bytype == null ? null : bytype.trim();
    }

    /**
     * @return path
     */
    public String getPath() {
        return path;
    }

    /**
     * @param path
     */
    public void setPath(String path) {
        this.path = path == null ? null : path.trim();
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
     * @return comment
     */
    public String getComment() {
        return comment;
    }

    /**
     * @param comment
     */
    public void setComment(String comment) {
        this.comment = comment == null ? null : comment.trim();
    }

    /**
     * @return projectId
     */
    public Integer getProjectid() {
        return projectid;
    }

    /**
     * @param projectid
     */
    public void setProjectid(Integer projectid) {
        this.projectid = projectid;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }
}