package com.revature.p0.util.datastructs.linkedlist;

import com.revature.p0.daos.UserDAO;
import com.revature.p0.exceptions.InvalidRequestException;
import com.revature.p0.services.BankUserService;
import com.revature.p0.daos.UserDAO;
import com.revature.p0.exceptions.InvalidRequestException;
import com.revature.p0.exceptions.ResourcePersistenceException;
import com.revature.p0.models.account.BankUser;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

/**
 * Created by IntelliJ IDEA.
 * User: Jbialon
 * Date: 5/11/2021
 * Time: 8:20 AM
 * Description: {Insert Description}
 */
public class UserServiceTest {

    private BankUserService sut;
    private UserDAO mockUserDao;

    @Before
    public void setUp() {
        mockUserDao = mock(UserDAO.class);
        sut = new BankUserService(mockUserDao);
    }

    @After
    public void tearDown() {
        sut = null;
        mockUserDao = null;

    }

    @Test
    public void test_registerWithValidUserAndAvailableUsernameAndPassword() {

        // Arrange
        when(mockUserDao.isUsernameAvailable(anyString())).thenReturn(true);
        when(mockUserDao.isEmailAvailable(anyString())).thenReturn(true);

        // Act
        sut.register(new BankUser(0, "fN", "lN", "uN", "not.taken@email.com", "pwd"));

        // Assert
        verify(mockUserDao, times(1)).isUsernameAvailable(anyString());
        verify(mockUserDao, times(1)).isEmailAvailable(anyString());
        verify(mockUserDao, times(1)).save(any());
    }

    @Test
    public void test_registerWithValidUserAndTakenUsername() {
        // Arrange
        when(mockUserDao.isUsernameAvailable(anyString())).thenReturn(false);

        // Act
        try {
            sut.register(new BankUser(0, "fN", "lN", "uN", "email@email.org", "pwd"));
        } catch (Exception e) {
            assertTrue(e instanceof ResourcePersistenceException);
        } finally {
            verify(mockUserDao, times(0)).isEmailAvailable(anyString());
            verify(mockUserDao, times(0)).save(any());
        }


    }

    @Test
    public void test_registerWithValidUserAndTakenEmail() {
        // Arrange
        when(mockUserDao.isUsernameAvailable(anyString())).thenReturn(true);
        when(mockUserDao.isEmailAvailable(anyString())).thenReturn(false);

        // Act
        try {
            sut.register(new BankUser(0, "fN", "lN", "uN", "taken@email.com", "pwd"));
        } catch (Exception e) {
            assertTrue(e instanceof ResourcePersistenceException);
        } finally {
            verify(mockUserDao, times(1)).isUsernameAvailable(anyString());
            verify(mockUserDao, times(1)).isEmailAvailable(anyString());
            verify(mockUserDao, times(0)).save(any());
        }


    }

    @Test(expected = InvalidRequestException.class)
    public void test_registerWithInvalidUser() {
        // Arrange
        BankUser invalidUser = new BankUser(-1, "", "", "", "", "");

        // Act
        sut.register(invalidUser);

        // Assert
        verify(mockUserDao.isUsernameAvailable(anyString()), times(1));
        verify(mockUserDao.isEmailAvailable(anyString()), times(0));


    }

}
