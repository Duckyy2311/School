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
 * @author Acer
 */
public class Issue {

    private int issue_id;
    private int git_id;
    private String git_url;
    private int assign_id;
    private String assign_name;
    private String issue_title;
    private String description;
    private Date created_at;
    private Date due_date;
    private int team_id;
    private String topic_name;
    private int milestone_id;
    private String milestone_name;
    private String labels;
    private int function_id;
    private String function_name;
    private int status;

    public Issue() {
    }

    public Issue(int issue_id, int git_id,String git_url, int assign_id, String assign_name, String issue_title, String description, Date created_at, Date due_date, int topic_id, String topic_name, int milestone_id, String labels, int function_ids, int status) {
        this.issue_id = issue_id;
        this.git_id = git_id;
        this.git_url = git_url;
        this.assign_id = assign_id;
        this.assign_name = assign_name;
        this.issue_title = issue_title;
        this.description = description;
        this.created_at = created_at;
        this.due_date = due_date;
        this.team_id = topic_id;
        this.topic_name = topic_name;
        this.milestone_id = milestone_id;
        this.labels = labels;
        this.function_id = function_id;

        this.status = status;
    }

    public Issue(int issue_id, int git_id,String git_url, int assign_id, String issue_title, String description, Date created_at, Date due_date, int topic_id, int milestone_id, String labels, int function_id, int status) {
        this.issue_id = issue_id;
        this.git_id = git_id;
        this.git_url = git_url;
        this.assign_id = assign_id;
        this.issue_title = issue_title;
        this.description = description;
        this.created_at = created_at;
        this.due_date = due_date;
        this.team_id = topic_id;
        this.milestone_id = milestone_id;
        this.labels = labels;
        this.function_id = function_id;
        this.status = status;
    }

    public Issue(int issue_id, String issue_title, String assign_name, String topic_name, String description, String git_url, String function_name, String milestone_name,String labels, int status) {
        this.issue_id = issue_id;
        this.git_url = git_url;
        this.assign_name = assign_name;
        this.issue_title = issue_title;
        this.description = description;
        this.topic_name = topic_name;
        this.milestone_name = milestone_name;
        this.labels = labels;
        this.function_name = function_name;
        this.status = status;
    }

    public int getIssue_id() {
        return issue_id;
    }

    public void setIssue_id(int issue_id) {
        this.issue_id = issue_id;
    }

    public int getGit_id() {
        return git_id;
    }

    public String getGit_url() {
        return git_url;
    }

    public void setGit_url(String git_url) {
        this.git_url = git_url;
    }

    public void setGit_id(int git_id) {
        this.git_id = git_id;
    }

    public int getAssign_id() {
        return assign_id;
    }

    public void setAssign_id(int assign_id) {
        this.assign_id = assign_id;
    }

    public String getAssign_name() {
        return assign_name;
    }

    public void setAssign_name(String assign_name) {
        this.assign_name = assign_name;
    }

    public String getIssue_title() {
        return issue_title;
    }

    public void setIssue_title(String issue_title) {
        this.issue_title = issue_title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getCreated_at() {
        return created_at;
    }

    public void setCreated_at(Date created_at) {
        this.created_at = created_at;
    }

    public Date getDue_date() {
        return due_date;
    }

    public void setDue_date(Date due_date) {
        this.due_date = due_date;
    }

    public int getTopic_id() {
        return team_id;
    }

    public void setTopic_id(int topic_id) {
        this.team_id = topic_id;
    }

    public String getTopic_name() {
        return topic_name;
    }

    public void setTopic_name(String topic_name) {
        this.topic_name = topic_name;
    }

    public int getMilestone_id() {
        return milestone_id;
    }

    public void setMilestone_id(int milestone_id) {
        this.milestone_id = milestone_id;
    }


    public String getLabels() {
        return labels;
    }

    public void setLabels(String labels) {
        this.labels = labels;
    }

    public int getFunction_id() {
        return function_id;
    }

    public void setFunction_id(int function_id) {
        this.function_id = function_id;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getTeam_id() {
        return team_id;
    }

    public void setTeam_id(int team_id) {
        this.team_id = team_id;
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

    public void setFunction_name(String function_name) {
        this.function_name = function_name;
    }

    public String toString() {
        String[] TypeStatus = {"open", "close","pending"};
        SimpleDateFormat SDF = new SimpleDateFormat("dd/MM/yyyy");
        return String.format("|%-5s|%-15s|%-15s|%-15s|%-15s|%-15s|%-15s|%-15s|%-15s|%-12s",
                this.getIssue_id(),
                this.getIssue_title(),               
                this.getTopic_name(),
                this.getAssign_name(),
                this.getGit_url(),                                
                this.getDescription(),
                this.getFunction_name(),
                this.getMilestone_name(),
                this.getLabels(),
                TypeStatus[this.getStatus()-1]);
    }

}
