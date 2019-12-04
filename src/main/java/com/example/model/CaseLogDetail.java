package com.example.model;

import javax.persistence.*;

@Table(name = "case_log_detail")
public class CaseLogDetail {
    @Id
    private Integer id;

    @Column(name = "caseLogId")
    private Integer caselogid;

    @Column(name = "stepDetail")
    private String stepdetail;

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
     * @return caseLogId
     */
    public Integer getCaselogid() {
        return caselogid;
    }

    /**
     * @param caselogid
     */
    public void setCaselogid(Integer caselogid) {
        this.caselogid = caselogid;
    }

    /**
     * @return stepDetail
     */
    public String getStepdetail() {
        return stepdetail;
    }

    /**
     * @param stepdetail
     */
    public void setStepdetail(String stepdetail) {
        this.stepdetail = stepdetail == null ? null : stepdetail.trim();
    }
}