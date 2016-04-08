package net.bondar.utils;

import net.bondar.exceptions.CalculatorApplicationException;
import org.apache.log4j.Logger;

/**
 *
 */
public class NumberBuilder {
    private final static Logger log = Logger.getLogger(NumberBuilder.class);

    /**
     * @param stringNumber
     * @return
     */
    public int buildNumber(String stringNumber) {
        log.info("---------- Builds number...");
        if (stringNumber.length() != 0) {
            try {
                return Integer.parseInt(stringNumber);
            }catch (NumberFormatException e){
                throw new CalculatorApplicationException(e.getMessage());
            }
        }
        throw new CalculatorApplicationException("Error while building number -->"+stringNumber);
    }

    /**
     * @param str
     * @return
     */
    public String buildStringNumber(String str, String expression) {
        log.info("---------- Builds part of expression...");
        if (str.length() == 0) {
            return "";
        }
        if (expression.indexOf(str) == 0) {
            StringBuilder builder = new StringBuilder(str);
            str = builder.reverse().toString();
            str = reverseOperator(str);
            return builder.replace(0, builder.length(), str).reverse().toString();
        }
        return reverseOperator(str);
    }

    public String reverseOperator(String str) {
        if (str.length() > 1) {
            char[] arr = str.toCharArray();
            for (int i = 0; i < arr.length; i++) {
                if (arr[i] == '*' || arr[i] == '/' || arr[i] == '+' || arr[i] == '-') {
                    return str.substring(0, i);
                }
            }
        }
        return str;
    }
}
