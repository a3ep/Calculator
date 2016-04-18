package net.bondar.domain;

import net.bondar.interfaces.IResultObject;

/**
 * Contains an implementation of empty <code>ResultObject</code>.
 */
public class NullObject implements IResultObject {

    /**
     * Result status.
     */
    private final String status;

    /**
     * Result error message.
     */
    private final String errorMessage;

    /**
     * Result value.
     */
    private final int result;

    /**
     * Creates an empty <code>ResultObject</code>.
     */
    public NullObject() {
        this.status = "NULL";
        this.errorMessage = "";
        this.result = -1;
    }

    /**
     * Gets <code>NullObject</code> status.
     *
     * @return null status
     * @see {@link IResultObject}
     */
    @Override
    public String getStatus() {
        return "NULL";
    }

    /**
     * Gets <code>NullObject</code> error message.
     *
     * @return null message
     * @see {@link IResultObject}
     */
    @Override
    public String getErrorMessage() {
        return "";
    }

    /**
     * Gets <code>NullObject</code> value of calculation.
     *
     * @return null value
     * @see {@link IResultObject}
     */
    @Override
    public int getResult() {
        return -1;
    }

    @Override
    public String toString() {
        return "NullObject{" +
                "status='" + status + '\'' +
                ", errorMessage='" + errorMessage + '\'' +
                ", result=" + result +
                '}';
    }
}
