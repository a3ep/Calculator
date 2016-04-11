package net.bondar.interfaces;

/**
 * Interface for wrapper class that provides expression processing.
 */
public interface ICalculableProcessorDecorator {

    /**
     * Processes an expression.
     *
     * @param expression string with expression to be processed
     * @return value of calculation
     */
    int process(String expression);
}
