/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 *
 * @author AnhMinh
 */
public class Tracking {
    private int tracking_id;
    private int teamid;
    private int mileStoneid;
    private int function_id;
    private String topic_code;
    private String milestone_name;
    private String function_name;
    private int assigner_id;
    private int assignee_id;
    private String assigner_name;
    private String assignee_name;
    private String note;
    private String updates;
    private int status;

    public Tracking() {
    }

    public Tracking( int teamid, int mileStoneid, int function_id, int assigner_id, int assignee_id, String note, String updates, int status) {

        this.teamid = teamid;
        this.mileStoneid = mileStoneid;
        this.function_id = function_id;
        this.assigner_id = assigner_id;
        this.assignee_id = assignee_id;
        this.note = note;
        this.updates = updates;
        this.status = status;
    }

    public Tracking( String topic_code, String milestone_name, String func_name, String assigner_name, String assignee_name, String note, String updates, int status) {

        this.topic_code = topic_code;
        this.milestone_name = milestone_name;
        this.function_name = func_name;
        this.assigner_name = assigner_name;
        this.assignee_name = assignee_name;
        this.note = note;
        this.updates = updates;
        this.status = status;
    }

    public int getTracking_id() {
        return tracking_id;
    }

    public void setTracking_id(int tracking_id) {
        this.tracking_id = tracking_id;
    }

    public int getTeamid() {
        return teamid;
    }

    public void setTeamid(int teamid) {
        this.teamid = teamid;
    }

    public int getMileStoneid() {
        return mileStoneid;
    }

    public void setMileStoneid(int mileStoneid) {
        this.mileStoneid = mileStoneid;
    }

    public int getFunction_id() {
        return function_id;
    }

    public void setFunction_id(int function_id) {
        this.function_id = function_id;
    }

    public String getTopic_code() {
        return topic_code;
    }

    public void setTopic_code(String topic_code) {
        this.topic_code = topic_code;
    }

    public String getMilestone_name() {
        return milestone_name;
    }

    public void setMilestone_name(String milestone_name) {
        this.milestone_name = milestone_name;
    }

    public String getFunction_name() {
        return function_name;
    }

    public void setFunction_name(String func_name) {
        this.function_name = func_name;
    }

    public int getAssigner_id() {
        return assigner_id;
    }

    public void setAssigner_id(int assigner_id) {
        this.assigner_id = assigner_id;
    }

    public int getAssignee_id() {
        return assignee_id;
    }

    public void setAssignee_id(int assignee_id) {
        this.assignee_id = assignee_id;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public String getUpdates() {
        return updates;
    }

    public void setUpdates(String updates) {
        this.updates = updates;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getAssigner_name() {
        return assigner_name;
    }

    public void setAssigner_name(String assigner_name) {
        this.assigner_name = assigner_name;
    }

    public String getAssignee_name() {
        return assignee_name;
    }

    public void setAssignee_name(String assignee_name) {
        this.assignee_name = assignee_name;
    }
    
    

    @Override
    public String toString() {
        String[] TypeStatus = {"Planned", "Analysed", "Designed", "Coded", "Integrated", "Submitted", "Evaluated", "Rejected"};
        return String.format("%-15s%-20s%-15s%-20s%-15s%-15s%-15s%-15s",
                this.getTopic_code(),
                this.getMilestone_name(),
                this.getFunction_name(),
                this.getAssigner_name(),
                this.getAssignee_name(),
                this.getNote(),
                this.getUpdates(),
                TypeStatus[this.getStatus()]);}
    

   
    
    
    
}
