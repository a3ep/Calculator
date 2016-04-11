package net.bondar.interfaces;

/**
 * Interface to check whether an expression is negative and the appropriate processing of expression.
 */
public interface INegativeChecker {
    /**
     * Checks whether an expression is negative.
     *
     * @param expression string with expression to be checked
     * @return if an expression is negative returns true, else - false
     */
    boolean isNegative(String expression);

    /**
     * Turns the negative expression into a positive.
     *
     * @param expression string with expression to be turned
     * @return string with positive expression
     */
    String processNegative(String expression);
}
