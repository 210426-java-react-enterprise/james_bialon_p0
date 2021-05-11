package com.revature.p0.screens;

import com.revature.p0.daos.AccountBalanceDAO;
import com.revature.p0.daos.AccountTransactionDAO;
import com.revature.p0.exceptions.InvalidRequestException;
import com.revature.p0.models.account.AccountTransaction;
import com.revature.p0.services.DepositService;
import com.revature.p0.services.WithdrawService;
import com.revature.p0.util.scenemgmt.ScreenRouter;
import com.revature.p0.util.singleton.CurrentAccount;

import java.io.BufferedReader;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static com.revature.p0.Driver.app;

/**
 * Created by IntelliJ IDEA.
 * User: Jbialon
 * Date: 5/10/2021
 * Time: 4:45 PM
 * Description: {Insert Description}
 */
public class WithdrawScreen extends Screen{

    private BufferedReader consoleReader;

    private AccountTransactionDAO transactionDAO = new AccountTransactionDAO();
    private WithdrawService withdrawService;

    public WithdrawScreen(BufferedReader consoleReader, WithdrawService withdrawService) {
        super("DepositScreen", "/withdraw");
        this.consoleReader = consoleReader;
        this.withdrawService = withdrawService;
    }

    public void render() {

        try {
            System.out.println();
            System.out.println("How much would you like to withdraw?");
            System.out.println("+--------------------------------+");
            System.out.print("> $");
            String userSelection = consoleReader.readLine();

            try {
                if (withdrawService.createBalance(userSelection)) {
                    System.out.println("Withdraw Success");
                    app().getRouter().navigate("/accounts");
                }
            } catch (InvalidRequestException ire) {
                System.out.println("Invalid amount, try again.");
                app().getRouter().navigate("/withdraw");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
