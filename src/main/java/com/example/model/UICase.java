package com.example.model;

import javax.persistence.*;

@Table(name = "ui_case")
public class UICase {
    @Id
    private Integer id;

    private String name;

    @Column(name = "moduleId")
    private String moduleid;

    private String status;

    @Column(name = "projectId")
    private Integer projectid;

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
     * @return moduleId
     */
    public String getModuleid() {
        return moduleid;
    }

    /**
     * @param moduleid
     */
    public void setModuleid(String moduleid) {
        this.moduleid = moduleid == null ? null : moduleid.trim();
    }

    /**
     * @return status
     */
    public String getStatus() {
        return status;
    }

    /**
     * @param status
     */
    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
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
}