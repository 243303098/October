package com.example.model;

import java.util.Date;
import javax.persistence.*;

@Table(name = "quartz_task")
public class QuartzTask {
    @Id
    private Integer id;

    @Column(name = "jobName")
    private String jobname;

    @Column(name = "jobProject")
    private String jobproject;

    private String description;

    @Column(name = "jobClassName")
    private String jobclassname;

    @Column(name = "cronExpression")
    private String cronexpression;

    @Column(name = "triggerName")
    private String triggername;

    @Column(name = "triggerState")
    private String triggerstate;

    @Column(name = "updateData")
    private Date updatedata;

    @Column(name = "jobParam")
    private String jobparam;

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
     * @return jobName
     */
    public String getJobname() {
        return jobname;
    }

    /**
     * @param jobname
     */
    public void setJobname(String jobname) {
        this.jobname = jobname == null ? null : jobname.trim();
    }

    /**
     * @return jobProject
     */
    public String getJobproject() {
        return jobproject;
    }

    /**
     * @param jobproject
     */
    public void setJobproject(String jobproject) {
        this.jobproject = jobproject == null ? null : jobproject.trim();
    }

    /**
     * @return description
     */
    public String getDescription() {
        return description;
    }

    /**
     * @param description
     */
    public void setDescription(String description) {
        this.description = description == null ? null : description.trim();
    }

    /**
     * @return jobClassName
     */
    public String getJobclassname() {
        return jobclassname;
    }

    /**
     * @param jobclassname
     */
    public void setJobclassname(String jobclassname) {
        this.jobclassname = jobclassname == null ? null : jobclassname.trim();
    }

    /**
     * @return cronExpression
     */
    public String getCronexpression() {
        return cronexpression;
    }

    /**
     * @param cronexpression
     */
    public void setCronexpression(String cronexpression) {
        this.cronexpression = cronexpression == null ? null : cronexpression.trim();
    }

    /**
     * @return triggerName
     */
    public String getTriggername() {
        return triggername;
    }

    /**
     * @param triggername
     */
    public void setTriggername(String triggername) {
        this.triggername = triggername == null ? null : triggername.trim();
    }

    /**
     * @return triggerState
     */
    public String getTriggerstate() {
        return triggerstate;
    }

    /**
     * @param triggerstate
     */
    public void setTriggerstate(String triggerstate) {
        this.triggerstate = triggerstate == null ? null : triggerstate.trim();
    }

    /**
     * @return updateData
     */
    public Date getUpdatedata() {
        return updatedata;
    }

    /**
     * @param updatedata
     */
    public void setUpdatedata(Date updatedata) {
        this.updatedata = updatedata;
    }

    /**
     * @return jobParam
     */
    public String getJobparam() {
        return jobparam;
    }

    /**
     * @param jobparam
     */
    public void setJobparam(String jobparam) {
        this.jobparam = jobparam == null ? null : jobparam.trim();
    }
}