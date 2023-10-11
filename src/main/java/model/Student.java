package model;

public class Student {
    private String userName = "";
    private String userNumber = "";
    private String ipAddress = "";
    private String port = "";
    private String courseName = "综合程序设计";

    public void setUserName(String userName) {
        this.userName = userName;
    }
    public void setUserNumber(String userNumber) {
        this.userNumber = userNumber;
    }
    public void setIpAddress(String ipAddress) {
        this.ipAddress = ipAddress;
    }
    public void setPort(String port) {
        this.port = port;
    }
    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public String getUserName() {
        return this.userName;
    }
    public String getUserNumber() {
        return this.userNumber;
    }
    public String getIpAddress() {
        return this.ipAddress;
    }
    public String getPort() {
        return this.port;
    }
    public String getCourseName() { return this.courseName; }
}
