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
public class LocEvaluation {

    private int evaluationId;
    private int trackingId;
    private String trackingName;
    private Date evaluationTime;
    private String evaluationNote;
    private int complexityId;
    private int qualityId;

    public LocEvaluation() {
    }

    public LocEvaluation(int evaluationId, int trackingId, String trackingName, Date evaluationTime, String evaluationNote, int complexityId, int qualityId) {
        this.evaluationId = evaluationId;
        this.trackingId = trackingId;
        this.trackingName = trackingName;
        this.evaluationTime = evaluationTime;
        this.evaluationNote = evaluationNote;
        this.complexityId = complexityId;
        this.qualityId = qualityId;
    }
  

    public LocEvaluation(int trackingId,  String evaluationNote, int complexityId, int qualityId, int evaluationId) {
        this.trackingId = trackingId;
        this.evaluationNote = evaluationNote;
        this.complexityId = complexityId;
        this.qualityId = qualityId;
        this.evaluationId = evaluationId;

    }

    public LocEvaluation(int trackingId, Date evaluationTime, String evaluationNote, int complexityId, int qualityId) {
        this.trackingId = trackingId;
        this.evaluationTime = evaluationTime;
        this.evaluationNote = evaluationNote;
        this.complexityId = complexityId;
        this.qualityId = qualityId;
    }
   
    
    public LocEvaluation(int trackingId, String evaluationNote, int complexityId, int qualityId) {
        this.trackingId = trackingId;
        this.evaluationNote = evaluationNote;
        this.complexityId = complexityId;
        this.qualityId = qualityId;
    }
    

    public LocEvaluation(String trackingName, Date evaluationTime, String evaluationNote, int complexityId, int qualityId,int evaluationId) {
        this.trackingName = trackingName;
        this.evaluationTime = evaluationTime;
        this.evaluationNote = evaluationNote;
        this.complexityId = complexityId;
        this.qualityId = qualityId;
        this.evaluationId = evaluationId;
    }

    public String getTrackingName() {
        return trackingName;
    }

    public void setTrackingName(String trackingName) {
        this.trackingName = trackingName;
    }

    public int getEvaluationId() {
        return evaluationId;
    }

    public void setEvaluationId(int evaluationId) {
        this.evaluationId = evaluationId;
    }

    public int getTrackingId() {
        return trackingId;
    }

    public void setTrackingId(int trackingId) {
        this.trackingId = trackingId;
    }

    public Date getEvaluationTime() {
        return evaluationTime;
    }

    public void setEvaluationTime(Date evaluationTime) {
        this.evaluationTime = evaluationTime;
    }

    public String getEvaluationNote() {
        return evaluationNote;
    }

    public void setEvaluationNote(String evaluationNote) {
        this.evaluationNote = evaluationNote;
    }

    public int getComplexityId() {
        return complexityId;
    }

    public void setComplexityId(int complexityId) {
        this.complexityId = complexityId;
    }

    public int getQualityId() {
        return qualityId;
    }

    public void setQualityId(int qualityId) {
        this.qualityId = qualityId;
    }

    @Override
    public String toString() {
        SimpleDateFormat SDF = new SimpleDateFormat("yyyy/MM/dd");
        String[] complex = {"Simple", "Medium", "Hard"};

        return String.format("%-15d%-15s%-15s%-15s%-15s",
         this.getTrackingId(),
         SDF.format(this.getEvaluationTime()),
         this.getEvaluationNote(),
         complex[this.getComplexityId() - 1],
         this.getQualityId()
        );
    }

}
