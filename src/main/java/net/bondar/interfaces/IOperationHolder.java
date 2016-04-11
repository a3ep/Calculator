package net.bondar.interfaces;

import net.bondar.utils.Operation;

import java.util.List;

/**
 * Interface for storing and working with mathematical operations.
 */
public interface IOperationHolder {

    /**
     * Gets the mathematical operations sorted by priority.
     *
     * @return list of the mathematical operations
     */
    List<Operation> getOperationsByPriority();

    /**
     * Checks the mathematical operation with the same priority and the returns which the first is found in the expression.
     *
     * @param operation  current mathematical operation
     * @param expression string with expression to be checked
     * @return the mathematical operation which the first is found in the expression
     */
    Operation checkOperator(Operation operation, String expression);
}
