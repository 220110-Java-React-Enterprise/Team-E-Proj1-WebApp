package models;

public class Users {
    private  String tableName;
    private int users_id = 0;
    private String username;
    private String fName;
    private String lName;


    public Users(){};

    public Users(String tablename, int userID, String username, String fName, String lname) {
        this.tableName = tablename;
        this.users_id = userID;
        this.username = username;
        this.fName = fName;
        this.lName = lname;

    }

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public int getUsers_id() {
        return users_id;
    }

    public void setUsers_id(int userID) {
        this.users_id = userID;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getFName() {
        return fName;
    }

    public void setFName(String fName) {
        this.fName = fName;
    }

    public String getLName() {
        return lName;
    }

    public void setLName(String lName) {
        this.lName = lName;
    }
}