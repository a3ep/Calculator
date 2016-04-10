package net.bondar.utils;

import net.bondar.interfaces.IResultObject;
import net.bondar.interfaces.IResultViewer;
import org.apache.log4j.Logger;

/**
 *
 */
public class ResultViewer implements IResultViewer{
    private final static Logger log = Logger.getLogger(ResultViewer.class);

    public void viewResult(IResultObject object){
        if (object.getStatus().equals("OK")) {
            log.info("----------- Calculation success.");
            log.info("----------- Result --> " + object.getResult() + "\n");
        } else if(object.getStatus().equals("ERROR")) {
            log.info("----------- Calculation failed.");
            log.warn("----------- Error message: " + object.getErrorMessage() + "\n");
        }else{
            log.info("----------- End of history.\n");
        }
    }
}
