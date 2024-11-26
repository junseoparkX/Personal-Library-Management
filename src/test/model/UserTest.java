package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class UserTest {

    private User user;

    @BeforeEach
    public void setUp() {
        user = new User("junseo", "1234");
    }

    @Test
    public void testConstructor() {
        assertEquals("junseo", user.getUsername());
        assertEquals("1234", user.getPassword());
        assertEquals(0, user.getLoginAttempts());
    }

    @Test
    public void testIsPasswordValid() {
        assertTrue(user.isPasswordValid());
        User userInvalidPassword = new User("user2", "thispasswordistoolong");
        assertFalse(userInvalidPassword.isPasswordValid());
    }

    @Test
    public void testIsUserNameValid() {
        assertTrue(user.isUserNameValid());
        User userInvalidUsername = new User("thisusernameistoolong", "pass");
        assertFalse(userInvalidUsername.isUserNameValid());
    }

    @Test
    public void testIncrementLoginAttempts() {
        user.incrementLoginAttempts();
        assertEquals(1, user.getLoginAttempts());
        user.incrementLoginAttempts();
        assertEquals(2, user.getLoginAttempts());
    }

    @Test
    public void testResetLoginAttempts() {
        user.incrementLoginAttempts();
        user.incrementLoginAttempts();
        user.resetLoginAttempts();
        assertEquals(0, user.getLoginAttempts());
    }

    @Test
    public void testIsLockedOut() {
        assertFalse(user.isLockedOut());
        user.incrementLoginAttempts();
        user.incrementLoginAttempts();
        user.incrementLoginAttempts();
        assertTrue(user.isLockedOut());
    }

    @Test
    public void testChangePassword_Success() {
        boolean changed = user.changePassword("1234", "2345");
        assertTrue(changed);
        assertEquals("2345", user.getPassword());
    }

    @Test
    public void testFailedToChangePassword_WrongOldPassword() {
        boolean changed = user.changePassword("12345", "2345");
        assertFalse(changed);
        assertEquals("1234", user.getPassword());
    }

    @Test
    public void testFailedToChangePassword_NewPasswordTooLong() {
        boolean changed = user.changePassword("1234", "LONGPASSWORDDDDDD");
        assertFalse(changed);
        assertEquals("1234", user.getPassword());
    }

}
