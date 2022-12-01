/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author Admin
 */
public class Milestone {
    private int id;
    private int iter_id;
    private int class_id;
    private Date from;
    private Date to;
    private int status;
    private String iter_name;
    private String Class_code;

    public Milestone(int id, String iter_name, String Class_code, Date from, Date to, int status, int iter_id, int class_id) {
        this.id = id;
        this.iter_id = iter_id;
        this.class_id = class_id;
        this.from = from;
        this.to = to;
        this.status = status;
        this.iter_name = iter_name;
        this.Class_code = Class_code;
    }

    public Milestone(int id, int iter_id, int class_id, Date from, Date to, int status) {
        this.id = id;
        this.iter_id = iter_id;
        this.class_id = class_id;
        this.from = from;
        this.to = to;
        this.status = status;
    }

    public Milestone(int id, String iter_name, String Class_code, Date from, Date to, int status) {
        this.id = id;
        this.from = from;
        this.to = to;
        this.status = status;
        this.iter_name = iter_name;
        this.Class_code = Class_code;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIter_id() {
        return iter_id;
    }

    public void setIter_id(int iter_id) {
        this.iter_id = iter_id;
    }

    public int getClass_id() {
        return class_id;
    }

    public void setClass_id(int class_id) {
        this.class_id = class_id;
    }

    public Date getFrom() {
        return from;
    }

    public void setFrom(Date from) {
        this.from = from;
    }

    public Date getTo() {
        return to;
    }

    public void setTo(Date to) {
        this.to = to;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getIter_name() {
        return iter_name;
    }

    public void setIter_name(String iter_name) {
        this.iter_name = iter_name;
    }

    public String getClass_code() {
        return Class_code;
    }

    public void setClass_code(String Class_code) {
        this.Class_code = Class_code;
    }
    @Override
    public String toString() {  
        String[] TypeStatus = {"Open", "Closed","Cancel"};
        SimpleDateFormat SDF = new SimpleDateFormat("dd/MM/yyyy");
        return String.format("%-7d%-12s%-10s%-15s%-15s%-15s",
                this.getId(),
                this.getClass_code(),
                this.getIter_name(),                
                SDF.format(this.getFrom()),
                SDF.format(this.getTo()),
                TypeStatus[this.getStatus()-1]);
    }
}
