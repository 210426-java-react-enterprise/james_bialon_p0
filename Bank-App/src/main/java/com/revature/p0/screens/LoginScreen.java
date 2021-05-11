package com.revature.p0.screens;

import com.revature.p0.daos.UserDAO;
import com.revature.p0.models.account.BankUser;
import com.revature.p0.util.singleton.LoggedInUser;

import java.io.BufferedReader;

import static com.revature.p0.Driver.app;

/**
 * Created by IntelliJ IDEA.
 * User: Jbialon
 * Date: 5/1/2021
 * Time: 11:09 AM
 * Description: {Insert Description}
 */
public class LoginScreen extends Screen {

    private UserDAO userDao;
    private BufferedReader consoleReader;

    public LoginScreen(BufferedReader consoleReader, UserDAO userDAO) {
        super("LoginScreen", "/login");
        this.consoleReader = consoleReader;
        this.userDao = userDAO;
    }

    public void render() {

        try {
            String username;
            String password;

            System.out.println("Log into your account!");
            System.out.println("+--------------------+");

            System.out.print("Username: ");
            username = consoleReader.readLine();

            System.out.print("Password: ");
            password = consoleReader.readLine();

            if (username != null && !username.isEmpty() && password != null && !password.isEmpty()) {
                BankUser authenticatedUser = userDao.findUserByUsernameAndPassword(username, password);
                if (authenticatedUser != null) {
                    LoggedInUser.getInstance().setLoggedInUser(authenticatedUser);
                    System.out.println("Login successful!");
                    app().getRouter().navigate("/accounts");

                } else {
                    System.out.println("Login failed!");

                    /*
                        The below code is not necessary, because if the login fails, we will fall
                        out of this method
                     */
                    app().getRouter().navigate("/welcome");
                }
            } else {
                System.out.println("It looks like you didn't provide any credentials!");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }



}
