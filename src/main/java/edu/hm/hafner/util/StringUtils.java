package edu.hm.hafner.util;

import java.util.HashSet;
import java.util.Set;

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
     * @author Mihail Menev, menev@hm.edu
     */
    public static String strip(final String input, final String toBeRemoved) {
        String tmp = input;
        if (input == null || toBeRemoved == null) {
            throw new IllegalArgumentException("None of the parameters should be null");
        }

        else if (toBeRemoved.length() == 0) {
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

    /**
     * Tests if the password given as parameter is secure enough. A secure
     * password means- at least 20 charachter- from them:
     *          - at least 10 unique
     *          - at least 1 upper case character
     *          - at least 1 lower case character
     *          - at least 1 digit
     *          - at least 1 special character.
     * @param inputPass the password to be checked
     * @return true if the password is secure, and false if not
     * @author Mihail Menev, menev@hm.edu
     */
    public static boolean isSecure(final String inputPass) {
        // Null is valid but weak password
        if (inputPass == null) {
            return false;
        }
        // Funny way to eliminate the duplicates if any.
        // Later used to check how many unique characters have the password.
        final Set<Character> inputPassSet = new HashSet<Character>();
        char[] inputPassChars = inputPass.toCharArray();
        for (int i = 0; i < inputPassChars.length; i++) {
            inputPassSet.add(Character.valueOf(inputPassChars[i]));
        }
        // Regexes that match one upper case, one lower case, a number and a special character
        String upCase = "[\\s\\S]*[A-Z][\\s\\S]*";
        String lowCase = "[\\s\\S]*[a-z][\\s\\S]*";
        String digit = "[\\s\\S]*[0-9][\\s\\S]*";
        String specialChar = "[\\s\\S]*[^A-Za-z0-9][\\s\\S]*";

        if (inputPass.length() > 20 && inputPassSet.size() >= 10) {
            return inputPass.matches(upCase) && inputPass.matches(lowCase)
                   && inputPass.matches(digit) && inputPass.matches(specialChar);
        }
        else {
            return false;
        }
    }
}
