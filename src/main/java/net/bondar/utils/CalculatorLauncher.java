package net.bondar.utils;

import net.bondar.domain.ResultObject;
import net.bondar.exceptions.CalculatorApplicationException;
import net.bondar.interfaces.ICalculableProcessor;
import org.apache.log4j.Logger;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 *
 */
public class CalculatorLauncher {
    private final static Logger log = Logger.getLogger(CalculatorLauncher.class);
    private ICalculableProcessor processor;

    /**
     * @param processor
     */
    public CalculatorLauncher(ICalculableProcessor processor) {
        this.processor = processor;
    }

    /**
     * @return
     */
    public void run() {
        ResultObject result;
        String input;
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            log.info("----- Input your expression:");
            input = br.readLine();
            result = new ResultObject("OK", processor.process(input));
        } catch (IOException | CalculatorApplicationException e) {
            log.debug("Error while calculation:\n" + e.getMessage());
            result = new ResultObject("ERROR", "Wrong input string: " + e.getMessage());
        }
        if (result.getStatus().equals("OK")) {
            log.info("----- Calculation success.");
            log.info("----- Result --> " + result.getResult() + "\n");
        } else {
            log.info("----- Calculation failed.");
            log.warn("----- Error message: " + result.getErrorMessage()+"\n");
        }
    }
}
