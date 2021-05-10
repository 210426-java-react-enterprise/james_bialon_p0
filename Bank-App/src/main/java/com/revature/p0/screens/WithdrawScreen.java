package com.revature.p0.screens;

import com.revature.p0.daos.AccountBalanceDAO;
import com.revature.p0.daos.AccountTransactionDAO;
import com.revature.p0.models.account.AccountTransaction;
import com.revature.p0.util.scenemgmt.ScreenRouter;
import com.revature.p0.util.singleton.CurrentAccount;

import java.io.BufferedReader;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by IntelliJ IDEA.
 * User: Jbialon
 * Date: 5/10/2021
 * Time: 4:45 PM
 * Description: {Insert Description}
 */
public class WithdrawScreen extends Screen{

    private BufferedReader consoleReader;
    private ScreenRouter router;
    private AccountBalanceDAO balanceDAO = new AccountBalanceDAO();
    private AccountTransactionDAO transactionDAO = new AccountTransactionDAO();
    private AccountTransaction newTransaction = new AccountTransaction();

    String regex = "[0-9]*(\\.[0-9]{0,2})?";
    Pattern p = Pattern.compile(regex);

    public WithdrawScreen(BufferedReader consoleReader, ScreenRouter router) {
        super("DepositScreen", "/withdraw");
        this.consoleReader = consoleReader;
        this.router = router;
    }

    public void render() {

        try {
            System.out.println();
            System.out.println("How much would you like to withdraw?");
            System.out.println("+--------------------------------+");
            System.out.print("> $");
            String userSelection = consoleReader.readLine();

            Matcher m = p.matcher(userSelection);

            while (!m.matches()) {
                System.out.println();
                System.out.println("How much would you like to withdraw?");
                System.out.println("+--------------------------------+");
                System.out.print("> $");

                userSelection = consoleReader.readLine();

                m = p.matcher(userSelection);
            }

            double newBalance = balanceDAO.getBalance(CurrentAccount.getInstance().getCurrentAccount()) - Double.parseDouble(userSelection);

            if (newBalance < 0) {
                System.out.println("Sorry you do not have the funds available for this transaction.");
                router.navigate("/accounts");
            }

            balanceDAO.saveBalance(CurrentAccount.getInstance().getCurrentAccount(), newBalance);

            newTransaction.setAcctID(CurrentAccount.getInstance().getCurrentAccount().getaID());
            newTransaction.setTransactionAmt(Double.parseDouble("-" + userSelection));
            newTransaction.setDescription("Withdraw");

            transactionDAO.saveTransaction(newTransaction);

            router.navigate("/accounts");

        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
