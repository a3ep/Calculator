package net.bondar.utils;

import org.apache.log4j.Logger;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 *
 */
public class OperationHolder {
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

    public Operation checkSimple(Operation op, String expression) {
        log.info("-------- Checks is a simple operation - " + op.getOperation());
        if (op == Operation.PLUS || op == Operation.MINUS) {
            log.info("-------- Operation is simple.");
            int plusIndex = expression.indexOf(Operation.PLUS.getOperation());
            int minusIndex = expression.indexOf(Operation.MINUS.getOperation());
            if (plusIndex >= 0 && minusIndex >= 0) {
                return (plusIndex > minusIndex) ? Operation.MINUS : Operation.PLUS;
            }
            log.info("-------- Operation changed to - " + op.getOperation());
        }
        return op;
    }
}
