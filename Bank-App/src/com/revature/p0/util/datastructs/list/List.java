package com.revature.p0.util.datastructs.list;

import com.revature.p0.models.account.AccountTransaction;

/**
 * Created by IntelliJ IDEA.
 * User: Jbialon
 * Date: 5/4/2021
 * Time: 9:35 AM
 * Description: {Insert Description}
 */
public interface List<T> {

    void add(T data);
    boolean contains(T data);
    AccountTransaction pop();
    int size();

}
