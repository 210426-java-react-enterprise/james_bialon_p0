package com.revature.p0.services;

import com.revature.p0.daos.AccountBalanceDAO;
import com.revature.p0.daos.AccountTransactionDAO;
import com.revature.p0.exceptions.InvalidRequestException;
import com.revature.p0.models.account.AccountTransaction;
import com.revature.p0.util.singleton.CurrentAccount;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by IntelliJ IDEA.
 * User: Jbialon
 * Date: 5/11/2021
 * Time: 3:02 PM
 * Description: {Insert Description}
 */
public class WithdrawService {

    AccountBalanceDAO balanceDAO;
    AccountTransactionService xActionService = new AccountTransactionService(new AccountTransactionDAO());

    public WithdrawService(AccountBalanceDAO balanceDAO) {
        this.balanceDAO = balanceDAO;
    }

    public boolean createBalance(String usrInput) throws InvalidRequestException {

        if (!isWithdrawValid(usrInput)) {
            throw new InvalidRequestException("Invalid Withdraw Amount Entered");
        }

        double newBalance = balanceDAO.getBalance(CurrentAccount.getInstance().getCurrentAccount()) - Double.parseDouble(usrInput);

        xActionService.sendBalanceAsTransaction(usrInput);
        return balanceDAO.saveBalance(CurrentAccount.getInstance().getCurrentAccount(), newBalance);

    }

    public boolean isWithdrawValid(String usrInput) {

        String regex = "[0-9]*(\\.[0-9]{0,2})?";
        Pattern p = Pattern.compile(regex);
        Matcher m = p.matcher(usrInput);

        if (usrInput == null || usrInput.trim().isEmpty() || usrInput.contains("-") || usrInput.contains(" ") || !m.matches()) return false;

        double newBalance = balanceDAO.getBalance(CurrentAccount.getInstance().getCurrentAccount()) - Double.parseDouble(usrInput);
        if (newBalance < 0) return false;

        return true;
    }



}
