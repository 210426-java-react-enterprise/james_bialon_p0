package com.revature.p0.services;

import com.revature.p0.daos.AccountTransactionDAO;
import com.revature.p0.models.account.AccountTransaction;
import com.revature.p0.util.singleton.CurrentAccount;

/**
 * Created by IntelliJ IDEA.
 * User: Jbialon
 * Date: 5/11/2021
 * Time: 3:35 PM
 * Description: {Insert Description}
 */
public class AccountTransactionService {

    private AccountTransactionDAO transactionDAO;

    public AccountTransactionService(AccountTransactionDAO transactionDAO) {
        this.transactionDAO = transactionDAO;
    }

    public boolean sendBalanceAsTransaction(String transactionAmt, String description) {

        AccountTransaction newTransaction = new AccountTransaction();

        newTransaction.setAcctID(CurrentAccount.getInstance().getCurrentAccount().getaID());
        newTransaction.setTransactionAmt(Double.parseDouble(transactionAmt));
        newTransaction.setDescription(description);

        transactionDAO.saveTransaction(newTransaction);

        return true;
    }
}
