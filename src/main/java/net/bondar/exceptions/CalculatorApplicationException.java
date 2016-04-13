package net.bondar.exceptions;

/**
 * Custom application exception.
 */
public class CalculatorApplicationException extends RuntimeException {

    /**
     * Creates <code>CalculatorApplicationException</code> instance.
     *
     * @param message error message
     */
    public CalculatorApplicationException(String message) {
        super(message);
    }
}
