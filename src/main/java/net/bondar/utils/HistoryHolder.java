package net.bondar.utils;

import net.bondar.interfaces.IHistoryHolder;
import org.apache.log4j.Logger;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

/**
 * Provides storing the history of calculations.
 */
public class HistoryHolder implements IHistoryHolder {

    /**
     * Logger.
     */
    private final static Logger log = Logger.getLogger(HistoryHolder.class);

    /**
     * List with the history of calculations.
     */
    private List<String> history = new ArrayList<>();

    /**
     * Adds new expression to the history.
     *
     * @param expression string with expression and the result of calculation
     * @see {@link IHistoryHolder}
     */
    public void addToHistory(String expression) {
        history.add(expression);
    }

    /**
     * Gets the history of calculations.
     *
     * @return list with history of calculations
     * @see {@link IHistoryHolder}
     */
    public List<String> getHistory() {
        return history;
    }

    /**
     * Gets the unique history of calculations.
     *
     * @return set with unique history of calculations
     * @see {@link IHistoryHolder}
     */
    public Set<String> getUniqueHistory() {
        return new LinkedHashSet<>(history);
    }
}
