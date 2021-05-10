package com.revature.p0.screens;

import com.revature.p0.daos.AccountBalanceDAO;
import com.revature.p0.daos.AccountDAO;
import com.revature.p0.daos.UserDAO;
import com.revature.p0.models.account.Account;
import com.revature.p0.models.account.BankUser;
import com.revature.p0.util.scenemgmt.ScreenRouter;
import com.revature.p0.util.singleton.CurrentAccount;
import com.revature.p0.util.singleton.LoggedInUser;

import java.io.BufferedReader;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static com.revature.p0.Driver.app;

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

    String regex = "[0-9]+";
    Pattern p = Pattern.compile(regex);

    public AccountScreen(BufferedReader consoleReader, ScreenRouter router) {
        super("AccountScreen", "/accounts");
        this.consoleReader = consoleReader;
        this.router = router;
    }

    @Override
    public void render() {

        user = LoggedInUser.getInstance().getLoggedInUser();
        Account[] userAccounts;
        int counter = 1;

        try {

            userAccounts = acctDao.getAcct(user);

            getSelectionScreen(userAccounts);

            String userSelection = consoleReader.readLine();

            Matcher m = p.matcher(userSelection);

            while (Integer.parseInt(userSelection) < 0 || Integer.parseInt(userSelection) > userAccounts.length || !m.matches()) {

                System.out.println();
                System.out.println("Invalid selection.");
                System.out.println();
                getSelectionScreen(userAccounts);

                userSelection = consoleReader.readLine();

                m = p.matcher(userSelection);
            }

            if (userSelection.equals("0")) {

                System.out.println("Routing to account opening screen...");
                router.navigate("/openAcct");

            } else {

                System.out.println();
                CurrentAccount.getInstance().setCurrentAccount(userAccounts[Integer.parseInt(userSelection) - 1]);
                showAccountInfo(CurrentAccount.getInstance().getCurrentAccount());

                userSelection = consoleReader.readLine();

                p.matcher(userSelection);

                while (Integer.parseInt(userSelection) < 0 || Integer.parseInt(userSelection) > 3 || !m.matches()) {

                    System.out.println();
                    System.out.println("Invalid selection.");
                    System.out.println();
                    showAccountInfo(userAccounts[Integer.parseInt(userSelection)]);

                    userSelection = consoleReader.readLine();

                    m = p.matcher(userSelection);

                }

                switch (userSelection) {
                    case "0":
                        System.out.println("Have a beautiful time!");
                        Thread.sleep(1000);
                        app().setAppRunning(false);
                        break;

                    case "1":
                        System.out.println("Routing to deposit screen...");
                        router.navigate("/deposit");
                        break;

                    case "2":
                        System.out.println("Routing to withdraw screen...");
                        router.navigate("/withdraw");
                        break;

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

        System.out.printf("Account Name: %s\nBalance: $%.2f", acct.getaName(), balanceDAO.getBalance(acct));
        System.out.println("");

        System.out.println("1 - Deposit");
        System.out.println("2 - Withdraw");
        System.out.println("3 - Transaction History");
        System.out.println("0 - Exit");

        System.out.print("> ");

    }
}
