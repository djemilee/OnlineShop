package tests;

import org.junit.Test;
import shop.Profile;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import static junit.framework.TestCase.assertFalse;
import static junit.framework.TestCase.assertTrue;
import static junit.framework.TestCase.*;

public class TestProfile {

    @Test
    public void testValidatePhoneNumber() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        Method method = Profile.class.getDeclaredMethod("checkForPhoneNumber", String.class);
        method.setAccessible(true);

        boolean isValid = (boolean) method.invoke(Profile.class, "0895674323");
        assertTrue(isValid);
    }

    @Test
    public void testNotValidPhoneNumber() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        Method method = Profile.class.getDeclaredMethod("checkForPhoneNumber", String.class);
        method.setAccessible(true);

        boolean isValid = (boolean) method.invoke(Profile.class, "089567");
        assertFalse(isValid);
    }

    @Test
    public void testCountOfCities(){
        int count = Profile.getCountOfCities();
        assertNotSame(0, count);
    }
}