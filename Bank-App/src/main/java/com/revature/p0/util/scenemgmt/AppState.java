package com.revature.p0.util.scenemgmt;

import com.revature.p0.daos.AccountBalanceDAO;
import com.revature.p0.daos.AccountDAO;
import com.revature.p0.daos.UserDAO;
import com.revature.p0.screens.*;
import com.revature.p0.services.AccountOpeningService;
import com.revature.p0.services.BankUserService;
import com.revature.p0.services.DepositService;
import com.revature.p0.services.WithdrawService;

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
        final AccountDAO acctDAO = new AccountDAO();
        final AccountBalanceDAO balanceDAO = new AccountBalanceDAO();

        final BankUserService userService = new BankUserService(userDao);
        final AccountOpeningService acctOpenService = new AccountOpeningService(acctDAO);
        final DepositService depositService = new DepositService(balanceDAO);
        final WithdrawService withdrawService = new WithdrawService(balanceDAO);

        router = new ScreenRouter();
        router.addScreen(new WelcomeScreen(consoleReader, router))
                .addScreen(new LoginScreen(consoleReader, router))
                .addScreen(new RegisterScreen(consoleReader, userService))
                .addScreen(new AccountScreen(consoleReader, router))
                .addScreen(new OpenAccountScreen(consoleReader, acctOpenService))
                .addScreen(new DepositScreen(consoleReader, depositService))
                .addScreen(new WithdrawScreen(consoleReader, withdrawService))
                .addScreen(new TransactionScreen(consoleReader, router));

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
