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
     *
     * FIXME: Document method testStripMethodIllegalArgument
     */
    @Test(expected=IllegalArgumentException.class)
    public void testStripMethodIllegalArgument() {
        StringUtils.strip(null, "foo");
        StringUtils.strip("foo", null);
        StringUtils.strip(null, null);
    }


}

