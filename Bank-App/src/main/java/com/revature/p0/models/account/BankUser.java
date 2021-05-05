package com.revature.p0.models.account;

/**
 * Created by IntelliJ IDEA.
 * User: Jbialon
 * Date: 4/29/2021
 * Time: 9:06 AM
 * Description: {Insert Description}
 */
public class BankUser {
    private int uID;
    private String fName;
    private String lName;
    private String email;
    private String password;

    public BankUser(String fName, String lName, String email, String password) {
        this.fName = fName;
        this.lName = lName;
        this.email = email;
        this.password = password;
    }

    public BankUser(int uID, String fName, String lName, String email, String password) {
        this.uID = uID;
        this.fName = fName;
        this.lName = lName;
        this.email = email;
        this.password = password;
    }

    public int getuID() {
        return uID;
    }

    public void setuID(int uID) {
        this.uID = uID;
    }

    public String getfName() {
        return fName;
    }

    public void setfName(String fName) {
        this.fName = fName;
    }

    public String getlName() {
        return lName;
    }

    public void setlName(String lName) {
        this.lName = lName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
