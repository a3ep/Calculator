package net.bondar.service.api;

import net.bondar.exceptions.CalculatorApplicationException;
import net.bondar.interfaces.INumberBuilder;
import net.bondar.interfaces.ICalculableProcessor;
import net.bondar.interfaces.INegativeChecker;
import net.bondar.interfaces.IOperationHolder;
import net.bondar.utils.Operation;
import org.apache.log4j.Logger;

/**
 *
 */
public class BasicCalculatorProcessor implements ICalculableProcessor {
    private final static Logger log = Logger.getLogger(BasicCalculatorProcessor.class);
    private IOperationHolder operationHolder;
    private INegativeChecker negativeChecker;
    private INumberBuilder numberBuilder;

    /**
     * @param operationHolder
     * @param negativeChecker
     * @param numberBuilder
     */
    public BasicCalculatorProcessor(IOperationHolder operationHolder,
                                    INegativeChecker negativeChecker,
                                    INumberBuilder numberBuilder) {
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
        op = operationHolder.checkOperator(op, expression);
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
