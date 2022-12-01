package model;

/**
 *
 * @author Dinh Quoc Tung
 */
public class SubjectSetting {

    private int setting_id;
    private int subject_id;
    private String subject_name;
    private int type_id;
    private String setting_tittle;
    private int setting_value;
    private String display_order;
    private int status;

    public SubjectSetting() {

    }

    public SubjectSetting(int setting_id, int subject_id, int type_id, String setting_tittle, int setting_value, String display_order, int status) {
        this.setting_id = setting_id;
        this.subject_id = subject_id;
        this.type_id = type_id;
        this.setting_tittle = setting_tittle;
        this.setting_value = setting_value;
        this.display_order = display_order;
        this.status = status;
    }

    public SubjectSetting(int subject_id, int type_id, String setting_tittle, int setting_value, String display_order, int status) {
        this.subject_id = subject_id;
        this.type_id = type_id;
        this.setting_tittle = setting_tittle;
        this.setting_value = setting_value;
        this.display_order = display_order;
        this.status = status;
    }

    
    public SubjectSetting(int setting_id, String subject_name, int type_id, String setting_tittle, int setting_value, String display_order, int status) {
        this.setting_id = setting_id;
        this.subject_name = subject_name;
        this.type_id = type_id;
        this.setting_tittle = setting_tittle;
        this.setting_value = setting_value;
        this.display_order = display_order;
        this.status = status;
    }

    
    
    public int getSetting_id() {
        return setting_id;
    }

    public void setSetting_id(int setting_id) {
        this.setting_id = setting_id;
    }

    public int getSubject_id() {
        return subject_id;
    }

    public void setSubject_id(int subject_id) {
        this.subject_id = subject_id;
    }

    public int getType_id() {
        return type_id;
    }

    public void setType_id(int type_id) {
        this.type_id = type_id;
    }

    public String getSetting_tittle() {
        return setting_tittle;
    }

    public void setSetting_tittle(String setting_tittle) {
        this.setting_tittle = setting_tittle;
    }

    public int getSetting_value() {
        return setting_value;
    }

    public void setSetting_value(int setting_value) {
        this.setting_value = setting_value;
    }

    public String getDisplay_order() {
        return display_order;
    }

    public void setDisplay_order(String display_order) {
        this.display_order = display_order;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getSubject_name() {
        return subject_name;
    }

    public void setSubject_name(String subject_name) {
        this.subject_name = subject_name;
    }
    

    @Override
    public String toString() {
        String[] TypeStatus = {"inactive", "active"};
        String[] Type = {"Grade","Complexity","Quality","Defect","Leakage"};
        String[] TypeValue = {"Zero", "Low", "Medium", "High"};

        return String.format("%-15d%-15s%-15s%-20s%-15s%-20s%-10s",
                this.getSetting_id(),
                this.getSubject_name(),
                Type[this.getType_id() - 1],
                this.getSetting_tittle(),
                TypeValue[this.getSetting_value() - 1],
                this.getDisplay_order(),
                TypeStatus[this.getStatus()]);
    }

}