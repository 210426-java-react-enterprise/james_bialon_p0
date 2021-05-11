package com.revature.p0.daos;

import com.revature.p0.models.account.Account;
import com.revature.p0.models.account.AccountBalance;
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
 * Time: 4:01 PM
 * Description: {Insert Description}
 */
public class AccountBalanceDAO {
    public void saveNewBalance(Account acct) {

        try(Connection conn = ConnectionFactory.getInstance().getConnection()) {

            String sqlInsertAcctBal = "insert into bank_app.account_balance" +
                    "(account_id , balance) values (?,0)";
            PreparedStatement pstmt = conn.prepareStatement(sqlInsertAcctBal);

            pstmt.setInt(1,acct.getaID());
            pstmt.executeUpdate();


        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public boolean saveBalance(Account acct, double currBalance) {

        try(Connection conn = ConnectionFactory.getInstance().getConnection()) {

            String sqlInsertAcctBal = "update account_balance " +
                    "set balance = ? where account_id = ?";
            PreparedStatement pstmt = conn.prepareStatement(sqlInsertAcctBal);

            pstmt.setDouble(1, currBalance);
            pstmt.setInt(2, acct.getaID());
            pstmt.executeUpdate();


        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }


        return true;

    }

    public double getBalance(Account acct) {

        double balance = 0;

        try(Connection conn = ConnectionFactory.getInstance().getConnection()) {

            String sqlInsertAcctBal = "select balance " +
                    "from account_balance where account_id = ?";
            PreparedStatement pstmt = conn.prepareStatement(sqlInsertAcctBal);

            pstmt.setInt(1, acct.getaID());

            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                balance = rs.getDouble("balance");
            }


        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return balance;

    }
}
