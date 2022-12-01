/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 *
 * @author Acer
 */
public class Criteria {

    private int criteriaID;
    private String subCode;
    private int subId;
    private String iterationName;
    private int iterationID;
    private String criteriaName;
    private int evaluationWeight;
    private int teamEvaluation;
    private int criteriaOrder;
    private int maxLoc;
    private int status;

    public Criteria(int criteriaID, String subCode, String iterationName, String criteriaName, int evaluationWeight, int teamEvaluation, int criteriaOrder, int maxLoc, int status) {
        this.criteriaID = criteriaID;
        this.subCode = subCode;
        this.iterationName = iterationName;
        this.criteriaName = criteriaName;
        this.evaluationWeight = evaluationWeight;
        this.teamEvaluation = teamEvaluation;
        this.criteriaOrder = criteriaOrder;
        this.maxLoc = maxLoc;
        this.status = status;
    }

    public Criteria(int criteriaID, String subCode, int subId, String iterationName, int iterationID, String criteriaName, int evaluationWeight, int teamEvaluation, int criteriaOrder, int maxLoc, int status) {
        this.criteriaID = criteriaID;
        this.subCode = subCode;
        this.subId = subId;
        this.iterationName = iterationName;
        this.iterationID = iterationID;
        this.criteriaName = criteriaName;
        this.evaluationWeight = evaluationWeight;
        this.teamEvaluation = teamEvaluation;
        this.criteriaOrder = criteriaOrder;
        this.maxLoc = maxLoc;
        this.status = status;
    }

    public Criteria(int criteriaID, String subCode, String iterationName, int iterationID, String criteriaName, int evaluationWeight, int teamEvaluation, int criteriaOrder, int maxLoc, int status) {
        this.criteriaID = criteriaID;
        this.subCode = subCode;
        this.iterationName = iterationName;
        this.criteriaName = criteriaName;
        this.evaluationWeight = evaluationWeight;
        this.teamEvaluation = teamEvaluation;
        this.criteriaOrder = criteriaOrder;
        this.maxLoc = maxLoc;
        this.status = status;
    }

    public Criteria(int criteriaID, int subId, int iterationID, String criteriaName, int evaluationWeight, int teamEvaluation, int criteriaOrder, int maxLoc, int status) {
        this.criteriaID = criteriaID;
        this.subId = subId;
        this.iterationID = iterationID;
        this.criteriaName = criteriaName;
        this.evaluationWeight = evaluationWeight;
        this.teamEvaluation = teamEvaluation;
        this.criteriaOrder = criteriaOrder;
        this.maxLoc = maxLoc;
        this.status = status;
    }

    public String getIterationName() {
        return iterationName;
    }

    public String getSubCode() {
        return subCode;
    }

    public int getSubId() {
        return subId;
    }

    public void setIterationName(String iterationName) {
        this.iterationName = iterationName;
    }

    public void setSubCode(String subCode) {
        this.subCode = subCode;
    }

    public void setSubId(int subId) {
        this.subId = subId;
    }

    public String getCriteriaName() {
        return criteriaName;
    }

    public void setCriteriaName(String criteriaName) {
        this.criteriaName = criteriaName;
    }

    public Criteria() {
    }

    public int getCriteriaID() {
        return criteriaID;
    }

    public void setCriteriaID(int criteriaID) {
        this.criteriaID = criteriaID;
    }

    public int getIterationID() {
        return iterationID;
    }

    public void setIterationID(int iterationID) {
        this.iterationID = iterationID;
    }

    public int getEvaluationWeight() {
        return evaluationWeight;
    }

    public void setEvaluationWeight(int evaluationWeight) {
        this.evaluationWeight = evaluationWeight;
    }

    public int getTeamEvaluation() {
        return teamEvaluation;
    }

    public void setTeamEvaluation(int teamEvaluation) {
        this.teamEvaluation = teamEvaluation;
    }

    public int getCriteriaOrder() {
        return criteriaOrder;
    }

    public void setCriteriaOrder(int criteriaOrder) {
        this.criteriaOrder = criteriaOrder;
    }

    public int getMaxLoc() {
        return maxLoc;
    }

    public void setMaxLoc(int maxLoc) {
        this.maxLoc = maxLoc;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String toString() {
        String[] TypeStatus = {"inactive", "active"};
        String[] teameva = {"No", "Yes"};
        return String.format("%-10s%-15s%-18s%-23s%-23s%-20s%-18s%-7s%-15s",
                this.getCriteriaID(),
                this.getSubCode(),
                this.getIterationName(),
                this.getCriteriaName(),
                this.getEvaluationWeight(),
                teameva[this.getTeamEvaluation()],
                this.getCriteriaOrder(),
                this.getMaxLoc(),
                TypeStatus[this.getStatus()]);
    }
}
