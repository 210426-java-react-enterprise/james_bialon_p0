package com.revature.p0;

import com.revature.p0.models.account.AccountTransaction;
import com.revature.p0.util.datastructs.linkedlist.LinkedList;
import com.revature.p0.util.scenemgmt.AppState;

/**
 * Created by IntelliJ IDEA.
 * User: Jbialon
 * Date: 4/29/2021
 * Time: 9:06 AM
 * Description: {Insert Description}
 */
public class Driver {
    private static AppState app = new AppState();

    public static void main(String[] args) {
        while (app.isAppRunning()) {
            app.getRouter().navigate("/welcome");
        }
    }

    public static AppState app() {
        return app;
    }
}
