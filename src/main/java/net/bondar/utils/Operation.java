package net.bondar.utils;


import net.bondar.interfaces.ComparableOperation;
import org.apache.log4j.Logger;

import java.util.ArrayList;
import java.util.List;

/**
 *
 */
public enum Operation implements ComparableOperation {
    /**
     *
     */
    PLUS("+", 2) {
        public int calculate(int x, int y) {
            log.info("-------------- Addition: " + x + " + " + y);
            return x + y;
        }
    },

    /**
     *
     */
    MINUS("-", 2) {
        public int calculate(int x, int y) {
            log.info("-------------- Substraction: " + x + " - " + y);
            return x - y;
        }
    },

    /**
     *
     */
    MUL("*", 1) {
        public int calculate(int x, int y) {
            log.info("-------------- Multiplication: " + x + " * " + y);
            return x * y;
        }
    },

    /**
     *
     */
    DIV("/", 1) {
        public int calculate(int x, int y) {
            log.info("-------------- Division: " + x + " / " + y);
            return x / y;
        }
    };
    private final static Logger log = Logger.getLogger(Operation.class);

    /**
     * @param operation
     * @param priority
     */
    Operation(String operation, int priority) {
        this.operation = operation;
        this.priority = priority;
    }

    /**
     * @return
     */
    public String getOperation() {
        return operation;
    }

    public static Operation getOperation(String operator) {
        Operation result = null;
        for (Operation operation : Operation.values()) {
            if (operator.equals(operation.operation)) result = operation;
        }
        return result;
    }

    /**
     * @return
     */
    public int getPriority() {
        return priority;
    }


    /**
     *
     */
    private String operation;

    /**
     *
     */
    private int priority;

    /**
     *
     */
    private List<Operation> highPriorityOperations = new ArrayList<>();

    /**
     *
     */
    private List<Operation> lowPriorityOperations = new ArrayList<>();

    /**
     * @param x
     * @param y
     * @return
     */
    public abstract int calculate(int x, int y);

}
