package com.example.model;

import java.util.Date;
import javax.persistence.*;

@Table(name = "case_log")
public class CaseLog {
    @Id
    private Integer id;

    @Column(name = "caseId")
    private Integer caseid;

    private String status;

    @Column(name = "createTime")
    private Date createtime;

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
     * @return caseId
     */
    public Integer getCaseid() {
        return caseid;
    }

    /**
     * @param caseid
     */
    public void setCaseid(Integer caseid) {
        this.caseid = caseid;
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
}