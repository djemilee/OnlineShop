package tests;

import org.junit.Test;
import shop.Profile;
import shop.User;;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import static junit.framework.TestCase.*;


public class TestsUser {

    @Test
    public void testIsValidPasswordUser() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        Method method = User.class.getDeclaredMethod("isValidPassword", String.class);
        method.setAccessible(true);

        boolean isValid = (boolean) method.invoke(User.class, "123456");
        assertTrue(isValid);
    }

    @Test
    public void testIsNotValidPasswordUser() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        Method method = User.class.getDeclaredMethod("isValidPassword", String.class);
        method.setAccessible(true);

        boolean isValid = (boolean) method.invoke(User.class, "123");
        assertFalse(isValid);
    }

    @Test
    public void testIsValidEmaildUser() throws NoSuchMethodException, IllegalAccessException, InvocationTargetException {
        Method method = User.class.getDeclaredMethod("isValidEmail", String.class);
        method.setAccessible(true);

        boolean isValid = (boolean) method.invoke(User.class, "dj@abv.bg");
        assertTrue(isValid);
    }

    @Test
    public void testIsNotValidEmailUser() throws NoSuchMethodException, IllegalAccessException, InvocationTargetException {
        Method method = User.class.getDeclaredMethod("isValidEmail", String.class);
        method.setAccessible(true);

        boolean isValid = (boolean) method.invoke(User.class, "@abv.bg");
        assertFalse(isValid);
    }
}
