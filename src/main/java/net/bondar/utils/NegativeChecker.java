package net.bondar.utils;

import net.bondar.interfaces.INegativeChecker;
import org.apache.log4j.Logger;

/**
 * Provides scanning and processing of negative expressions.
 */
public class NegativeChecker implements INegativeChecker {

    /**
     * Logger.
     */
    private final static Logger log = Logger.getLogger(NegativeChecker.class);

    /**
     * Checks whether an expression is negative.
     *
     * @return if an expression is negative returns true, else - false
     * @see {@link INegativeChecker}
     */
    public boolean isNegative(String str) {
        log.info("-------- Checks negatives...");
        return str.indexOf(Operation.MINUS.getOperation()) == 0;
    }

    /**
     * Turns the negative expression into a positive.
     *
     * @param str string with expression to be turned
     * @return string with positive expression
     * @see {@link INegativeChecker}
     */
    public String processNegative(String str) {
        log.info("-------- Processes negative expression...");
        char[] arr = str.substring(1).toCharArray();
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == '+') arr[i] = '-';
            else if (arr[i] == '-') arr[i] = '+';
        }
        log.info("-------- Converted into positive expression.");
        return new String(arr);
    }
}

