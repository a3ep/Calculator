package net.bondar.utils;

import net.bondar.interfaces.IOperationHolder;
import org.apache.log4j.Logger;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 *
 */
public class OperationHolder implements IOperationHolder{
    private final static Logger log = Logger.getLogger(OperationHolder.class);
    /**
     *
     */
    private List<Operation> operations = Arrays.asList(Operation.values());

    /**
     * @return
     */
    public List<Operation> getOperationsByPriority() {
        log.info("-------- Gets operations by priority...");
        Collections.sort(operations, new OperationPriorityComparator<>());
        return operations;
    }

    public Operation checkOperator(Operation op, String expression) {
        if (op == Operation.MUL || op == Operation.DIV) {
            return swap(expression, op, Operation.MUL, Operation.DIV);
        }
        if (op == Operation.PLUS || op == Operation.MINUS) {
           return swap(expression, op, Operation.PLUS, Operation.MINUS);
        }
        return op;
    }

    /**
     *
     * @param expression
     * @param current
     * @param op1
     * @param op2
     * @return
     */
    public Operation swap(String expression,Operation current, Operation op1, Operation op2){
        int firstIndex = expression.indexOf(op1.getOperation());
        int secondIndex = expression.indexOf(op2.getOperation());
        if (firstIndex >= 0 && secondIndex >= 0) {
            return (firstIndex > secondIndex) ? op2 : op1;
        }
        return current;
    }
}
