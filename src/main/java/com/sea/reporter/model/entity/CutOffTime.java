package com.sea.reporter.model.entity;

import javax.persistence.*;
import java.io.Serializable;

/**
 *
 * @author navand
 */
@Entity
@Table(name = "CutOffTime")
public class CutOffTime implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cutofftime_id")
    private long cutofftimeId;
    @Column(name = "bridge_id")
    private long bridgeId;
    @Column(name = "cut_off_time")
    private String cutOffTime;
    @Column(name = "status")
    private int status;
    @Column(name = "regdate")
    private String regdate;
    @Column(name = "regtime")
    private String regtime;
    @Column(name = "enddate")
    private String enddate;
    @Column(name = "endtime")
    private String endtime;

    public long getCutofftimeId() {
        return cutofftimeId;
    }

    public void setCutofftimeId(long cutofftimeId) {
        this.cutofftimeId = cutofftimeId;
    }

    public long getBridgeId() {
        return bridgeId;
    }

    public void setBridgeId(long bridgeId) {
        this.bridgeId = bridgeId;
    }

    public String getCutOffTime() {
        return cutOffTime;
    }

    public void setCutOffTime(String cutOffTime) {
        this.cutOffTime = cutOffTime;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getRegdate() {
        return regdate;
    }

    public void setRegdate(String regdate) {
        this.regdate = regdate;
    }

    public String getRegtime() {
        return regtime;
    }

    public void setRegtime(String regtime) {
        this.regtime = regtime;
    }

    public String getEnddate() {
        return enddate;
    }

    public void setEnddate(String enddate) {
        this.enddate = enddate;
    }

    public String getEndtime() {
        return endtime;
    }

    public void setEndtime(String endtime) {
        this.endtime = endtime;
    }
}
