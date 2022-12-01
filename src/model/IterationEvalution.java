/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 *
 * @author Admin
 */
public class IterationEvalution {
    private int eval_id;
    private int iter_id;
    private String iter_name;
    private int class_id;
    private String class_code;
    private int team_id;
    private String team_name;
    private int user_id;
    private String full_name;
    private int bonus;
    private double grade;
    private int team_grade;
    private int mem_grade;
    private String note;

    public IterationEvalution(double grade, String note) {
        this.grade = grade;
        this.note = note;
    }

    public IterationEvalution(int eval_id, int iter_id, int class_id, int team_id, int user_id, int bonus, double grade, String note) {
        this.eval_id = eval_id;
        this.iter_id = iter_id;
        this.class_id = class_id;
        this.team_id = team_id;
        this.user_id = user_id;
        this.bonus = bonus;
        this.grade = grade;
        this.note = note;
    }

    public IterationEvalution(int eval_id, String class_code, String team_name, String full_name, String iter_name, int team_grade, int mem_grade, int bonus, double grade, String note) {
        this.eval_id = eval_id;
        this.iter_name = iter_name;
        this.class_code = class_code;
        this.team_name = team_name;
        this.full_name = full_name;
        this.bonus = bonus;
        this.grade = grade;
        this.team_grade = team_grade;
        this.mem_grade = mem_grade;
        this.note = note;
    }

    public IterationEvalution(int eval_id, String class_code, String team_name, String full_name, String iter_name, int bonus, double grade, String note) {
        this.eval_id = eval_id;
        this.iter_name = iter_name;
        this.class_code = class_code;
        this.team_name = team_name;
        this.full_name = full_name;
        this.bonus = bonus;
        this.grade = grade;
        this.note = note;
    }

    public IterationEvalution(int eval_id, int iter_id, String iter_name, int class_id, String class_code, int team_id, String team_name, int user_id, String full_name, int team_grade, int mem_grade, int bonus, double grade, String note) {
        this.eval_id = eval_id;
        this.iter_id = iter_id;
        this.iter_name = iter_name;
        this.class_id = class_id;
        this.class_code = class_code;
        this.team_id = team_id;
        this.team_name = team_name;
        this.user_id = user_id;
        this.full_name = full_name;
        this.bonus = bonus;
        this.grade = grade;
        this.team_grade = team_grade;
        this.mem_grade = mem_grade;
        this.note = note;
    }

    public int getEval_id() {
        return eval_id;
    }

    public void setEval_id(int eval_id) {
        this.eval_id = eval_id;
    }

    public int getIter_id() {
        return iter_id;
    }

    public void setIter_id(int iter_id) {
        this.iter_id = iter_id;
    }

    public String getIter_name() {
        return iter_name;
    }

    public void setIter_name(String iter_name) {
        this.iter_name = iter_name;
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

    public int getTeam_id() {
        return team_id;
    }

    public void setTeam_id(int team_id) {
        this.team_id = team_id;
    }

    public String getTeam_name() {
        return team_name;
    }

    public void setTeam_name(String team_name) {
        this.team_name = team_name;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public String getFull_name() {
        return full_name;
    }

    public void setFull_name(String full_name) {
        this.full_name = full_name;
    }

    public int getBonus() {
        return bonus;
    }

    public void setBonus(int bonus) {
        this.bonus = bonus;
    }

    public double getGrade() {
        return grade;
    }

    public void setGrade(double grade) {
        this.grade = grade;
    }

    public int getTeam_grade() {
        return team_grade;
    }

    public void setTeam_grade(int team_grade) {
        this.team_grade = team_grade;
    }

    public int getMem_grade() {
        return mem_grade;
    }

    public void setMem_grade(int mem_grade) {
        this.mem_grade = mem_grade;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    
    
    public String toString() {  
        
        
        return String.format("|%-5d|%-10s|%-10s|%-17s|%-15s|%-10d|%-10.2f|%-15s",
                this.getEval_id(),                
                this.getClass_code(),                
                this.getTeam_name(),
                this.getFull_name(),
                this.getIter_name(),
                this.getBonus(),
                this.getGrade(),
                this.getNote());                
    }
    
}
