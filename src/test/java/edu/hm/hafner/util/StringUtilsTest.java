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
     * Tests if the the method return a not null string.
     * @author Mihail Menev, menev@hm.edu
     */
    @Test
    public void testStripMethodNull() {
        assertNotNull("The method should not return null", StringUtils.strip("blaah", "blaah"));
    }

    /**
     * Tests if the correct characters are removed from the string.
     * @author Mihail Menev, menev@hm.edu
     */
    @Test
    public void testStripMethod() {
        assertEquals("The string should be the same as the input", "foo",  StringUtils.strip("foo", ""));
        assertEquals("The string should be the same as the input", "foo",  StringUtils.strip("foo", "dsd"));
        assertEquals("The string should be foob", "foob", StringUtils.strip("foobar", "ra"));
        assertEquals("The string should be '-' ", "-" , StringUtils.strip("Hallo-ollaH", "Halo"));
        assertEquals("The string should be empty", "", StringUtils.strip("fooobbbbaaarrrrr", "fobar"));
    }

    /**
     * Test if the right exception is thrown by the strip method when the first parameter is null.
     * @author Mihail Menev, menev@hm.edu
     */
    @Test(expected = IllegalArgumentException.class)
    public void testStripMethodIllegalArgument() {
        StringUtils.strip(null, "foo");
    }

    /**
    * Test if the right exception is thrown by the strip method when the second parameter is null.
    * @author Mihail Menev, menev@hm.edu
    */
   @Test(expected = IllegalArgumentException.class)
   public void testStripMethodIllegalArgument2() {
       StringUtils.strip("foo", null);
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
    public void testIsSecureFalse() {
        assertFalse("a short password", StringUtils.isSecure("abcd"));
        assertFalse("An empty password", StringUtils.isSecure(""));
        assertFalse("A password with only lower case", StringUtils.isSecure("abcdefghijklmnopqrstuvwxyz"));
        assertFalse("Only UPEER CASE password", StringUtils.isSecure("ABCDEFGHIJKLMNOPQRSTUVWXYZ"));
        assertFalse("Uppercase and lowercase", StringUtils.isSecure("abcdeabcdeABcdeabcd3!"));
        assertFalse("Everything correct but the special char", StringUtils.isSecure("pqrabcd3efghL569KMinS"));
        assertFalse("Everything correct but the number", StringUtils.isSecure("pqrabcd§efghL%&/KMinS"));
        assertFalse("Null as a password", StringUtils.isSecure(null));
    }

    /**
     * Test the isValidIsbn13 method with valid ISBN Numbers.
     * @author Mihail Menev, menev@hm.edu
     */
    @Test
    public void testIsValidIsbn13() {
        assertTrue("A valid ISBN 13 number", StringUtils.isValidIsbn13("ISBN 978-0-306-40615-7"));
        assertTrue("A valid ISBN 13 number", StringUtils.isValidIsbn13("9780670020485"));
        assertTrue("A valid ISBN 13 number", StringUtils.isValidIsbn13("     9-7-8-    0-3-8-5-521---6   80"));
    }

    /**
     * Test the isValidIsbn13 method with invalid ISBN Numbers.
     * @author Mihail Menev, menev@hm.edu
     */
    @Test
    public void testIsValidIsbn13False() {
        assertFalse("An invalid ISBN number", StringUtils.isValidIsbn13("ISBN 978-0-306-40615-2"));
        assertFalse("A null as a ISBN number", StringUtils.isValidIsbn13(null));
        assertFalse("Too short number", StringUtils.isValidIsbn13("ISBN --78-65-4-6-3-   54"));
        assertFalse("Too long number", StringUtils.isValidIsbn13("ISBN --78-65-4-\n\r-3-   543242344432423"));
        assertFalse("An empty string as a ISBN number", StringUtils.isValidIsbn13(""));
    }
}

