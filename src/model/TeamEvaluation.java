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
public class TeamEvaluation {
    private int team_eval_id;
    private int evaluation_id;
    private String criteria_name;
    private String team_name;
    private int criteria_id;
    private int team_id;
    private double grade;
    private String note;

    public TeamEvaluation( int evaluation_id, int criteria_id, int team_id, double grade, String note) {
        
        this.evaluation_id = evaluation_id;
        this.criteria_id = criteria_id;
        this.team_id = team_id;
        this.grade = grade;
        this.note = note;
    }
    public TeamEvaluation( int evaluation_id, String criteria_name,  double grade, String note) {
     
        this.evaluation_id = evaluation_id;
        this.criteria_name = criteria_name;

        this.grade = grade;
        this.note = note;
    }
    public TeamEvaluation( int evaluation_id,String criteria_name, String  team_name,  double grade, String note) {
     
        this.evaluation_id = evaluation_id;
        this.criteria_name = criteria_name;
        this.team_name = team_name;
        this.grade = grade;
        this.note = note;
    }


    public int getTeam_eval_id() {
        return team_eval_id;
    }

    public void setTeam_eval_id(int team_eval_id) {
        this.team_eval_id = team_eval_id;
    }

    public int getEvaluation_id() {
        return evaluation_id;
    }

    public void setEvaluation_id(int evaluation_id) {
        this.evaluation_id = evaluation_id;
    }

    public String getCriteria_name() {
        return criteria_name;
    }

    public void setCriteria_name(String criteria_name) {
        this.criteria_name = criteria_name;
    }

    public String getTeam_name() {
        return team_name;
    }

    public void setTeam_name(String team_name) {
        this.team_name = team_name;
    }

    public int getCriteria_id() {
        return criteria_id;
    }

    public void setCriteria_id(int criteria_id) {
        this.criteria_id = criteria_id;
    }

    public int getTeam_id() {
        return team_id;
    }

    public void setTeam_id(int team_id) {
        this.team_id = team_id;
    }

    public double getGrade() {
        return grade;
    }

    public void setGrade(double grade) {
        this.grade = grade;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }
    public String toString() {
        
        return String.format("%-15s%-15s%-15s%-15s%-15s",
                this.getEvaluation_id(),
                this.getCriteria_name(),
                this.getTeam_name(),
                this.getGrade(),
                this.getNote());
    }
}
