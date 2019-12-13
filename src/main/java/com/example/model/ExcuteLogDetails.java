package com.example.model;

import javax.persistence.*;

@Table(name = "excute_log_details")
public class ExcuteLogDetails {
    @Id
    private Integer id;

    @Column(name = "excuteLogId")
    private String excutelogid;

    @Column(name = "excuteName")
    private String excutename;

    @Column(name = "methodName")
    private String methodname;

    private String status;

    @Column(name = "excuteTime")
    private String excutetime;

    @Column(name = "startTime")
    private String starttime;

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
     * @return excuteLogId
     */
    public String getExcutelogid() {
        return excutelogid;
    }

    /**
     * @param excutelogid
     */
    public void setExcutelogid(String excutelogid) {
        this.excutelogid = excutelogid == null ? null : excutelogid.trim();
    }

    /**
     * @return excuteName
     */
    public String getExcutename() {
        return excutename;
    }

    /**
     * @param excutename
     */
    public void setExcutename(String excutename) {
        this.excutename = excutename == null ? null : excutename.trim();
    }

    /**
     * @return methodName
     */
    public String getMethodname() {
        return methodname;
    }

    /**
     * @param methodname
     */
    public void setMethodname(String methodname) {
        this.methodname = methodname == null ? null : methodname.trim();
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
     * @return excuteTime
     */
    public String getExcutetime() {
        return excutetime;
    }

    /**
     * @param excutetime
     */
    public void setExcutetime(String excutetime) {
        this.excutetime = excutetime == null ? null : excutetime.trim();
    }

    /**
     * @return startTime
     */
    public String getStarttime() {
        return starttime;
    }

    /**
     * @param starttime
     */
    public void setStarttime(String starttime) {
        this.starttime = starttime == null ? null : starttime.trim();
    }
}