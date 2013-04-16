package edu.hm.hafner.util;

/**
 * Several useful utility methods that work on {@link String} instances.
 *
 * @author Ulli Hafner
 */
public final class StringUtils {
    /**
     * Prüft, ob der übergebene String leer ist, d.h. kein Zeichen enthält.
     *
     * @param value
     *            der zu prüfende String
     * @return <code>true</code> falls der String kein Zeichen enthält oder <code>null</code> ist, <code>false</code>
     *         andernfalls.
     */
    public static boolean isEmpty(final String value) {
        return value == null || value.isEmpty();
    }

    /**
     * Creates a new instance of {@link StringUtils}.
     *
     * @author Ulli Hafner
     */
    private StringUtils() {
        // prevents instantiation
    }

    /**
     * Removes the specified characters from the string.
     * @param input the input string
     * @param toBeRemoved the string that should be removed
     * @return the stripped string
     */
    public static String strip(final String input, final String toBeRemoved) {
        String tmp = input;
        if (input == null || toBeRemoved == null) {
            throw new IllegalArgumentException("None of the parameters should be null");
        }

        if (toBeRemoved.length() == 0) {
            return input;
        }
        else {
            char[] toRemove = toBeRemoved.toCharArray();
            for (char character : toRemove) {
                tmp = tmp.replaceAll(String.valueOf(character), "");
            }
            return tmp;
        }
    }
}
