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
     *
     * @author Mihail Menev, menev@hm.edu
     */
    @Test
    public void testStripMethodNull() {
        assertNotNull("The method should not return null", StringUtils.strip("blaah", "blaah"));
    }

    /**
     * Tests if the correct characters are removed from the string.
     *
     * @author Mihail Menev, menev@hm.edu
     */
    @Test
    public void testStripMethod() {
        assertEquals("The string should be the same as the input", "foo", StringUtils.strip("foo", ""));
        assertEquals("The string should be the same as the input", "foo", StringUtils.strip("foo", "dsd"));
        assertEquals("The string should be foob", "foob", StringUtils.strip("foobar", "ra"));
        assertEquals("The string should be '-' ", "-", StringUtils.strip("Hallo-ollaH", "Halo"));
        assertEquals("The string should be empty", "", StringUtils.strip("fooobbbbaaarrrrr", "fobar"));
    }

    /**
     * Test if the right exception is thrown by the strip method when the first parameter is null.
     *
     * @author Mihail Menev, menev@hm.edu
     */
    @Test(expected = IllegalArgumentException.class)
    public void testStripMethodIllegalArgument() {
        StringUtils.strip(null, "foo");
    }

    /**
     * Test if the right exception is thrown by the strip method when the second parameter is null.
     *
     * @author Mihail Menev, menev@hm.edu
     */
    @Test(expected = IllegalArgumentException.class)
    public void testStripMethodIllegalArgument2() {
        StringUtils.strip("foo", null);
    }

    /**
     * Test if strong passwords are recognized as such from the isSecure method.
     *
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
     *
     * @author Mihail Menev, menev@hm.edu
     */
    @Test
    public void testIsSecureFalse() {
        assertFalse("a short password", StringUtils.isSecure("abcd"));
        assertFalse("An empty password", StringUtils.isSecure(""));
        assertFalse("A password with only lower case", StringUtils.isSecure("abcdefghijklmnopqrstuvwxyz"));
        assertFalse("Only UPEER CASE password", StringUtils.isSecure("ABCDEFGHIJKLMNOPQRSTUVWXYZ"));
        assertFalse("Password with less as 10 unique characters", StringUtils.isSecure("abcdeabcdeABcdeabcd3!"));
        assertFalse("Everything correct but the special char", StringUtils.isSecure("pqrabcd3efghL569KMinS"));
        assertFalse("Everything correct but the number", StringUtils.isSecure("pqrabcd§efghL%&/KMinS"));
        assertFalse("Null as a password", StringUtils.isSecure(null));
    }

    /**
     * Test the isValidIsbn13 method with valid ISBN Numbers.
     *
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
     *
     * @author Mihail Menev, menev@hm.edu
     */
    @Test
    public void testIsValidIsbn13False() {
        assertFalse("An invalid ISBN number", StringUtils.isValidIsbn13("ISBN 978-0-306-40615-2"));
        assertFalse("A null as a ISBN number", StringUtils.isValidIsbn13(null));
        assertFalse("Too short number", StringUtils.isValidIsbn13("ISBN --78-65-4-6-3-   54"));
        assertFalse("Too long number", StringUtils.isValidIsbn13("ISBN --78-65-4-\n\r-3-   543242344432423"));
        assertFalse("An empty string as a ISBN number", StringUtils.isValidIsbn13(""));
        assertFalse("Invalid ISBN only with characters", StringUtils.isValidIsbn13("asfjdsfgskdafg"));
    }

    // //////////////////////////////////////////////////////////////////////////////////

    /**
     * Tests with INVALID ISBN-Strings.
     *
     * @author Johann Vierthaler, johann.vierthaler@web.de Tests:. - one number too much. - one number missing - special
     *         char - non-number-chars - empty string - null-string
     */
    @Test
    public void testInvalidISBN10() {
        // one number to much
        String isbn = "02015308212";
        assertEquals("this is an invalid isbn (too many numbers): ", false, StringUtils.isValidIsbn10(isbn));

        // one number is missing
        isbn = "020153082";
        assertEquals("this is an invalid isbn (too few numbers) : ", false, StringUtils.isValidIsbn10(isbn));

        // checksum error (0 instead of 1)
        isbn = "0201530820";
        assertEquals("checksum error ", false, StringUtils.isValidIsbn10(isbn));

        // use of chars besides numbers, minus and whitespace
        isbn = "0201+530821";
        assertEquals("only numbers, '-' and ' ' are allowed: ", false, StringUtils.isValidIsbn10(isbn));

        isbn = "abcd-efhi jdfdfdfklmnop";
        assertEquals("only numbers, '-' and ' ' are allowed: ", false, StringUtils.isValidIsbn10(isbn));

        // empty string
        assertEquals("this is an invalid isbn: ", false, StringUtils.isValidIsbn10(""));

        // null
        assertEquals("this is an invalid isbn: ", false, StringUtils.isValidIsbn10(null));
    }

    /**
     * This checks the correctness of isValidISBN().
     *
     * @author Johann Vierthaler, johann.vierthaler@web.de Tests: - a correct ISBN - a correct ISBN with minus-chars - a
     *         correct ISBN with random format and minus-chars
     */
    @Test
    public void testCorrecISBNValid() {

        // no special formats or minus-chars
        String isbn = "0201530821";
        assertEquals("this is a valid isbn: ", true, StringUtils.isValidIsbn10(isbn));

        // ISBN formatted with minus-chars
        isbn = "0-201-53-08--21";
        assertEquals("this is a valid isbn: ", true, StringUtils.isValidIsbn10(isbn));

        // random format
        isbn = "0          20      15-------308----   -  21   -----  -";
        assertEquals("this should be a valid isbn", true, StringUtils.isValidIsbn10(isbn));
    }

    /**
     * Tests if the String is blank.
     *
     * @author Johann Vierthaler, johann.vierthaler@web.de Tests: - a non-blank string - a blank string - a blank string
     *         with whitespaces - a tabulator - a null-string
     */
    @Test
    public void testIsBlank() {
        assertEquals("Should return true ", true, StringUtils.isBlank(""));
        assertEquals("Should return true ", true, StringUtils.isBlank("       "));
        assertEquals("String can be null: ", true, StringUtils.isBlank("\t"));
        assertEquals("String can be null: ", true, StringUtils.isBlank(null));
    }

    /**
     * Tests if string is not blank.
     *
     * @author Johann Vierthaler, johann.vierthaler@web.de - one char in the string. - many whitespaces and then a char
     */
    @Test
    public void testIsNotBlank() {
        assertEquals("Should return false: ", false, StringUtils.isBlank("a"));
        assertEquals("Should return false: ", false, StringUtils.isBlank("           a"));
    }

    /**
     * Tests if join() works correct.
     *
     * @author Johann Vierthaler, johann.vierthaler@web.de
     * Tests:
     * - only one string
     * - a series of strings
     * - one string is null
     * - beginning string has length of 0
     * - beginning string is null
     * - "", null, ""
     * - null, null, ""
     */
    @Test
    public void testJoin() {
        assertEquals("should be 'A': ", "A", StringUtils.join(new String[]{"A"}));
        assertEquals("should be 'a,b,c': ", "a,b,c", StringUtils.join(new String[]{"a", "b", "c"}));
        assertEquals("should be 'a,(null),c': ", "a,(null),c", StringUtils.join(new String[]{"a", null, "c"}));
        assertEquals("should be '(null),(null),c': ", "(null),(null),c", StringUtils.join(new String[]{"", null, "c"}));
        assertEquals("should be '(null),b,c': ", "(null),b,c", StringUtils.join(new String[]{null, "b", "c"}));
        assertEquals("should be '(null),(null),(null)': ", "(null),(null),(null)", StringUtils.join(new String[]{"", null, ""}));
        assertEquals("should be '(null),(null),(null)': ", "(null),(null),(null)", StringUtils.join(new String[]{null, null, ""}));
    }

    /**
     * Tests when the array is null. A null-string causes a NullPointerException.
     *
     * @author Johann Vierthaler, johann.vierthaler@web.de
     */
    @Test(expected = IllegalArgumentException.class)
    public void testJoinNull() {
        StringUtils.join((String[])null);
    }

    /**
     * Test when the array is empty. An empty Array causes a NullPointerExcpetion
     *
     * @author Johann Vierthaler, johann.vierthaler@web.de
     */
    @Test(expected = IllegalArgumentException.class)
    public void testJoinEmptyArray() {
        StringUtils.join(new String[]{});
    }
}
