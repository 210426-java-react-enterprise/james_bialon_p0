package com.revature.p0.util.singleton;

import com.revature.p0.models.account.Account;
import com.revature.p0.models.account.BankUser;

/**
 * Created by IntelliJ IDEA.
 * User: Jbialon
 * Date: 5/10/2021
 * Time: 10:47 AM
 * Description: {Insert Description}
 */
public class CurrentAccount {

    private static CurrentAccount acctSingleton = new CurrentAccount();
    private Account currentAccount = null;

    private CurrentAccount() {

    }

    public static CurrentAccount getInstance() {
        return acctSingleton;
    }

    public Account getCurrentAccount() { return currentAccount; }

    public void setCurrentAccount(Account currAcct) {
        currentAccount = currAcct;
    }

}
