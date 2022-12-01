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
public class Team {
    private int teamId;
    private int class_id;
    private String topicCode;
    private String topicName;
    private String gitlabUrl;
    private int status;

    public Team() {
    }

    
    public Team(int teamId, int class_id, String topicCode, String topicName, String gitlabUrl, int status) {
        this.teamId = teamId;
        this.class_id = class_id;
        this.topicCode = topicCode;
        this.topicName = topicName;
        this.gitlabUrl = gitlabUrl;
        this.status = status;
    }

    public int getTeamId() {
        return teamId;
    }

    public void setTeamId(int teamId) {
        this.teamId = teamId;
    }

    public int getClass_id() {
        return class_id;
    }

    public void setClass_id(int class_id) {
        this.class_id = class_id;
    }

    public String getTopicCode() {
        return topicCode;
    }

    public void setTopicCode(String topicCode) {
        this.topicCode = topicCode;
    }

    public String getTopicName() {
        return topicName;
    }

    public void setTopicName(String topicName) {
        this.topicName = topicName;
    }

    public String getGitlabUrl() {
        return gitlabUrl;
    }

    public void setGitlabUrl(String gitlabUrl) {
        this.gitlabUrl = gitlabUrl;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    @Override
    public String toString() {
        String[] TypeStatus = {"inactive", "active"};
        
        return String.format("%-20d%-20d%-20s%-20s%-20s%-20s",
                this.getTeamId(),
                this.getClass_id(),
                this.getTopicCode(),
                this.getTopicName(),
                this.getGitlabUrl(),
                TypeStatus[this.getStatus()]);
//        return "Team{" + "teamId=" + teamId + ", class_id=" + class_id + ", topicCode=" + topicCode + ", topicName=" + topicName + ", gitlabUrl=" + gitlabUrl + ", status=" + status + '}';
//    }
}
}
