package com.revature.p0.daos;

import com.revature.p0.models.account.Account;
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
 * Time: 3:27 PM
 * Description: {Insert Description}
 */
public class AccountDAO {
    public void saveNewAcct(Account newAcct, BankUser bankUser, int acctType) {

        try(Connection conn = ConnectionFactory.getInstance().getConnection()) {

            String sqlInsertAcct = "insert into account" +
                    "(user_id , join_user_id, type_id) values (?,?,?)";
            PreparedStatement pstmt = conn.prepareStatement(sqlInsertAcct);

            pstmt.setInt(1,bankUser.getuID());
            pstmt.setString(2,"null");
            pstmt.setInt(3, acctType);
            pstmt.executeUpdate();


        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public Account getAcct(BankUser bankUser) {

        Account acct = null;

        try(Connection conn = ConnectionFactory.getInstance().getConnection()) {

            String sqlGetAcct = "select *" +
                    "from account where user_id = ?";
            PreparedStatement pstmt = conn.prepareStatement(sqlGetAcct);

            pstmt.setInt(1,bankUser.getuID());

            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                acct = new Account();
                acct.setaID(rs.getInt("id"));
                acct.setuID(rs.getInt("user_id"));
                acct.setjUID(rs.getInt("joint_user_id"));
                acct.settID(rs.getInt("type_id"));
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return acct;

    }
}
