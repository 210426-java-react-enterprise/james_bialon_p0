package com.revature.p0.screens;

import com.revature.p0.util.scenemgmt.ScreenRouter;

import java.io.BufferedReader;

/**
 * Created by IntelliJ IDEA.
 * User: Jbialon
 * Date: 5/10/2021
 * Time: 10:20 AM
 * Description: {Insert Description}
 */
public class DepositScreen extends Screen{

    private BufferedReader consoleReader;
    private ScreenRouter router;

    public DepositScreen(BufferedReader consoleReader, ScreenRouter router) {
        super("DepositScreen", "/openAcct");
        this.consoleReader = consoleReader;
        this.router = router;
    }

    public void render() {

    }

}
