package com.revature.p0.util.scenemgmt;

import com.revature.p0.daos.AccountBalanceDAO;
import com.revature.p0.daos.AccountDAO;
import com.revature.p0.daos.AccountTransactionDAO;
import com.revature.p0.daos.UserDAO;
import com.revature.p0.screens.*;
import com.revature.p0.services.*;

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

        final UserDAO userDAO = new UserDAO();
        final AccountDAO acctDAO = new AccountDAO();
        final AccountBalanceDAO balanceDAO = new AccountBalanceDAO();
        final AccountTransactionDAO xActionDAO = new AccountTransactionDAO();

        final UserInputService inputService = new UserInputService();
        final BankUserService userService = new BankUserService(userDAO);
        final AccountOpeningService acctOpenService = new AccountOpeningService(acctDAO);
        final DepositService depositService = new DepositService(balanceDAO, xActionDAO);
        final WithdrawService withdrawService = new WithdrawService(balanceDAO, xActionDAO);

        router = new ScreenRouter();
        router.addScreen(new WelcomeScreen(consoleReader))
                .addScreen(new LoginScreen(consoleReader, userDAO))
                .addScreen(new RegisterScreen(consoleReader, userService))
                .addScreen(new AccountScreen(consoleReader, inputService))
                .addScreen(new OpenAccountScreen(consoleReader, acctOpenService))
                .addScreen(new DepositScreen(consoleReader, depositService))
                .addScreen(new WithdrawScreen(consoleReader, withdrawService))
                .addScreen(new TransactionScreen(consoleReader, xActionDAO));

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
