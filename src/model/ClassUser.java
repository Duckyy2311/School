/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.Date;

/**
 *
 * @author AnhMinh
 */
public class ClassUser {
    private String fullname;
    private String class_code;
    private String topic_code;
    private int class_id;
    private int team_id; 
    private int user_id;
    private int team_leader;
    private Date dropout_date;
    private  String user_notes;
    private String ongoing_eval;
    private String final_press_eval;
    private String final_topic_eval;
    private int status;

    public ClassUser() {
    }

    public ClassUser(String class_code, String fullname, String topic_code, int team_leader, Date dropout_date, String user_notes, String ongoing_eval, String final_press_eval, String final_topic_eval, int status) {
        this.fullname = fullname;
        this.class_code = class_code;
        this.topic_code = topic_code;
        this.team_leader = team_leader;
        this.dropout_date = dropout_date;
        this.user_notes = user_notes;
        this.ongoing_eval = ongoing_eval;
        this.final_press_eval = final_press_eval;
        this.final_topic_eval = final_topic_eval;
        this.status = status;
    }

    
    
    public ClassUser(int class_id, int team_id, int user_id, int team_leader, Date dropout_date, String user_notes, String ongoing_eval, String final_press_eval, String final_topic_eval, int status) {
        this.class_id = class_id;
        this.team_id = team_id;
        this.user_id = user_id;
        this.team_leader = team_leader;
        this.dropout_date = dropout_date;
        this.user_notes = user_notes;
        this.ongoing_eval = ongoing_eval;
        this.final_press_eval = final_press_eval;
        this.final_topic_eval = final_topic_eval;
        this.status = status;
    }

    public String getClass_code() {
        return class_code;
    }

    public void setClass_code(String class_code) {
        this.class_code = class_code;
    }

    public String getTopic_code() {
        return topic_code;
    }

    public void setTopic_code(String topic_code) {
        this.topic_code = topic_code;
    }

    public int getClass_id() {
        return class_id;
    }

    public void setClass_id(int class_id) {
        this.class_id = class_id;
    }

    public int getTeam_id() {
        return team_id;
    }

    public void setTeam_id(int team_id) {
        this.team_id = team_id;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public int getTeam_leader() {
        return team_leader;
    }

    public void setTeam_leader(int team_leader) {
        this.team_leader = team_leader;
    }

    public Date getDropout_date() {
        return dropout_date;
    }

    public void setDropout_date(Date dropout_date) {
        this.dropout_date = dropout_date;
    }

    public String getUser_notes() {
        return user_notes;
    }

    public void setUser_notes(String user_notes) {
        this.user_notes = user_notes;
    }

    public String getOngoing_eval() {
        return ongoing_eval;
    }

    public void setOngoing_eval(String ongoing_eval) {
        this.ongoing_eval = ongoing_eval;
    }

    public String getFinal_press_eval() {
        return final_press_eval;
    }

    public void setFinal_press_eval(String final_press_eval) {
        this.final_press_eval = final_press_eval;
    }

    public String getFinal_topic_eval() {
        return final_topic_eval;
    }

    public void setFinal_topic_eval(String final_topic_eval) {
        this.final_topic_eval = final_topic_eval;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    @Override
    public String toString() {
        
        String[] TypeStatus = {"inactive","Active"};
        String[] TypeTleader = {"Yes", "No"};
        return String.format("%-15s%-20s%-20s%-20s%-20s%-20s%-20s%-20s%-20s%-10s",
                this.getClass_code(),
                this.getTopic_code(),
                this.getFullname(),
                TypeTleader[this.getTeam_leader()],
                this.getDropout_date(),
                this.getUser_notes(),
                this.getOngoing_eval(),
                this.getFinal_press_eval(),  
                this.getFinal_topic_eval(),
                TypeStatus[this.getStatus()]);
                }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }
    
}
