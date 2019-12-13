package com.example.model;

import javax.persistence.*;

@Table(name = "excute_step_details")
public class ExcuteStepDetails {
    @Id
    private Integer id;

    @Column(name = "excuteLogDetailId")
    private Integer excutelogdetailid;

    @Column(name = "stepDeatail")
    private String stepdeatail;

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
     * @return excuteLogDetailId
     */
    public Integer getExcutelogdetailid() {
        return excutelogdetailid;
    }

    /**
     * @param excutelogdetailid
     */
    public void setExcutelogdetailid(Integer excutelogdetailid) {
        this.excutelogdetailid = excutelogdetailid;
    }

    /**
     * @return stepDeatail
     */
    public String getStepdeatail() {
        return stepdeatail;
    }

    /**
     * @param stepdeatail
     */
    public void setStepdeatail(String stepdeatail) {
        this.stepdeatail = stepdeatail == null ? null : stepdeatail.trim();
    }
}