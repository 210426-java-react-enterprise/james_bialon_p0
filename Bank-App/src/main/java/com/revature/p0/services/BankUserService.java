package com.revature.p0.services;

import com.revature.p0.daos.UserDAO;
import com.revature.p0.exceptions.InvalidRequestException;
import com.revature.p0.exceptions.ResourcePersistenceException;
import com.revature.p0.models.account.BankUser;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by IntelliJ IDEA.
 * User: Jbialon
 * Date: 5/9/2021
 * Time: 4:04 PM
 * Description: {Insert Description}
 */
public class BankUserService {

    private UserDAO userDao;

    public BankUserService(UserDAO userDao) {
        this.userDao = userDao;
    }

    public BankUser register(BankUser newUser) throws InvalidRequestException, ResourcePersistenceException {

        if (!isUserValid(newUser)) {
            throw new InvalidRequestException("Invalid new user data provided!");
        }

        if (!userDao.isUsernameAvailable(newUser.getuName())) {
            throw new ResourcePersistenceException("The provided username is already taken!");
        }

        if (!userDao.isEmailAvailable(newUser.getEmail())) {
            throw new ResourcePersistenceException("The provided email is already taken!");
        }

        return userDao.save(newUser);

    }

    public boolean isUserValid(BankUser user) {
        if (user == null) return false;
        if (user.getuName() == null || user.getuName().trim().isEmpty() || user.getuName().length() > 15) return false;
        if (user.getPassword() == null || user.getPassword().trim().isEmpty() || user.getPassword().length() > 72) return false;
        if (user.getEmail() == null || user.getEmail().trim().isEmpty() || user.getEmail().length() > 50) return false;
        if (user.getfName() == null || user.getfName().trim().isEmpty() || user.getfName().length() > 50) return false;
        if (user.getfName() == null || user.getfName().trim().isEmpty() || user.getfName().length() > 50) return false;

        /*
            Regular expression evaluation email input...
            Example #5 on: https://howtodoinjava.com/java/regex/java-regex-validate-email-address/
         */

        String regex = "^[\\w!#$%&'*+/=?`{|}~^-]+(?:\\.[\\w!#$%&'*+/=?`{|}~^-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,6}$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(user.getEmail());

        if (!matcher.matches()) { return false; }

        return true;
    }


}
