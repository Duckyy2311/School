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
public class Setting {
    private int SettingId;
    private int TypeId;
    private String SettingTittle;
    private int SettingValue;
    private String DisOrder;
    private int Status;

    public Setting() {
    }

    public Setting(int SettingId, int TypeId, String SettingTittle, int SettingValue, String DisOrder, int Status) {
        this.SettingId = SettingId;
        this.TypeId = TypeId;
        this.SettingTittle = SettingTittle;
        this.SettingValue = SettingValue;
        this.DisOrder = DisOrder;
        this.Status = Status;
    }

    public int getSettingId() {
        return SettingId;
    }

    public void setSettingId(int SettingId) {
        this.SettingId = SettingId;
    }

    public int getTypeId() {
        return TypeId;
    }

    public void setTypeId(int TypeId) {
        this.TypeId = TypeId;
    }

    public String getSettingTittle() {
        return SettingTittle;
    }

    public void setSettingTittle(String SettingTittle) {
        this.SettingTittle = SettingTittle;
    }

    public int getSettingValue() {
        return SettingValue;
    }

    public void setSettingValue(int SettingValue) {
        this.SettingValue = SettingValue;
    }

    public String getDisOrder() {
        return DisOrder;
    }

    public void setDisOrder(String DisOrder) {
        this.DisOrder = DisOrder;
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
        String[] TypeValue = {"Zero", "Low","Medium","High"};
        String[] Type = {"WP", "Q&A","Task","Defect","Leakage"};
        return String.format("%-10d%-15s%-20s%-10s%-20s%-10s",
                this.getSettingId(),
                Type[this.getTypeId()-1],
                this.getSettingTittle(),
                TypeValue[this.getSettingValue()-1],
                this.getDisOrder(),
                TypeStatus[this.getStatus()]);
    }
    
}
