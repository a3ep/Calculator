package net.bondar.interfaces;

/**
 * Interface for class that provides building the parts of expression.
 */
public interface INumberBuilder {

    /**
     * Converts the received string with a number value to the number.
     *
     * @param stringNumber the string to be converted to the number
     * @return number value
     */
    int buildNumber(String stringNumber);

    /**
     * Builds a string with a number value based on the specified substring.
     *
     * @param str        specified substring
     * @param expression string with expression
     * @return string with a number value
     */
    String buildStringNumber(String str, String expression);
}
