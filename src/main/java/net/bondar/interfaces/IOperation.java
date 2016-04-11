package net.bondar.interfaces;

/**
 * Interface for a mathematical operations.
 */
public interface IOperation {

    /**
     * Gets a mathematical operator.
     *
     * @return value of the mathematical operator
     */
    String getOperator();

    /**
     * Gets a mathematical operation priority.
     *
     * @return a mathematical operation priority
     */
    int getPriority();

    /**
     * Calculates the expression depending on the mathematical operation.
     *
     * @param x first number
     * @param y second number
     * @return the result of calculation
     */
    int calculate(int x, int y);
}
