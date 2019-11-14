package com.example.model;

import javax.persistence.*;

@Table(name = "ui_module")
public class UIModule {
    @Id
    private Integer id;

    @Column(name = "moduleName")
    private String modulename;

    private String status;

    private String comment;

    @Column(name = "paramData")
    private String paramdata;

    @Column(name = "returnData")
    private String returndata;

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
     * @return moduleName
     */
    public String getModulename() {
        return modulename;
    }

    /**
     * @param modulename
     */
    public void setModulename(String modulename) {
        this.modulename = modulename == null ? null : modulename.trim();
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
     * @return paramData
     */
    public String getParamdata() {
        return paramdata;
    }

    /**
     * @param paramdata
     */
    public void setParamdata(String paramdata) {
        this.paramdata = paramdata == null ? null : paramdata.trim();
    }

    /**
     * @return returnData
     */
    public String getReturndata() {
        return returndata;
    }

    /**
     * @param returndata
     */
    public void setReturndata(String returndata) {
        this.returndata = returndata == null ? null : returndata.trim();
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