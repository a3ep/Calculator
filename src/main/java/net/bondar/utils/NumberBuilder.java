package net.bondar.utils;

import net.bondar.exceptions.CalculatorApplicationException;
import net.bondar.interfaces.INumberBuilder;
import org.apache.log4j.Logger;

/**
 * Provides building the parts of expression.
 */
public class NumberBuilder implements INumberBuilder {

    /**
     * Logger.
     */
    private final static Logger log = Logger.getLogger(NumberBuilder.class);

    /**
     * Builds the number on the basis of expression string and expression part indexes.
     *
     * @param startIndex start expression part index
     * @param endIndex   end expression part index
     * @param expression expression string
     * @return number value
     * @throws CalculatorApplicationException when the string with a number value has incorrect format
     * @see {@link INumberBuilder}
     */
    public int buildNumber(int startIndex, int endIndex, String expression) {
        log.info("---------- Builds part of expression...");
        String expressionPart = expression.substring(startIndex, endIndex);
        String numberString;
        if (expressionPart.length() == 0) {
            numberString = "";
        } else if (expression.indexOf(expressionPart) == 0) {
            StringBuilder builder = new StringBuilder(expressionPart);
            expressionPart = builder.reverse().toString();
            expressionPart = findEndOfNumber(expressionPart);
            numberString = builder.replace(0, builder.length(), expressionPart).reverse().toString();
        } else {
            numberString = findEndOfNumber(expressionPart);
        }
        log.info("---------- Builds number...");
        if (numberString.length() != 0) {
            try {
                return Integer.parseInt(numberString);
            } catch (NumberFormatException e) {
                throw new CalculatorApplicationException(e.getMessage());
            }
        }
        throw new CalculatorApplicationException("Error while building number -->" + numberString);
    }

    /**
     * Finds the end of the number in the specified substring.
     *
     * @param str the specified substring
     * @return string with the number value
     */
    private String findEndOfNumber(String str) {
        if (str.length() > 1) {
            char[] arr = str.toCharArray();
            for (int i = 0; i < arr.length; i++) {
                if (arr[i] == Operation.MUL.getOperator().charAt(0) ||
                        arr[i] == Operation.DIV.getOperator().charAt(0) ||
                        arr[i] == Operation.PLUS.getOperator().charAt(0) ||
                        arr[i] == Operation.MINUS.getOperator().charAt(0)) {
                    return str.substring(0, i);
                }
            }
        }
        return str;
    }
}
