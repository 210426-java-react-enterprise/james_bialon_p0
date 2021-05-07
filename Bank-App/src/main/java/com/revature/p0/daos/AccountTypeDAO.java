package com.revature.p0.daos;

import com.revature.p0.models.account.AccountType;
import com.revature.p0.util.factory.ConnectionFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by IntelliJ IDEA.
 * User: Jbialon
 * Date: 5/7/2021
 * Time: 4:16 PM
 * Description: {Insert Description}
 */
public class AccountTypeDAO {

    public AccountType[] getAllAcctTypes() {
        AccountType[] acctTypes = null;
        AccountType acctType = null;

        try(Connection conn = ConnectionFactory.getInstance().getConnection()) {

            String sqlGetAcctTypes = "select *" +
                    "from account_type";
            PreparedStatement pstmt = conn.prepareStatement(sqlGetAcctTypes);

            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                acctType = new AccountType();
                acctType.setId(rs.getInt("id"));
                acctType.setType(rs.getString("acct_type"));
                acctType.setInterest(rs.getFloat("id"));
            }


        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return acctTypes;
    }

}
