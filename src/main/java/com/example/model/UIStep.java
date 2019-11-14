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
}