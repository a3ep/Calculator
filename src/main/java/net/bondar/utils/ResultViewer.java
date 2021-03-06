package net.bondar.utils;

import net.bondar.interfaces.IResultObject;
import net.bondar.interfaces.IResultViewer;
import org.apache.log4j.Logger;

/**
 * Provides displaying the calculation result.
 */
public class ResultViewer implements IResultViewer {

    /**
     * Logger.
     */
    private final static Logger log = Logger.getLogger(ResultViewer.class);

    /**
     * Displays the result of the calculation to the console.
     *
     * @param object object with the result of calculation
     * @see {@link IResultViewer}
     */
    public void viewResult(IResultObject object) {
        if (object.getStatus().equals("NULL")) {

        } else if (object.getStatus().equals("OK")) {
            log.info("----------- Calculation success.");
            log.info("----------- Result --> " + object.getResult() + "\n");
        } else if (object.getStatus().equals("ERROR")) {
            log.info("----------- Calculation failed.");
            log.warn("----------- Error message: " + object.getErrorMessage() + "\n");

        }
    }
}
