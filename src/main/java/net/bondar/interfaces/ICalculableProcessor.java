package net.bondar.interfaces;

/**
 * Interface for class that provides expression processing.
 */
public interface ICalculableProcessor {

    /**
     * Processes an expression.
     *
     * @param expression string with expression to be processed
     * @return value of calculation
     */
    int process(String expression);
}
