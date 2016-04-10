package net.bondar.interfaces;

/**
 *
 */
public interface INumberBuilder {
    /**
     *
     * @param stringNumber
     * @return
     */
    int buildNumber(String stringNumber);

    /**
     *
     * @param str
     * @param expression
     * @return
     */
    String buildStringNumber(String str, String expression);

}
