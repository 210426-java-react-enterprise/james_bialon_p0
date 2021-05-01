package com.revature.p0.models.account;

/**
 * Created by IntelliJ IDEA.
 * User: Jbialon
 * Date: 4/29/2021
 * Time: 11:09 AM
 * Description: {Insert Description}
 */
public class AccountTransaction {
    private int transactionID;
    private int acctID;
    private double transactionAmt;

    public AccountTransaction(int transactionID, int acctID, double transactionAmt) {
        this.transactionID = transactionID;
        this.acctID = acctID;
        this.transactionAmt = transactionAmt;
    }

    public AccountTransaction(int acctID, double transactionAmt) {
        this.acctID = acctID;
        this.transactionAmt = transactionAmt;
    }

    public int getTransactionID() {
        return transactionID;
    }

    public void setTransactionID(int transactionID) {
        this.transactionID = transactionID;
    }

    public int getAcctID() {
        return acctID;
    }

    public void setAcctID(int acctID) {
        this.acctID = acctID;
    }

    public double getTransactionAmt() {
        return transactionAmt;
    }

    public void setTransactionAmt(int transactionAmt) {
        this.transactionAmt = transactionAmt;
    }
}
