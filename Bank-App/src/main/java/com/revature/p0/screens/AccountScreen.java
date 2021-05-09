package com.revature.p0.screens;

import com.revature.p0.daos.AccountBalanceDAO;
import com.revature.p0.daos.AccountDAO;
import com.revature.p0.daos.UserDAO;
import com.revature.p0.models.account.Account;
import com.revature.p0.models.account.BankUser;
import com.revature.p0.util.scenemgmt.ScreenRouter;

import java.io.BufferedReader;

/**
 * Created by IntelliJ IDEA.
 * User: Jbialon
 * Date: 5/1/2021
 * Time: 11:10 AM
 * Description: {Insert Description}
 */
public class AccountScreen extends Screen {

    private AccountDAO acctDao = new AccountDAO();
    private BufferedReader consoleReader;
    private ScreenRouter router;
    private BankUser user;

    public AccountScreen(BufferedReader consoleReader, ScreenRouter router) {
        super("AccountScreen", "/accounts");
        this.consoleReader = consoleReader;
        this.router = router;
    }

    @Override
    public void render() {

        Account[] userAccounts;
        int counter = 1;

        try {

            userAccounts = acctDao.getAcct(user);

            getSelectionScreen(userAccounts);

            String userSelection = consoleReader.readLine();

            while (Integer.parseInt(userSelection) < 0 || Integer.parseInt(userSelection) > userAccounts.length) {

                System.out.println();
                System.out.println("Invalid selection.");
                System.out.println();
                getSelectionScreen(userAccounts);

                userSelection = consoleReader.readLine();

            }

            if (userSelection.equals(0)) {

                //TODO: route user to bank account opening screen

            } else {

                System.out.println();
                showAccountInfo(userAccounts[Integer.parseInt(userSelection)]);

                userSelection = consoleReader.readLine();

                while (Integer.parseInt(userSelection) < 0 || Integer.parseInt(userSelection) > 3) {

                    System.out.println();
                    System.out.println("Invalid selection.");
                    System.out.println();
                    showAccountInfo(userAccounts[Integer.parseInt(userSelection)]);

                    userSelection = consoleReader.readLine();

                }

                switch (userSelection) {
                    case "0":
                        //TODO exit

                    case "1":
                        //TODO deposit screen

                    case "2":
                        //TODO withdraw screen

                    case "3":
                        //TODO transaction screen
                }

            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private void getSelectionScreen(Account[] userAccounts) {

        int counter = 1;

        System.out.println("Select the account you want to view.");
        System.out.println("+----------------------------------+");

        for (Account acct : userAccounts) {
            System.out.println(counter + " - " + acct.getaName());

            counter++;
        }

        System.out.println("0 - Open a new account.");

        System.out.print("> ");
    }

    private void showAccountInfo(Account acct) {

        AccountBalanceDAO balanceDAO = new AccountBalanceDAO();

        System.out.printf("Account Name: %s\nBalance: %f", acct.getaName(), balanceDAO.getBalance(acct));
        System.out.println("");

        System.out.println("1 - Deposit");
        System.out.println("2 - Withdraw");
        System.out.println("3 - Transaction History");
        System.out.println("0 - Exit");

        System.out.print("> ");

    }
}
