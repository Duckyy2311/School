/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.time.Year;
import java.time.format.DateTimeFormatter;
import java.util.Date;

/**
 *
 * @author Admin
 */
public class Class {
    private int class_id;
    private String class_code;
    private String train_name;
    private String subname;  
    private int trainer_id;
    private int sub_id;
    private int class_year;
    private String term;
    private int bl5;
    private int status;
    public Class() {
    }

    public Class(int class_id, String class_code, int trainer_id, int sub_id, int class_year, String term, int bl5, int status) {
        this.class_id = class_id;
        this.class_code = class_code;
        this.trainer_id = trainer_id;
        this.sub_id = sub_id;
        this.class_year = class_year;
        this.term = term;
        this.bl5 = bl5;
        this.status = status;
    }

    public Class(int class_id, String class_code, String train_name, String subname, int class_year, String term, int bl5, int status) {
        this.class_id = class_id;
        this.class_code = class_code;
        this.train_name = train_name;
        this.subname = subname;
        this.class_year = class_year;
        this.term = term;
        this.bl5 = bl5;
        this.status = status;
    }

    public Class(int class_id, String class_code, String train_name, String subname, int class_year, String term, int bl5, int status, int trainer_id, int sub_id) {
        this.class_id = class_id;
        this.class_code = class_code;
        this.train_name = train_name;
        this.subname = subname;
        this.trainer_id = trainer_id;
        this.sub_id = sub_id;
        this.class_year = class_year;
        this.term = term;
        this.bl5 = bl5;
        this.status = status;
    }
    
    

    public int getClass_id() {
        return class_id;
    }

    public void setClass_id(int class_id) {
        this.class_id = class_id;
    }

    public String getClass_code() {
        return class_code;
    }

    public void setClass_code(String class_code) {
        this.class_code = class_code;
    }

    public int getTrainer_id() {
        return trainer_id;
    }

    public void setTrainer_id(int trainer_id) {
        this.trainer_id = trainer_id;
    }

    public int getSub_id() {
        return sub_id;
    }

    public void setSub_id(int sub_id) {
        this.sub_id = sub_id;
    }

    public int getClass_year() {
        return class_year;
    }

    public void setClass_year(int class_year) {
        this.class_year = class_year;
    }

    public String getTerm() {
        return term;
    }

    public void setTerm(String term) {
        this.term = term;
    }

    public int getBl5() {
        return bl5;
    }

    public void setBl5(int bl5) {
        this.bl5 = bl5;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getSubname() {
        return subname;
    }

    public void setSubname(String subname) {
        this.subname = subname;
    }

    public String getTrain_name() {
        return train_name;
    }

    public void setTrain_name(String train_name) {
        this.train_name = train_name;
    }
    
        @Override
    public String toString() {  
        String[] TypeStatus = {"Active", "Closed","Cancel"};
        String[] TypeBl5={"No","Yes"};
        return String.format("%-10d%-15s%-20s%-10s%-10s%-20s%-10s%-10s",
                this.getClass_id(),
                this.getClass_code(),
                this.getTrain_name(),
                this.getSubname(),
                this.getClass_year(),
                this.getTerm(),
                TypeBl5[this.getBl5()-1],
                TypeStatus[this.getStatus()-1]);
    }
}
