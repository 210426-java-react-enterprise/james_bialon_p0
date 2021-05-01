package com.revature.p0.models.account;

/**
 * Created by IntelliJ IDEA.
 * User: Jbialon
 * Date: 4/29/2021
 * Time: 9:17 AM
 * Description: {Insert Description}
 */
public class Account {
    private int aID;
    private String uID;
    private String jUID;

    public Account(int aID, String uID, String jUID) {
        this.aID = aID;
        this.uID = uID;
        this.jUID = jUID;
    }

    public Account(String uID, String jUID) {
        this.uID = uID;
        this.jUID = jUID;
    }

    public Account(String uID) {
        this.uID = uID;
    }

    public int getaID() {
        return aID;
    }

    public void setaID(int aID) {
        this.aID = aID;
    }

    public String getuID() {
        return uID;
    }

    public void setuID(String uID) {
        this.uID = uID;
    }

    public String getjUID() {
        return jUID;
    }

    public void setjUID(String jUID) {
        this.jUID = jUID;
    }
}
