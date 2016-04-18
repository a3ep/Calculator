package net.bondar.interfaces;

import java.util.List;
import java.util.Set;

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
     * Gets the history of calculations.
     *
     * @return list with history of calculations
     */
    List<String> getHistory();

    /**
     * Gets the unique history of calculations.
     *
     * @return set with unique history of calculations
     */
    Set<String> getUniqueHistory();
}
