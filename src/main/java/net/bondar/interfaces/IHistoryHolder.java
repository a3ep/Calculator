package net.bondar.interfaces;

/**
 * Interface for storing the history of calculations.
 */
public interface IHistoryHolder {

    /**
     * Adds new expression to the history.
     *
     * @param expression string with expression and the result of calculation
     */
    void addToHistory(String expression);

    /**
     * Shows the history of calculations.
     */
    void showHistory();

    /**
     * Shows the unique history of calculations.
     */
    void showUniqueHistory();
}
