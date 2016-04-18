package net.bondar.interfaces;

/**
 * Interface for class that provides building the parts of expression.
 */
public interface INumberBuilder {

    /**
     * Builds the number on the basis of expression string and expression part indexes.
     *
     * @param startIndex start expression part index
     * @param endIndex   end expression part index
     * @param expression expression string
     * @return number value
     */
    int buildNumber(int startIndex, int endIndex, String expression);
}
