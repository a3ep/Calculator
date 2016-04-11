package net.bondar.utils;

import net.bondar.domain.NullObject;
import net.bondar.domain.ResultObject;
import net.bondar.exceptions.CalculatorApplicationException;
import net.bondar.interfaces.ICalculableProcessor;
import net.bondar.interfaces.ILauncher;
import net.bondar.interfaces.IResultObject;
import net.bondar.interfaces.IResultViewer;
import org.apache.log4j.Logger;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 *
 */
public class CalculatorLauncher implements ILauncher {
    private final static Logger log = Logger.getLogger(CalculatorLauncher.class);
    private ICalculableProcessor processor;
    private IResultViewer viewer;
    private HistoryHolder historyHolder;

    /**
     * @param processor
     * @param viewer
     */
    public CalculatorLauncher(ICalculableProcessor processor, IResultViewer viewer, HistoryHolder historyHolder) {
        this.processor = processor;
        this.viewer = viewer;
        this.historyHolder = historyHolder;
    }

    /**
     * @return
     */
    public void run() {
        IResultObject result = new NullObject();
        String input;
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            log.info("----- Input your expression:");
            input = br.readLine();
            switch (input){
                case "history":
                    historyHolder.showHistory();
                    break;
                case "history unique":
                    historyHolder.showUniqueHistory();
                    break;
                default:
                    result = new ResultObject(processor.process(input));
                    historyHolder.addToHistory(input+" = "+result.getResult());
                    break;
            }
        } catch (IOException | CalculatorApplicationException e) {
            log.debug("Error while calculation:\n" + e.getMessage());
            result = new ResultObject("Wrong input string: " + e.getMessage());
        }
        viewer.viewResult(result);
        next();
    }

    /**
     *
     */
    public void next() {
        while (true) {
            log.info("- Perform another calculation? (yes/no)");
            try {
                BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
                switch (br.readLine()) {
                    case "yes":
                        run();
                        break;
                    case "no":
                        log.info("- Closing application.");
                        br.close();
                        System.exit(0);
                }
            } catch (IOException e) {
                ResultObject result = new ResultObject("Wrong input string: "+e.getMessage());
                viewer.viewResult(result);
            }
        }
    }
}
