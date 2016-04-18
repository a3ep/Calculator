package net.bondar.service.api;

import net.bondar.domain.NullObject;
import net.bondar.domain.ResultObject;
import net.bondar.exceptions.CalculatorApplicationException;
import net.bondar.interfaces.*;
import org.apache.log4j.Logger;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;
import java.util.Set;

/**
 * Provides communication with user.
 */
public class BasicCalculatorService implements ICalculableService {
    /**
     * Logger.
     */
    private final static Logger log = Logger.getLogger(BasicCalculatorService.class);

    /**
     * Calculator processor.
     */
    private ICalculableProcessor processor;

    /**
     * Result viewer.
     */
    private IResultViewer viewer;

    /**
     * History holder.
     */
    private IHistoryHolder holder;

    /**
     * Creates <code>BasicCalculatorService</code> instance.
     *
     * @param processor calculator processor
     * @param viewer    result viewer
     * @param holder    history holder
     */
    public BasicCalculatorService(ICalculableProcessor processor, IResultViewer viewer, IHistoryHolder holder) {
        this.processor = processor;
        this.viewer = viewer;
        this.holder = holder;
    }

    /**
     * Runs communication with user.
     * <br>
     * Depending on the user input line shows history or unique history, starts processing of expression.
     * If processing was successful - transmits to <code>IResultViewer</code> completed <code>IResultObject</code> with the result
     * of calculation, if else - transmits completed <code>IResultObject</code> with error message.
     */
    public void run() {
        IResultObject result = new NullObject();
        String input;
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            log.info("----- Input your expression (example: 2 + 3 * 4):");
            input = br.readLine();
            switch (input) {
                case "history":
                    List<String> history = holder.getHistory();
                    log.info("---------- Expressions history:");
                    for (String str : history) {
                        log.info("------------  " + str);
                    }
                    log.info("---------- End of history.\n");
                    break;
                case "history unique":
                   Set<String> uniqueHistory =  holder.getUniqueHistory();
                    log.info("---------- Unique expressions history:");
                    for (String str : uniqueHistory) {
                        log.info("------------  " + str);
                    }
                    log.info("---------- End of history.\n");
                    break;
                default:
                    result = new ResultObject(processor.process(input.replaceAll(" ", "")));
                    holder.addToHistory(input + " = " + result.getResult());
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
     * Checks whether the wish of user to continue the calculation.
     * If "yes" - initiates run(), if "no" - closes <code>BufferedReader</code> and application.
     * If exception was occurred - transmits to <code>IResultViewer</code> completed <code>IResultObject</code> with error message.
     */
    private void next() {
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
                ResultObject result = new ResultObject("Wrong input string: " + e.getMessage());
                viewer.viewResult(result);
            }
        }
    }
}
