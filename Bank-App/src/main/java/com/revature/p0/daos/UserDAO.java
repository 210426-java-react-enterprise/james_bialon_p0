package com.revature.p0.daos;

import com.revature.p0.models.account.BankUser;
import com.revature.p0.util.factory.ConnectionFactory;

import java.io.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by IntelliJ IDEA.
 * User: Jbialon
 * Date: 5/7/2021
 * Time: 8:28 AM
 * Description: {Insert Description}
 */
public class UserDAO {

    public void save(BankUser newUser) {

        try(Connection conn = ConnectionFactory.getInstance().getConnection()) {

            String sqlInsertUser = "insert into user_table" +
                                    "(email , first_name , last_name) values (?,?,?)";
            PreparedStatement pstmt = conn.prepareStatement(sqlInsertUser, new String[] { "user_id" });

            pstmt.setString(1,newUser.getEmail());
            pstmt.setString(2,newUser.getfName());
            pstmt.setString(3,newUser.getlName());
            int rowsInserted = pstmt.executeUpdate();

            if (rowsInserted != 0) {
                ResultSet rs = pstmt.getGeneratedKeys();
                while (rs.next()) {
                    newUser.setuID(rs.getInt("user_id"));
                }
            }

            String sqlInsertLogin = "insert into user_table" +
                    "(user_id , username , password) values (?,?,crypt(?, gen_salt('bf'))";
            pstmt = conn.prepareStatement(sqlInsertLogin);

            pstmt.setInt(1,newUser.getuID());
            pstmt.setString(2,newUser.getuName());
            pstmt.setString(3,newUser.getPassword());

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public boolean isUsernameAvailable(String username) {
        try (Connection conn = ConnectionFactory.getInstance().getConnection()) {

            String sql = "select * from user_login where username = ?";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, username);

            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                return false;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return true;

    }

    public boolean isEmailAvailable(String email) {
        try (Connection conn = ConnectionFactory.getInstance().getConnection()) {

            String sql = "select * from user_table where email = ?";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, email);

            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                return false;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return true;
    }

    public BankUser findUserByUsernameAndPassword(String username, String password) {

        BankUser user = null;

        try (Connection conn = ConnectionFactory.getInstance().getConnection()) {

            String sql = "select * from bank_app.user_login where" +
                    "username = ? and password = crypt(?, password)";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, username);
            pstmt.setString(2, password);

            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                user = new BankUser();
                user.setuID(rs.getInt("id"));
                user.setuName(rs.getString("username"));
                user.setPassword(rs.getString("password"));
            }

            sql = "select * from bank_app.user_table where id = ?";
            pstmt.setInt(1, user.getuID());

            rs = pstmt.executeQuery();
            while (rs.next()) {
                user = new BankUser();
                user.setfName(rs.getString("first_name"));
                user.setlName(rs.getString("last_name"));
                user.setEmail(rs.getString("email"));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return user;

    }

}
