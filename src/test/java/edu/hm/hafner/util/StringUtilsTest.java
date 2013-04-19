package edu.hm.hafner.util;

import static org.junit.Assert.*;

import org.junit.Test;

/**
 * Testet die Klasse {@link StringUtils}.
 *
 * @author Ulli Hafner
 */
public class StringUtilsTest {
    /**
     * Prüft, ob die beiden gültigen String Eingaben korrekt verarbeitet werden.
     */
    @Test
    public void testeGueltigeLeereStrings() {
        assertTrue("Der String sollte leer sein", StringUtils.isEmpty(null));
        assertTrue("Der String sollte leer sein", StringUtils.isEmpty(""));
    }

    /**
     * Prüft, ob ungültigen String Eingaben korrekt verarbeitet werden.
     */
    @Test
    public void testeLeereStringsMitZeichen() {
        assertFalse("Der String sollte nicht als empty erkannt werden", StringUtils.isEmpty(" "));
        assertFalse("Der String sollte nicht als empty erkannt werden", StringUtils.isEmpty("\t"));
        assertFalse("Der String sollte nicht als empty erkannt werden", StringUtils.isEmpty("Hallo"));
    }

    /**
     * Tests if the correct characters are removed from the string.
     */
    @Test
    public void testStripMethodNull() {
        assertNotNull("The method should not return null", StringUtils.strip("blaah", "blaah"));
    }

    /**
     *
     * FIXME: Document method testStripMethod.
     */
    @Test
    public void testStripMethod() {
        assertEquals("The string should be the same as the input", "foo",  StringUtils.strip("foo", ""));
        assertEquals("The string should be foo", "foob", StringUtils.strip("foobar", "ra"));
        assertEquals("The string should be '-' ", "-" , StringUtils.strip("Hallo-ollaH", "Halo"));
    }

    /**
     * Test if the right exception is thrown by the strip method if a null parameter is passed.
     * @author Mihail Menev, menev@hm.edu
     */
    @Test(expected = IllegalArgumentException.class)
    public void testStripMethodIllegalArgument() {
        StringUtils.strip("foo", "bar");
        StringUtils.strip(null, "foo");
        StringUtils.strip("foo", null);
        StringUtils.strip(null, null);
    }

    /**
     * Test if strong passwords are recognized as such from the isSecure method.
     * @author Mihail Menev, menev@hm.edu
     */
    @Test
    public void testIsSecure() {
        assertTrue("This should be a strong password: ", StringUtils.isSecure("seCuR3Pa$SwoRd!!!Ye4H"));
        assertTrue("This is a test string: ", StringUtils.isSecure("\nA9k§9kl8hgGztrVjjkLpdsss"));
        assertTrue("YATS: ", StringUtils.isSecure("aqweZtz/iopü sdfgjhkl9mcksd"));
    }

    /**
     * Test if unsecure passwords are recognized as such from the method isSecure method.
     * @author Mihail Menev, menev@hm.edu
     */
    @Test
    public void testIsSecureFalse(){
        assertFalse("a short password", StringUtils.isSecure("abcd"));
        assertFalse("An empty password", StringUtils.isSecure(""));
        assertFalse("A password with only lower case", StringUtils.isSecure("abcdefghijklmnopqrstuvwxyz"));
        assertFalse("Only UPEER CASE password", StringUtils.isSecure("ABCDEFGHIJKLMNOPQRSTUVWXYZ"));
        assertFalse("Uppercase and lowercase", StringUtils.isSecure("abcdeabcdeABcdeabcd3!"));
        assertFalse("Everything correct but the special char", StringUtils.isSecure("pqrabcd3efghL569KMinS"));
        assertFalse("Everything correct but the number", StringUtils.isSecure("pqrabcd§efghL%&/KMinS"));
        assertFalse("Null as a password", StringUtils.isSecure(null));
    }


}

