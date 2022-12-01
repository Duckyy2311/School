package model;

/**
 *
 * @author Dinh Quoc Tung
 */
public class Function {

    private int function_id;
    private int team_id;
    private String topic_name;
    private String function_name;
    private int feature_id;
    private String feature_name;
    private String access_role;
    private String description;
    private String complexity_id;
    private String owner_id;
    private int priority;
    private int status;
    private String owner_name;
    public Function() {

    }

    public Function(int function_id, int team_id, String function_name, int feature_id, String access_role, String description, String complexity_id, String owner_id, int priority, int status) {
        this.function_id = function_id;
        this.team_id = team_id;
        this.function_name = function_name;
        this.feature_id = feature_id;
        this.access_role = access_role;
        this.description = description;
        this.complexity_id = complexity_id;
        this.owner_id = owner_id;
        this.priority = priority;
        this.status = status;
    }

    public Function(int function_id, String topic_name, String function_name, String feature_name, String access_role, String description, String complexity_id, String owner_id, int priority, int status) {
        this.function_id = function_id;
        this.topic_name = topic_name;
        this.function_name = function_name;
        this.feature_name = feature_name;
        this.access_role = access_role;
        this.description = description;
        this.complexity_id = complexity_id;
        this.owner_id = owner_id;
        this.priority = priority;
        this.status = status;
    }

    public Function(int function_id, int team_id, String topic_name, String function_name, int feature_id, String feature_name, String access_role, String description, String complexity_id, String owner_id, int priority, int status) {
        this.function_id = function_id;
        this.team_id = team_id;
        this.topic_name = topic_name;
        this.function_name = function_name;
        this.feature_id = feature_id;
        this.feature_name = feature_name;
        this.access_role = access_role;
        this.description = description;
        this.complexity_id = complexity_id;
        this.owner_id = owner_id;
        this.priority = priority;
        this.status = status;
    }

    public Function(int function_id, String topic_name, String function_name, String feature_name, String access_role, String owner_name, String description, int status) {
        this.function_id = function_id;
        this.topic_name = topic_name;
        this.function_name = function_name;
        this.feature_name = feature_name;
        this.access_role = access_role;
        this.description = description;
        this.status = status;
        this.owner_name = owner_name;
    }
    
    public String getOwner_name() {
        return owner_name;
    }

    public void setOwner_name(String owner_name) {
        this.owner_name = owner_name;
    }

    public int getFunction_id() {
        return function_id;
    }

    public void setFunction_id(int function_id) {
        this.function_id = function_id;
    }

    public int getTeam_id() {
        return team_id;
    }

    public void setTeam_id(int team_id) {
        this.team_id = team_id;
    }

    public String getFunction_name() {
        return function_name;
    }

    public void setFunction_name(String function_name) {
        this.function_name = function_name;
    }

    public int getFeature_id() {
        return feature_id;
    }

    public void setFeature_id(int feature_id) {
        this.feature_id = feature_id;
    }

    public String getAccess_role() {
        return access_role;
    }

    public void setAccess_role(String access_role) {
        this.access_role = access_role;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getComplexity_id() {
        return complexity_id;
    }

    public void setComplexity_id(String complexity_id) {
        this.complexity_id = complexity_id;
    }

    public String getOwner_id() {
        return owner_id;
    }

    public void setOwner_id(String owner_id) {
        this.owner_id = owner_id;
    }

    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getTopic_name() {
        return topic_name;
    }

    public void setTopic_name(String topic_name) {
        this.topic_name = topic_name;
    }

    public String getFeature_name() {
        return feature_name;
    }

    public void setFeature_name(String feature_name) {
        this.feature_name = feature_name;
    }

    @Override
    public String toString() {
        String[] TypeStatus = {"Pending", "Planned", "Evaluated", "Rejected"};

        return String.format("|%-5d|%-15s|%-15s|%-15s|%-15s|%-15s|%-15s|%-10s",
                this.getFunction_id(),
                this.getFunction_name(),
                this.getTopic_name(),                
                this.getFeature_name(),
                this.getAccess_role(),
                this.getDescription(),
                this.getOwner_name(),
                TypeStatus[this.getStatus() - 1]);

    }

}
