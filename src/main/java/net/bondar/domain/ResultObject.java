package net.bondar.domain;

import net.bondar.interfaces.IResultObject;

/**
 *
 */
public class ResultObject implements IResultObject{

    /**
     *
     */
    private final String status;

    /**
     *
     */
    private final String errorMessage;

    /**
     *
     */
    private final int result;

    /**
     * @param status
     * @param errorMessage
     */
    public ResultObject(String status, String errorMessage) {
        this.status = status;
        this.errorMessage = errorMessage;
        this.result = -1;
    }

    /**
     * @param status
     * @param result
     */
    public ResultObject(String status, int result) {
        this.status = status;
        this.errorMessage = "";
        this.result = result;
    }

    /**
     * @return
     */
    public String getStatus() {
        return status;
    }

    /**
     * @return
     */
    public String getErrorMessage() {
        return errorMessage;
    }

    /**
     * @return
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
