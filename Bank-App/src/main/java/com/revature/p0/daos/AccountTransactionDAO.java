package com.revature.p0.daos;

import com.revature.p0.models.account.Account;
import com.revature.p0.models.account.AccountTransaction;
import com.revature.p0.models.account.AccountType;
import com.revature.p0.models.account.BankUser;
import com.revature.p0.util.factory.ConnectionFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by IntelliJ IDEA.
 * User: Jbialon
 * Date: 5/7/2021
 * Time: 4:15 PM
 * Description: {Insert Description}
 */
public class AccountTransactionDAO {
    public AccountTransaction[] getAllAcctTransactions(BankUser user) {
        AccountTransaction[] acctTransactions = null;
        AccountTransaction acctTransaction = null;
        int numOfTransactions = 0;
        int rsCounter = 0;

        try(Connection conn = ConnectionFactory.getInstance().getConnection()) {

            String sqlCountAcctTransactions = "select count(*)" +
                    "from bank_app.account_Transaction where account_id = ?";
            PreparedStatement pstmt = conn.prepareStatement(sqlCountAcctTransactions);

            pstmt.setInt(1, user.getuID());

            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                numOfTransactions = rs.getInt("count");
            }

            acctTransactions = new AccountTransaction[numOfTransactions];

            String sqlGetAcctTransactions = "select *" +
                    "from bank_app.account_Transaction where account_id = ?" +
                    "order by desc;";
            pstmt = conn.prepareStatement(sqlGetAcctTransactions);

            pstmt.setInt(1, user.getuID());

            rs = pstmt.executeQuery();

            while (rs.next()) {
                acctTransaction = new AccountTransaction();

                acctTransaction.setTransactionID(rs.getInt("id"));
                acctTransaction.setAcctID(rs.getInt("account_id"));
                acctTransaction.setTransactionAmt(rs.getDouble("transaction_amt"));
                acctTransaction.setDescription(rs.getString("description"));

                acctTransactions[rsCounter] = acctTransaction;

                rsCounter++;
            }


        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return acctTransactions;
    }

    public void saveTransaction(AccountTransaction transaction) {

        try(Connection conn = ConnectionFactory.getInstance().getConnection()) {

            String sqlInsertTransaction = "insert into bank_app.account_transaction" +
                    "(account_id , transaction_amt, description) values (?,?,?)";
            PreparedStatement pstmt = conn.prepareStatement(sqlInsertTransaction);

            pstmt.setInt(1, transaction.getAcctID());
            pstmt.setDouble(2, transaction.getTransactionAmt());
            pstmt.setString(3, transaction.getDescription());
            pstmt.executeUpdate();


        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}
