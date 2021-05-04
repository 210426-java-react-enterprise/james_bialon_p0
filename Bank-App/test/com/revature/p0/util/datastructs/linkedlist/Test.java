package com.revature.p0.util.datastructs.linkedlist;

import com.revature.p0.models.account.AccountTransaction;


/**
 * Created by IntelliJ IDEA.
 * User: Jbialon
 * Date: 5/1/2021
 * Time: 3:21 PM
 * Description: {Insert Description}
 */
public class Test {
    public static void main(String[] args) {
        LinkedTransactionList xActionList = new LinkedTransactionList();

        xActionList.add(new AccountTransaction(0, 1, 1000.25));

        System.out.println(xActionList.pop().getTransactionAmt());
    }
}