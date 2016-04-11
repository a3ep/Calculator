package net.bondar.interfaces;

/**
 *
 */
public interface IHistoryHolder {
    /**
     *
     * @param expression
     */
    void addToHistory(String expression);

    /**
     *
     */
    void showHistory();

    /**
     *
     */
    void showUniqueHistory();
}
