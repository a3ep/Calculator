package net.bondar.domain;

import net.bondar.interfaces.IResultObject;

/**
 * Contains an implementation of empty {@link ResultObject}.
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
     * Creates an empty {@link ResultObject}.
     */
    public NullObject() {
        this.status = "NULL";
        this.errorMessage = "";
        this.result = -1;
    }

    /**
     * Gets {@link NullObject} status.
     *
     * @return null status
     * @see {@link IResultObject}
     */
    @Override
    public String getStatus() {
        return "NULL";
    }

    /**
     * Gets {@link NullObject} error message.
     *
     * @return null message
     * @see {@link IResultObject}
     */
    @Override
    public String getErrorMessage() {
        return "";
    }

    /**
     * Gets {@link NullObject} value of calculation.
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
