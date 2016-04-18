package net.bondar.utils;


import net.bondar.interfaces.IComparableOperation;
import net.bondar.interfaces.IOperation;
import org.apache.log4j.Logger;

/**
 * Contains mathematical operation.
 */
public enum Operation implements IOperation, IComparableOperation {
    /**
     * Addition operation.
     */
    PLUS("+", 2) {
        /**
         * Calculates the result of adding two numbers.
         *
         * @param x first number
         * @param y second number
         * @return the result of addition
         */
        public int calculate(int x, int y) {
            log.info("-------------- Addition: " + x + " + " + y);
            return x + y;
        }
    },

    /**
     * Subtraction operation.
     */
    MINUS("-", 2) {
        /**
         * Calculates the result of subtracting two numbers.
         *
         * @param x first number
         * @param y second number
         * @return the result of subtraction
         */
        public int calculate(int x, int y) {
            log.info("-------------- Subtraction: " + x + " - " + y);
            return x - y;
        }
    },

    /**
     * Multiplication operation.
     */
    MUL("*", 1) {
        /**
         * Calculates the result of the product of two numbers.
         *
         * @param x first number
         * @param y second number
         * @return the result of multiplication
         */
        public int calculate(int x, int y) {
            log.info("-------------- Multiplication: " + x + " * " + y);
            return x * y;
        }
    },

    /**
     * Division operation.
     */
    DIV("/", 1) {
        /**
         * Calculates the result of the division of two numbers.
         *
         * @param x first number
         * @param y second number
         * @return the result of division
         */
        public int calculate(int x, int y) {
            log.info("-------------- Division: " + x + " / " + y);
            return x / y;
        }
    };

    /**
     * Logger.
     */
    private final static Logger log = Logger.getLogger(Operation.class);

    /**
     * Creates <code>Operation</code> instance.
     *
     * @param operator the mathematical operator
     * @param priority priority of the mathematical operation
     */
    Operation(String operator, int priority) {
        this.operator = operator;
        this.priority = priority;
    }

    /**
     * Gets a mathematical operator.
     *
     * @return value of the mathematical operator
     * @see {@link IOperation}
     */
    public String getOperator() {
        return operator;
    }


    /**
     * Gets a mathematical operation priority.
     *
     * @return a mathematical operation priority
     * @see {@link IOperation}
     */
    public int getPriority() {
        return priority;
    }


    /**
     * The mathematical operator.
     */
    private String operator;

    /**
     * Priority of the mathematical operator.
     */
    private int priority;
}
