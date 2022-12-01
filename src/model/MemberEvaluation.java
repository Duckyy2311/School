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
public class MemberEvaluation {
    private int member_eval_id;
    private int evaluation_id;
    private int criteria_id;
    private String criteria_name;
    private int converted_loc;
    private double grade;
    private String note;

    public MemberEvaluation() {
    }

    public MemberEvaluation(int evaluation_id, int criteria_id, int converted_loc, double grade, String note) {
        this.evaluation_id = evaluation_id;
        this.criteria_id = criteria_id;
        this.converted_loc = converted_loc;
        this.grade = grade;
        this.note = note;
    }

    public MemberEvaluation(int evaluation_id, String criteria_name, int converted_loc, double grade, String note) {
        this.evaluation_id = evaluation_id;
        this.criteria_name = criteria_name;
        this.converted_loc = converted_loc;
        this.grade = grade;
        this.note = note;
    }

    public int getMember_eval_id() {
        return member_eval_id;
    }

    public void setMember_eval_id(int member_eval_id) {
        this.member_eval_id = member_eval_id;
    }

    public int getEvaluation_id() {
        return evaluation_id;
    }

    public void setEvaluation_id(int evaluation_id) {
        this.evaluation_id = evaluation_id;
    }

    public int getCriteria_id() {
        return criteria_id;
    }

    public void setCriteria_id(int criteria_id) {
        this.criteria_id = criteria_id;
    }

    public String getCriteria_name() {
        return criteria_name;
    }

    public void setCriteria_name(String criteria_name) {
        this.criteria_name = criteria_name;
    }

    public int getConverted_loc() {
        return converted_loc;
    }

    public void setConverted_loc(int converted_loc) {
        this.converted_loc = converted_loc;
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
        
         return String.format("%-15s%-20s%-15s%-20s%-15s",
                this.getEvaluation_id(),
                this.getCriteria_name(),
                this.getConverted_loc(),
                this.getGrade(),
                this.getNote());
    }
    
}
