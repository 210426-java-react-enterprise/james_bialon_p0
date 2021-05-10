package com.revature.p0.util.scenemgmt;

import com.revature.p0.daos.UserDAO;
import com.revature.p0.screens.*;
import com.revature.p0.services.BankUserService;

import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * Created by IntelliJ IDEA.
 * User: Jbialon
 * Date: 5/1/2021
 * Time: 11:21 AM
 * Description: {Insert Description}
 */
public class AppState{
    private BufferedReader consoleReader;
    private ScreenRouter router;
    private boolean appRunning;

    public AppState() {
        System.out.println("Initializing application...");

        appRunning = true;
        consoleReader = new BufferedReader(new InputStreamReader(System.in));

        final UserDAO userDao = new UserDAO();
        final BankUserService userService = new BankUserService(userDao);

        router = new ScreenRouter();
        router.addScreen(new WelcomeScreen(consoleReader, router))
                .addScreen(new LoginScreen(consoleReader, router))
                .addScreen(new RegisterScreen(consoleReader, userService))
                .addScreen(new AccountScreen(consoleReader, router))
                .addScreen(new OpenAccountScreen(consoleReader, router))
                .addScreen(new DepositScreen(consoleReader, router))
                .addScreen(new WithdrawScreen(consoleReader, router));

        System.out.println("Application initialized!");
    }

    public ScreenRouter getRouter() {
        return router;
    }

    public boolean isAppRunning() {
        return appRunning;
    }

    public void setAppRunning(boolean appRunning) {
        this.appRunning = appRunning;
    }
}
