package com.revature.p0.services;

import com.revature.p0.daos.AccountDAO;
import com.revature.p0.exceptions.InvalidRequestException;
import com.revature.p0.models.account.Account;

/**
 * Created by IntelliJ IDEA.
 * User: Jbialon
 * Date: 5/11/2021
 * Time: 8:42 AM
 * Description: {Insert Description}
 */
public class AccountOpeningService {

    private AccountDAO acctDAO;

    public AccountOpeningService(AccountDAO acctDAO) {
        this.acctDAO = acctDAO;
    }

    public Account createAccount(Account acct) throws InvalidRequestException {

        if (!isAccountValid(acct)) {
            throw new InvalidRequestException("Invalid Account Data Entered");
        }

        return acctDAO.saveNewAcct(acct);

    }

    public boolean isAccountValid(Account acct) {

        if (acct == null) return false;
        if (acct.getaName() == null || acct.getaName().trim().isEmpty() || acct.getaName().length() > 50) return false;

        return true;

    }

}
