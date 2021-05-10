package com.revature.p0.screens;

import com.revature.p0.daos.AccountDAO;
import com.revature.p0.daos.AccountTypeDAO;
import com.revature.p0.models.account.Account;
import com.revature.p0.models.account.AccountType;
import com.revature.p0.util.scenemgmt.ScreenRouter;
import com.revature.p0.util.singleton.LoggedInUser;

import java.io.BufferedReader;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by IntelliJ IDEA.
 * User: Jbialon
 * Date: 5/9/2021
 * Time: 3:54 PM
 * Description: {Insert Description}
 */
public class OpenAccountScreen extends Screen{

    private AccountTypeDAO acctTypeDAO = new AccountTypeDAO();
    private AccountDAO acctDAO = new AccountDAO();
    private Account newAcct = new Account();
    private BufferedReader consoleReader;
    private ScreenRouter router;

    String regex = "[0-9]+";
    Pattern p = Pattern.compile(regex);

    public OpenAccountScreen(BufferedReader consoleReader, ScreenRouter router) {
        super("OpenAccountScreen", "/openAcct");
        this.consoleReader = consoleReader;
        this.router = router;
    }

    public void render() {

        try {

            AccountType[] acctTypes = acctTypeDAO.getAllAcctTypes();

            showAcctTypes(acctTypes);

            String userSelection = consoleReader.readLine();

            Matcher m = p.matcher(userSelection);

            while (Integer.parseInt(userSelection) < 0 || Integer.parseInt(userSelection) > 2 || !m.matches()) {

                System.out.println();
                System.out.println("Invalid selection.");
                System.out.println();
                showAcctTypes(acctTypes);

                userSelection = consoleReader.readLine();

                m = p.matcher(userSelection);

            }

            if (userSelection.equals("0")) {
                router.navigate("/accounts");
            }

            newAcct.setuID(LoggedInUser.getInstance().getLoggedInUser().getuID());
            newAcct.settID(acctTypes[Integer.parseInt(userSelection) - 1].getId());

            System.out.print("Account Name: ");
            userSelection = consoleReader.readLine();

            while (userSelection.trim().length() < 1) {
                System.out.println();
                System.out.println("Please enter a proper name");
                System.out.println();
                System.out.print("Account Name: ");

                userSelection = consoleReader.readLine();
            }

            newAcct.setaName(userSelection);

            newAcct = acctDAO.saveNewAcct(newAcct);

            System.out.println("Account created successful!");
            router.navigate("/accounts");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void showAcctTypes(AccountType[] acctTypes) {

        int counter = 1;

        System.out.println("What type of account would you like to open?");
        System.out.println("+------------------------------------------+");

        for (AccountType acctType : acctTypes) {
            System.out.printf("%d - Type: %s | Interest: %.2f | Monthly Fee $%.2f\n", counter, acctType.getType(), acctType.getInterest(), acctType.getMonthlyFees());
            counter++;
        }

        System.out.println("0 - Account Menu");

        System.out.print("> ");
    }
}
