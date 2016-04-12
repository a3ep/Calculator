package net.bondar.service.api;

import net.bondar.domain.NullObject;
import net.bondar.domain.ResultObject;
import net.bondar.exceptions.CalculatorApplicationException;
import net.bondar.interfaces.*;
import org.apache.log4j.Logger;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

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
     * Creates {@link BasicCalculatorService} instance.
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
     * If processing was successful - transmits to {@link IResultViewer} completed {@link IResultObject} with the result
     * of calculation, if else - transmits completed {@link IResultObject} with error message.
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
                    holder.showHistory();
                    break;
                case "history unique":
                    holder.showUniqueHistory();
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
     * If "yes" - initiates run(), if "no" - closes {@link BufferedReader} and application.
     * If exception was occurred - transmits to {@link IResultViewer} completed {@link IResultObject} with error message.
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
