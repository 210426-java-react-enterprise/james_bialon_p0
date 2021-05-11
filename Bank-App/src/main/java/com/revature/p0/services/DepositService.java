package com.revature.p0.services;

import com.revature.p0.daos.AccountBalanceDAO;
import com.revature.p0.daos.AccountTransactionDAO;
import com.revature.p0.exceptions.InvalidRequestException;
import com.revature.p0.util.singleton.CurrentAccount;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by IntelliJ IDEA.
 * User: Jbialon
 * Date: 5/11/2021
 * Time: 1:39 PM
 * Description: {Insert Description}
 */
public class DepositService {

    AccountBalanceDAO balanceDAO;
    AccountTransactionService xActionService = new AccountTransactionService(new AccountTransactionDAO());

    public DepositService(AccountBalanceDAO balanceDAO) {
        this.balanceDAO = balanceDAO;
    }

    public boolean createBalance(String usrInput) throws InvalidRequestException {

        if (!isDepositValid(usrInput)) {
            throw new InvalidRequestException("Invalid Deposit Amount Entered");
        }

        double newBalance = balanceDAO.getBalance(CurrentAccount.getInstance().getCurrentAccount()) + Double.parseDouble(usrInput);


        xActionService.sendBalanceAsTransaction(usrInput, "Deposit");
        return balanceDAO.saveBalance(CurrentAccount.getInstance().getCurrentAccount(), newBalance);

    }

    public boolean isDepositValid(String usrInput) {

        String regex = "[0-9]*(\\.[0-9]{0,2})?";
        Pattern p = Pattern.compile(regex);
        Matcher m = p.matcher(usrInput);

        if (usrInput == null || usrInput.trim().isEmpty() || usrInput.contains("-") || usrInput.contains(" ") || !m.matches()) return false;

        return true;
    }
}
