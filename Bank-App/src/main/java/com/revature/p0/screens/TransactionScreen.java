package com.revature.p0.screens;

import com.revature.p0.daos.AccountTransactionDAO;
import com.revature.p0.models.account.AccountTransaction;
import com.revature.p0.util.scenemgmt.ScreenRouter;
import com.revature.p0.util.singleton.CurrentAccount;

import java.io.BufferedReader;

/**
 * Created by IntelliJ IDEA.
 * User: Jbialon
 * Date: 5/10/2021
 * Time: 4:57 PM
 * Description: {Insert Description}
 */
public class TransactionScreen extends Screen {

    private BufferedReader consoleReader;
    private ScreenRouter router;
    private AccountTransactionDAO transactionDAO = new AccountTransactionDAO();

    public TransactionScreen(BufferedReader consoleReader, ScreenRouter router) {
        super("DepositScreen", "/transactions");
        this.consoleReader = consoleReader;
        this.router = router;
    }

    public void render() {

        try {
            AccountTransaction[] transactions = transactionDAO.getAllAcctTransactions(CurrentAccount.getInstance().getCurrentAccount());

            System.out.println("Transactions");
            System.out.println("+----------+");


            for (AccountTransaction xAction : transactions) {
                if (Double.toString(xAction.getTransactionAmt()).contains("-")) {
                    String negHandler = Double.toString(xAction.getTransactionAmt());
                    negHandler = negHandler.substring(1);
                    System.out.printf("-$%.2s | %s\n", negHandler, xAction.getDescription());

                } else {
                    System.out.printf("$%.2f | %s\n", xAction.getTransactionAmt(), xAction.getDescription());
                }
            }

            System.out.println();
            System.out.println("Enter 0 to go back to accounts screen");

            String userSelection = "1";

            while (!userSelection.equals("0")) {
                System.out.print(">");
                userSelection = consoleReader.readLine();
            }

            router.navigate("/accounts");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
