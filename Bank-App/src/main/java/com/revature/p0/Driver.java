package com.revature.p0;

import com.revature.p0.models.account.AccountTransaction;
import com.revature.p0.util.datastructs.linkedlist.LinkedList;

/**
 * Created by IntelliJ IDEA.
 * User: Jbialon
 * Date: 4/29/2021
 * Time: 9:06 AM
 * Description: {Insert Description}
 */
public class Driver {
    public static void main(String[] args) {
        LinkedList<AccountTransaction> test = new LinkedList();

        test.add(new AccountTransaction(1,1,1000.25));

        System.out.println(test.get(0).getTransactionAmt());


    }
}
