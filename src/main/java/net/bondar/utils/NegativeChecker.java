package net.bondar.utils;

import org.apache.log4j.Logger;

/**
 *
 */
public class NegativeChecker {
    private final static Logger log = Logger.getLogger(NegativeChecker.class);

    /**
     * @return
     */
    public boolean isNegative(String str) {
        log.info("-------- Checks negatives...");
        return str.indexOf(Operation.MINUS.getOperation()) == 0;
    }

    /**
     * @param str
     * @return
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

