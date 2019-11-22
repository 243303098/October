package com.example.model;

import javax.persistence.*;

@Table(name = "ui_step")
public class UIStep {
    @Id
    private Integer id;

    private Integer sort;

    private String name;

    @Column(name = "elementId")
    private Integer elementid;

    @Column(name = "dataKey")
    private String datakey;

    @Column(name = "actionId")
    private String actionid;

    private String comment;

    @Column(name = "moduleId")
    private Integer moduleid;

    private String uiElementName;

    private String uiElementByType;

    private String uiElementPath;

    private String uiElementComment;

    private String url;

    private String browsertype;

    private String actionKey;

    private String actionType;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
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
     * @return elementId
     */
    public Integer getElementid() {
        return elementid;
    }

    /**
     * @param elementid
     */
    public void setElementid(Integer elementid) {
        this.elementid = elementid;
    }

    /**
     * @return dataKey
     */
    public String getDatakey() {
        return datakey;
    }

    /**
     * @param datakey
     */
    public void setDatakey(String datakey) {
        this.datakey = datakey == null ? null : datakey.trim();
    }

    /**
     * @return actionId
     */
    public String getActionid() {
        return actionid;
    }

    /**
     * @param actionid
     */
    public void setActionid(String actionid) {
        this.actionid = actionid;
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
     * @return moduleId
     */
    public Integer getModuleid() {
        return moduleid;
    }

    /**
     * @param moduleid
     */
    public void setModuleid(Integer moduleid) {
        this.moduleid = moduleid;
    }

    public String getUiElementName() {
        return uiElementName;
    }

    public void setUiElementName(String uiElementName) {
        this.uiElementName = uiElementName;
    }

    public String getUiElementByType() {
        return uiElementByType;
    }

    public void setUiElementByType(String uiElementByType) {
        this.uiElementByType = uiElementByType;
    }

    public String getUiElementPath() {
        return uiElementPath;
    }

    public void setUiElementPath(String uiElementPath) {
        this.uiElementPath = uiElementPath;
    }

    public String getUiElementComment() {
        return uiElementComment;
    }

    public void setUiElementComment(String uiElementComment) {
        this.uiElementComment = uiElementComment;
    }

    public String getActionKey() {
        return actionKey;
    }

    public void setActionKey(String actionKey) {
        this.actionKey = actionKey;
    }

    public String getActionType() {
        return actionType;
    }

    public void setActionType(String actionType) {
        this.actionType = actionType;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getBrowsertype() {
        return browsertype;
    }

    public void setBrowsertype(String browsertype) {
        this.browsertype = browsertype;
    }
}