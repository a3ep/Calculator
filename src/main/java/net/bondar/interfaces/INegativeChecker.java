package net.bondar.interfaces;

/**
 *
 */
public interface INegativeChecker {
    /**
     *
     * @param expression
     * @return
     */
    boolean isNegative(String expression);

    /**
     *
     * @param expression
     * @return
     */
    String processNegative(String expression);
}
