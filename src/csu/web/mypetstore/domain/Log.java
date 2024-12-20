package csu.web.mypetstore.domain;

import java.sql.Timestamp;

public class Log {
    int id;
    private String userName;
    private String actionType;
    private String actionDetails;
    private java.sql.Timestamp requestTime;

    public Log() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getActionType() {
        return actionType;
    }

    public void setActionType(String actionType) {
        this.actionType = actionType;
    }

    public String getActionDetails() {
        return actionDetails;
    }

    public void setActionDetails(String actionDetails) {
        this.actionDetails = actionDetails;
    }

    public Timestamp getRequestTime() {
        return requestTime;
    }

    public void setRequestTime(java.sql.Timestamp requestTime) {
        this.requestTime = requestTime;
    }
}
