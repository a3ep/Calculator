package net.bondar.utils;

import net.bondar.interfaces.IOperationHolder;
import org.apache.log4j.Logger;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * Provides storing and working with mathematical operations.
 */
public class OperationHolder implements IOperationHolder {

    /**
     * Logger.
     */
    private final static Logger log = Logger.getLogger(OperationHolder.class);

    /**
     * List of the mathematical operations.
     */
    private List<Operation> operations = Arrays.asList(Operation.values());

    /**
     * Gets the mathematical operations sorted by priority.
     *
     * @return sorted list of the mathematical operations
     * @see {@link IOperationHolder}
     */
    public List<Operation> getOperationsByPriority() {
        log.info("-------- Gets operations by priority...");
        Collections.sort(operations, new OperationPriorityComparator<>());
        return operations;
    }

    /**
     * Checks the mathematical operation with the same priority and returns an operator which is found first in the expression.
     *
     * @param operation  current mathematical operation
     * @param expression string with expression to be checked
     * @return the mathematical operation which the first is found in the expression
     * @see {@link IOperationHolder}
     */
    public Operation checkOperator(Operation operation, String expression) {
        if (operation == Operation.MUL || operation == Operation.DIV) {
            return swap(expression, operation, Operation.MUL, Operation.DIV);
        }
        if (operation == Operation.PLUS || operation == Operation.MINUS) {
            return swap(expression, operation, Operation.PLUS, Operation.MINUS);
        }
        return operation;
    }

    /**
     * Swaps the mathematical operation.
     *
     * @param expression string with expression
     * @param current    current mathematical operation
     * @param op1        first mathematical operation with the same priority
     * @param op2        second mathematical operation with the same priority
     * @return the operation with the lowest index in expression string
     */
    private Operation swap(String expression, Operation current, Operation op1, Operation op2) {
        int firstIndex = expression.indexOf(op1.getOperator());
        int secondIndex = expression.indexOf(op2.getOperator());
        if (firstIndex >= 0 && secondIndex >= 0) {
            return (firstIndex > secondIndex) ? op2 : op1;
        }
        return current;
    }
}
