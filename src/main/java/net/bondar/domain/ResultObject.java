package net.bondar.domain;

import net.bondar.interfaces.IResultObject;

/**
 * Contains the final result of the expression.
 */
public class ResultObject implements IResultObject {

    /**
     * Result status, should be "OK" or "ERROR".
     */
    private final String status;

    /**
     * Result error message.
     */
    private final String errorMessage;

    /**
     * Result value, contains a value of calculation.
     */
    private final int result;

    /**
     * Creates {@link ResultObject} with <code>status</code> and <code>errorMessage</code>.
     *
     * @param errorMessage error message, if during the calculation error occurred contains an error message
     */
    public ResultObject(String errorMessage) {
        this.status = "ERROR";
        this.errorMessage = errorMessage;
        this.result = -1;
    }

    /**
     * Creates {@link ResultObject} with <code>status</code> and <code>result</code>.
     *
     * @param result calculation value
     */
    public ResultObject(int result) {
        this.status = "OK";
        this.errorMessage = "";
        this.result = result;
    }

    /**
     * Gets a result status.
     *
     * @return status of calculation
     * @see {@link IResultObject}
     */
    public String getStatus() {
        return status;
    }

    /**
     * Gets a result error message.
     *
     * @return error message
     * @see {@link IResultObject}
     */
    public String getErrorMessage() {
        return errorMessage;
    }

    /**
     * Gets a result value of calculation.
     *
     * @return value of calculation
     * * @see {@link IResultObject}
     */
    public int getResult() {
        return result;
    }

    @Override
    public String toString() {
        return "ResultObject{" +
                "status='" + status + '\'' +
                ", errorMessage='" + errorMessage + '\'' +
                ", result=" + result +
                '}';
    }
}
