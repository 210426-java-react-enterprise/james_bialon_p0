package com.revature.p0.screens;

import com.revature.p0.models.account.BankUser;
import com.revature.p0.services.BankUserService;

import java.io.BufferedReader;

/**
 * Created by IntelliJ IDEA.
 * User: Jbialon
 * Date: 5/1/2021
 * Time: 11:09 AM
 * Description: {Insert Description}
 */
public class RegisterScreen extends Screen {

    //private UserService userService;
    private BufferedReader consoleReader;
    private BankUserService userService;

    public RegisterScreen(BufferedReader consoleReader, BankUserService userService) {
        super("RegisterScreen", "/register");
        this.consoleReader = consoleReader;
        this.userService = userService;
    }

    public void render() {

        String firstName;
        String lastName;
        String email;
        String username;
        String password;
        int age;

        // ok but a little verbose
//        InputStreamReader inputStreamReader = new InputStreamReader(System.in);
//        BufferedReader consoleReader = new BufferedReader(inputStreamReader);

        try {
            // risky code that might through an exception

            System.out.println("Register for a new account!");
            System.out.println("+-------------------------+");

            System.out.print("First name: ");
            firstName = consoleReader.readLine(); // throws Exception here

            System.out.print("Last name: ");
            lastName = consoleReader.readLine();

            System.out.print("Email: ");
            email = consoleReader.readLine();

            System.out.print("Username: ");
            username = consoleReader.readLine();

            System.out.print("Password: ");
            password = consoleReader.readLine();

            BankUser newUser = new BankUser(firstName, lastName, username, email, password);
            userService.register(newUser);

        } catch (NumberFormatException nfe) {
            // do something about these!
            System.err.println("You provided an incorrect value for your age! Please try again!");
            this.render(); // this breaks some stuff! we will need to fix this
        } /*catch (InvalidRequestException | ResourcePersistenceException e) {
            e.printStackTrace();
        }*/ catch (Exception e) {
            e.printStackTrace(); // include this line while developing/debugging the app!
            // should be logged to a file in a production environment
        }



    }
}
