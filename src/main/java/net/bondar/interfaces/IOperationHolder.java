package net.bondar.interfaces;

import net.bondar.utils.Operation;

import java.util.List;

/**
 *
 */
public interface IOperationHolder {
    /**
     *
     * @return
     */
    List<Operation> getOperationsByPriority();

    /**
     *
     * @param operation
     * @param expression
     * @return
     */
    Operation checkOperator(Operation operation, String expression);
}
