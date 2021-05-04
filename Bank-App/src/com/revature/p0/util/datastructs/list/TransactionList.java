package com.revature.p0.util.datastructs.list;

import com.revature.p0.models.account.AccountTransaction;

/**
 * Created by IntelliJ IDEA.
 * User: Jbialon
 * Date: 4/30/2021
 * Time: 4:05 PM
 * Description: {Insert Description}
 */
public interface TransactionList<T> {

    void traverse();
    void reverseTraverse();
    void add(AccountTransaction data);
    AccountTransaction pop();
    AccountTransaction getByAmount(double amt);
    int size();

}
