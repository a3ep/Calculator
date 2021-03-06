package net.bondar.service.api;

import net.bondar.exceptions.CalculatorApplicationException;
import net.bondar.interfaces.ICalculableProcessor;
import net.bondar.interfaces.INegativeChecker;
import net.bondar.interfaces.INumberBuilder;
import net.bondar.interfaces.IOperationUtils;
import net.bondar.utils.Operation;
import org.apache.log4j.Logger;

/**
 * Provides calculation of expression.
 */
public class BasicCalculatorProcessor implements ICalculableProcessor {

    /**
     * Logger.
     */
    private final static Logger log = Logger.getLogger(BasicCalculatorProcessor.class);

    /**
     * Operation holder.
     */
    private IOperationUtils holder;

    /**
     * Negative expression checker.
     */
    private INegativeChecker checker;

    /**
     * Number builder.
     */
    private INumberBuilder builder;

    /**
     * Creates <code>BasicCalculatorProcessor</code> instance.
     *
     * @param holder  operation holder
     * @param checker negative expression checker
     * @param builder number builder
     */
    public BasicCalculatorProcessor(IOperationUtils holder,
                                    INegativeChecker checker,
                                    INumberBuilder builder) {
        this.holder = holder;
        this.checker = checker;
        this.builder = builder;
    }

    /**
     * Calculates expression value.
     *
     * @param expression string with expression to be processed
     * @return value of calculation
     * @throws CalculatorApplicationException when the result value has incorrect format
     * @see {@link ICalculableProcessor}
     */
    @Override
    public int process(String expression) {
        log.info("Processes: " + expression);
        int opIndex;
        if (checker.isNegative(expression)) {
            log.info("Expression is negative.");
            return process(checker.processNegative(expression)) * -1;
        }
        for (Operation op : holder.getOperationsByPriority()) {
            opIndex = expression.indexOf(op.getOperator());
            if (opIndex != -1) {
                return getExpressionValue(expression, op);
            }
        }
        try {
            return Integer.parseInt(expression);
        } catch (NumberFormatException e) {
            throw new CalculatorApplicationException(e.getMessage());
        }

    }

    /**
     * Defines the left and right sides of the expression and produces the required mathematical operation.
     *
     * @param expression string with expression to be processed
     * @param op         current mathematical operation
     * @return returns partially processed expression string back into the <code>process</code> method
     */
    private int getExpressionValue(String expression, Operation op) {
        log.info("Calculates an intermediate value...");
        int opIndex;
        Integer leftNumber;
        Integer rightNumber;
        op = holder.findNextOperation(op, expression);
        opIndex = expression.indexOf(op.getOperator());
        log.info("Searches right number...");
        rightNumber = builder.buildNumber(opIndex + 1, expression.length(), expression);
        log.info("Right number -> " + rightNumber);
        log.info("Searches left number...");
        leftNumber = builder.buildNumber(0, opIndex, expression);
        log.info("Left number -> " + leftNumber);
        return process(expression.substring(0, opIndex - leftNumber.toString().length()) + op.calculate(leftNumber, rightNumber)
                + expression.substring(opIndex + rightNumber.toString().length() + 1));
    }
}
