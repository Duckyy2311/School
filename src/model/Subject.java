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
public class Subject {
    private int SubId;
    private String SubCode;
    private String SubName;
    private int AuthId;
    private int Status;

    public Subject() {
    }

    
    
    public Subject(int SubId, String SubCode, String SubName , int AuthId, int Status) {
        this.SubId = SubId;
        this.SubCode = SubCode;
        this.SubName = SubName;
        this.AuthId = AuthId;
        this.Status = Status;
    }

    public String getSubName() {
        return SubName;
    }

    public void setSubName(String SubName) {
        this.SubName = SubName;
    }

    
    
    public int getSubId() {
        return SubId;
    }

    public void setSubId(int SubId) {
        this.SubId = SubId;
    }

    public String getSubCode() {
        return SubCode;
    }

    public void setSubCode(String SubCode) {
        this.SubCode = SubCode;
    }

    public int getAuthId() {
        return AuthId;
    }

    public void setAuthId(int AuthId) {
        this.AuthId = AuthId;
    }

    public int getStatus() {
        return Status;
    }

    public void setStatus(int Status) {
        this.Status = Status;
    }

    @Override
    public String toString() {
        String[] TypeStatus = {"inactive", "active"};
        
        return String.format("%-10d%-20s%-20s%-20d%-20s",
                this.getSubId(),
                this.getSubCode(),
                this.getSubName(),
                this.getAuthId(),
                TypeStatus[this.getStatus()]);
   }
    
}
