/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 *
 * @author Dinh Quoc Tung
 */
public class Feature {

    private int feature_id;
    private int team_id;
    private String feature_name;
    private int status;

    public Feature() {

    }

    public Feature(int feature_id, int team_id, String feature_name, int status) {
        this.feature_id = feature_id;
        this.team_id = team_id;
        this.feature_name = feature_name;
        this.status = status;
    }

    public int getFeature_id() {
        return feature_id;
    }

    public void setFeature_id(int feature_id) {
        this.feature_id = feature_id;
    }

    public int getTeam_id() {
        return team_id;
    }

    public void setTeam_id(int team_id) {
        this.team_id = team_id;
    }

    public String getFeature_name() {
        return feature_name;
    }

    public void setFeature_name(String feature_name) {
        this.feature_name = feature_name;
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

        return String.format("%-20d%-20d%-20s%-20s",
                this.getFeature_id(),
                this.getTeam_id(),
                this.getFeature_name(),
                TypeStatus[this.getStatus()]);
//        return "Team{" + "teamId=" + teamId + ", class_id=" + class_id + ", topicCode=" + topicCode + ", topicName=" + topicName + ", gitlabUrl=" + gitlabUrl + ", status=" + status + '}';
//    }
    }

}
