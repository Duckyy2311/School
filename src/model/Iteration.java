/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.Date;

public class Iteration {

    private int iterId;
    private int subid;
    private String subname;
    private String iterName;
    private int duration;
    private int status;

    public Iteration() {
    }

    public void setSubname(String Subname) {
        this.subname = Subname;
    }

    public String getSubname() {
        return subname;
    }

    public Iteration(int IterId, int Subid, String IterName, int Duration, int Status) {
        this.iterId = IterId;
        this.subid = Subid;
        this.iterName = IterName;
        this.duration = Duration;
        this.status = Status;
    }

    public Iteration(int IterId, String Subname, String IterName, int duration, int Status) {
        this.iterId = IterId;
        this.subname = Subname;
        this.iterName = IterName;
        this.duration = duration;
        this.status = Status;
    }

    public Iteration(int IterId, int Subid, String Subname, String IterName, int duration, int Status) {
        this.iterId = IterId;
        this.subid = Subid;
        this.subname = Subname;
        this.iterName = IterName;
        this.duration = duration;
        this.status = Status;
    }

    public void setSubid(int Subid) {
        this.subid = Subid;
    }

    public int getSubid() {
        return subid;
    }

    public int getIterId() {
        return iterId;
    }

    public void setIterId(int IterId) {
        this.iterId = IterId;
    }

    public String getIterName() {
        return iterName;
    }

    public void setIterName(String IterName) {
        this.iterName = IterName;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int Status) {
        this.status = Status;
    }

    @Override
    public String toString() {
        String[] TypeStatus = {"inactive", "active"};
        String[] Subject = {"MAS", "JPD", "ITA", "ISP"};
        return String.format("%-10s%-10s%-10s%-15s%-10s",
                this.getIterId(),
                this.getSubname(),
                this.getIterName(),
                this.getDuration(),
                TypeStatus[this.getStatus()]);
    }

}
