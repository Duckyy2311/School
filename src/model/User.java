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
 * @author Admin
 */
public class User {
    private int UserId;
    private String RollNumber;
    private String FullName;
    private int Gender;
    private Date DOB;
    private String Email;
    private String Mobile;
    private String AvtLink;
    private String FbLink;
    private int RoleId;
    private String Password;    
    private int Status;

    public User() {
    }

    public User(int UserId, String RollNumber, String FullName, int Gender, Date DOB, String Email, String Mobile, String AvtLink, String FbLink, int RoleId, String Password, int Status) {
        this.UserId = UserId;
        this.RollNumber = RollNumber;
        this.FullName = FullName;
        this.Gender = Gender;
        this.DOB = DOB;
        this.Email = Email;
        this.Mobile = Mobile;
        this.AvtLink = AvtLink;
        this.FbLink = FbLink;
        this.RoleId = RoleId;
        this.Password = Password;
        this.Status = Status;
    }

    public int getUserId() {
        return UserId;
    }

    public void setUserId(int UserId) {
        this.UserId = UserId;
    }

    public String getRollNumber() {
        return RollNumber;
    }

    public void setRollNumber(String RollNumber) {
        this.RollNumber = RollNumber;
    }

    public String getFullName() {
        return FullName;
    }

    public void setFullName(String FullName) {
        this.FullName = FullName;
    }

    public int getGender() {
        return Gender;
    }

    public void setGender(int Gender) {
        this.Gender = Gender;
    }

    public Date getDOB() {
        return DOB;
    }

    public void setDOB(Date DOB) {
        this.DOB = DOB;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String Email) {
        this.Email = Email;
    }

    public String getMobile() {
        return Mobile;
    }

    public void setMobile(String Mobile) {
        this.Mobile = Mobile;
    }

    public String getAvtLink() {
        return AvtLink;
    }

    public void setAvtLink(String AvtLink) {
        this.AvtLink = AvtLink;
    }

    public String getFbLink() {
        return FbLink;
    }

    public void setFbLink(String FbLink) {
        this.FbLink = FbLink;
    }

    public int getRoleId() {
        return RoleId;
    }

    public void setRoleId(int RoleId) {
        this.RoleId = RoleId;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String Password) {
        this.Password = Password;
    }

    public int getStatus() {
        return Status;
    }

    public void setStatus(int Status) {
        this.Status = Status;
    }

    @Override
    public String toString() {  
        String[] TypeStatus = {"Off", "Onl"};
        String[] TypeGender = {"Male", "Female","Other"};
        String[] TypeRole = {"Student", "Trainer","Admin","Manager"};
        SimpleDateFormat SDF = new SimpleDateFormat("yyyy/MM/dd");
        return String.format("%-10d%-15s%-20s%-10s%-15s%-25s%-10s%-20s%-20s%-10s%-10s",
                this.getUserId(),
                this.getRollNumber(),
                this.getFullName(),
                TypeGender[this.getGender()-1],
                SDF.format(this.getDOB()),
                this.getEmail(),
                this.getMobile(),
                this.getAvtLink(),
                this.getFbLink(),
                TypeRole[this.getRoleId()-1],
                TypeStatus[this.getStatus()]);
    }
}
