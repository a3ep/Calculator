package net.bondar.utils;

import net.bondar.interfaces.ICalculableProcessor;
import net.bondar.interfaces.ICalculableProcessorDecorator;
import net.bondar.service.api.BasicCalculatorProcessor;
import org.apache.log4j.Logger;

/**
 * Provides calculation of expression with brackets.
 */
public class BracketsCalculatorProcessor implements ICalculableProcessor, ICalculableProcessorDecorator {
    /**
     * Logger.
     */
    private final static Logger log = Logger.getLogger(BracketsCalculatorProcessor.class);

    /**
     * Calculator processor.
     */
    private ICalculableProcessor processor;

    /**
     * Creates {@link BracketsCalculatorProcessor} instance.
     *
     * @param processor calculator processor
     */
    public BracketsCalculatorProcessor(ICalculableProcessor processor) {
        this.processor = processor;
    }

    /**
     * Calculates the value based on the availability of brackets.
     *
     * @param expression string with expression to be processed
     * @return value of calculation
     * @see {@link ICalculableProcessor}, {@link BasicCalculatorProcessor}
     */
    @Override
    public int process(String expression) {
        log.info("Checks brackets...");
        int lastIndex = expression.indexOf(")");
        int firstIndex = expression.indexOf("(");
        if (firstIndex != -1 && lastIndex != -1) {
            log.info("Processes expression in the brackets...");
            StringBuilder leftPart = new StringBuilder(expression.substring(0, firstIndex));
            int number = processor.process(expression.substring(firstIndex + 1, lastIndex));
            if (number < 0 && leftPart.length() > 0) {
                number *= -1;
                leftPart = convertNegative(leftPart);
                return process(leftPart.toString() + number + expression.substring(lastIndex + 1));
            }
            return process(leftPart.toString() + number + expression.substring(lastIndex + 1));
        }
        return processor.process(expression);
    }

    /**
     * Converts the negative expression into a positive.
     *
     * @param leftPart left part of specified expression string
     * @return converted left part of specified expression string
     */
    private StringBuilder convertNegative(StringBuilder leftPart) {
        leftPart = leftPart.reverse();
        char[] arr = leftPart.toString().toCharArray();
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == '+') {
                arr[i] = '-';
                leftPart = new StringBuilder(new String(arr));
                break;
            } else if (arr[i] == '-') {
                arr[i] = '+';
                leftPart = new StringBuilder(new String(arr));
                break;
            } else if (arr[i] == arr[arr.length - 1]) {
                char[] newArr = new char[arr.length + 1];
                newArr[newArr.length - 1] = '-';
                System.arraycopy(arr, 0, newArr, 0, arr.length);
                leftPart = new StringBuilder(new String(newArr));
                break;
            }
        }
        return leftPart.reverse();
    }
}
