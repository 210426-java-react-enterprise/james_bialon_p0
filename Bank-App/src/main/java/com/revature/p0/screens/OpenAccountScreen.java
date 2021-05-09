package com.revature.p0.screens;

import com.revature.p0.daos.AccountDAO;
import com.revature.p0.daos.AccountTypeDAO;
import com.revature.p0.models.account.AccountType;
import com.revature.p0.util.scenemgmt.ScreenRouter;

import java.io.BufferedReader;

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
    private BufferedReader consoleReader;
    private ScreenRouter router;

    public OpenAccountScreen(BufferedReader consoleReader, ScreenRouter router) {
        super("OpenAccountScreen", "/openAcct");
        this.consoleReader = consoleReader;
        this.router = router;
    }

    public void render() {

        AccountType[] acctTypes = acctTypeDAO.getAllAcctTypes();




    }

}
