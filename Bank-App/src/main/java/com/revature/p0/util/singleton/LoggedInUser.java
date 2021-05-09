package com.revature.p0.util.singleton;

import com.revature.p0.models.account.BankUser;

/**
 * Created by IntelliJ IDEA.
 * User: Jbialon
 * Date: 5/9/2021
 * Time: 6:04 PM
 * Description: {Insert Description}
 */
public class LoggedInUser {

    private static LoggedInUser userSingleton = new LoggedInUser();
    private BankUser loggedInUser = null;

    private LoggedInUser() {

    }

    public static LoggedInUser getInstance() {
        return userSingleton;
    }

    public BankUser getLoggedInUser() { return loggedInUser; }

    public void setLoggedInUser(BankUser currUser) {
        loggedInUser = currUser;
    }

}
