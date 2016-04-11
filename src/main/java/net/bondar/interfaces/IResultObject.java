package net.bondar.interfaces;

import net.bondar.domain.NullObject;
import net.bondar.domain.ResultObject;

/**
 * Interface for a result object ({@link ResultObject} or {@link NullObject}).
 */
public interface IResultObject {
    /**
     * Gets a result status.
     *
     * @return result status
     */
    String getStatus();

    /**
     * Gets a result error message.
     *
     * @return result error message
     */
    String getErrorMessage();

    /**
     * Gets a result value of calculation.
     *
     * @return result value
     */
    int getResult();
}

