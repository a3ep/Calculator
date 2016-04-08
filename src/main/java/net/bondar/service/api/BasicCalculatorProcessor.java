package net.bondar.service.api;

import net.bondar.exceptions.CalculatorApplicationException;
import net.bondar.interfaces.ICalculableProcessor;
import net.bondar.utils.NegativeChecker;
import net.bondar.utils.NumberBuilder;
import net.bondar.utils.Operation;
import net.bondar.utils.OperationHolder;
import org.apache.log4j.Logger;

/**
 *
 */
public class BasicCalculatorProcessor implements ICalculableProcessor {
    private final static Logger log = Logger.getLogger(BasicCalculatorProcessor.class);
    private OperationHolder operationHolder;
    private NegativeChecker negativeChecker;
    private NumberBuilder numberBuilder;

    /**
     * @param operationHolder
     * @param negativeChecker
     * @param numberBuilder
     */
    public BasicCalculatorProcessor(OperationHolder operationHolder, NegativeChecker negativeChecker, NumberBuilder numberBuilder) {
        this.operationHolder = operationHolder;
        this.negativeChecker = negativeChecker;
        this.numberBuilder = numberBuilder;
    }

    /**
     * @param expression
     * @return
     */
    @Override
    public int process(String expression) {
        log.info("Processes: " + expression);
        int opIndex;
        if (negativeChecker.isNegative(expression)) {
            log.info("Expression is negative.");
            return process(negativeChecker.processNegative(expression)) * -1;
        }
        for (Operation op : operationHolder.getOperationsByPriority()) {
            opIndex = expression.indexOf(op.getOperation());
            if (opIndex != -1) {
                log.info("Found operator - " + op.getOperation());
                return getExpressionValue(expression, op);
            }
        }
        try{
            return Integer.parseInt(expression);
        }catch (NumberFormatException e){
            throw new CalculatorApplicationException(e.getMessage());
        }

    }

    /**
     * @param expression
     * @param op
     * @return
     */
    public int getExpressionValue(String expression, Operation op) {
        log.info("Calculates an intermediate value...");
        int opIndex;
        int leftNumber;
        int rightNumber;
        String leftSubstring;
        String rightSubstring;
        op = operationHolder.checkSimple(op, expression);
        opIndex = expression.indexOf(op.getOperation());
        log.info("Searches right number...");
        rightSubstring = numberBuilder.buildStringNumber(expression.substring(opIndex + 1), expression);
        rightNumber = numberBuilder.buildNumber(rightSubstring);
        log.info("Right number -> " + rightNumber);
        log.info("Searches left number...");
        leftSubstring = numberBuilder.buildStringNumber(expression.substring(0, opIndex), expression);
        leftNumber = numberBuilder.buildNumber(leftSubstring);
        log.info("Left number -> " + leftNumber);
        return process(expression.substring(0, opIndex - leftSubstring.length()) + op.calculate(leftNumber, rightNumber)
                + expression.substring(opIndex + rightSubstring.length() + 1));
    }
}
