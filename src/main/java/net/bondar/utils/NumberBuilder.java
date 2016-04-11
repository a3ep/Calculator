package net.bondar.utils;

import net.bondar.exceptions.CalculatorApplicationException;
import net.bondar.interfaces.INumberBuilder;
import org.apache.log4j.Logger;

/**
 * Provides building the parts of expression.
 */
public class NumberBuilder implements INumberBuilder {

    /**
     * Logger.
     */
    private final static Logger log = Logger.getLogger(NumberBuilder.class);

    /**
     * Converts the received string with a number value to the number.
     *
     * @param stringNumber the string to be converted to the number
     * @return number value
     * @throws CalculatorApplicationException when the string with a number value has incorrect format
     * @see {@link INumberBuilder}
     */
    public int buildNumber(String stringNumber) {
        log.info("---------- Builds number...");
        if (stringNumber.length() != 0) {
            try {
                return Integer.parseInt(stringNumber);
            } catch (NumberFormatException e) {
                throw new CalculatorApplicationException(e.getMessage());
            }
        }
        throw new CalculatorApplicationException("Error while building number -->" + stringNumber);
    }

    /**
     * Builds a string with a number value based on the specified substring.
     *
     * @param str        specified substring
     * @param expression string with expression
     * @return string with a number value or empty string, if the specified substring is empty
     */
    public String buildStringNumber(String str, String expression) {
        log.info("---------- Builds part of expression...");
        if (str.length() == 0) {
            return "";
        }
        if (expression.indexOf(str) == 0) {
            StringBuilder builder = new StringBuilder(str);
            str = builder.reverse().toString();
            str = findEndOfNumber(str);
            return builder.replace(0, builder.length(), str).reverse().toString();
        }
        return findEndOfNumber(str);
    }

    /**
     * Finds the end of the number in the specified substring.
     *
     * @param str the specified substring
     * @return string with the number value
     */
    private String findEndOfNumber(String str) {
        if (str.length() > 1) {
            char[] arr = str.toCharArray();
            for (int i = 0; i < arr.length; i++) {
                if (arr[i] == '*' || arr[i] == '/' || arr[i] == '+' || arr[i] == '-') {
                    return str.substring(0, i);
                }
            }
        }
        return str;
    }
}
