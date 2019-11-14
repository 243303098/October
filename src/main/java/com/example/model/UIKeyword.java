package com.example.model;

import javax.persistence.*;

@Table(name = "ui_keyword")
public class UIKeyword {
    @Id
    private String id;

    @Column(name = "actionType")
    private String actiontype;

    @Column(name = "actionKey")
    private String actionkey;

    private String comment;

    /**
     * @return id
     */
    public String getId() {
        return id;
    }

    /**
     * @param id
     */
    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    /**
     * @return actionType
     */
    public String getActiontype() {
        return actiontype;
    }

    /**
     * @param actiontype
     */
    public void setActiontype(String actiontype) {
        this.actiontype = actiontype == null ? null : actiontype.trim();
    }

    /**
     * @return actionKey
     */
    public String getActionkey() {
        return actionkey;
    }

    /**
     * @param actionkey
     */
    public void setActionkey(String actionkey) {
        this.actionkey = actionkey == null ? null : actionkey.trim();
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
}