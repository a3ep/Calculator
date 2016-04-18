package net.bondar;

import net.bondar.interfaces.*;
import net.bondar.service.api.BasicCalculatorProcessor;
import net.bondar.service.api.BasicCalculatorService;
import net.bondar.service.api.BracketsCalculatorProcessor;
import net.bondar.utils.*;

/**
 * Provides the beginning of the application.
 */
public class Main {

    public static void main(String[] args) {
        try {
            IOperationHolder operationHolder = new OperationHolder();
            INegativeChecker negativeChecker = new NegativeChecker();
            INumberBuilder numberBuilder = new NumberBuilder();
            ICalculableProcessor processor = new BracketsCalculatorProcessor(new BasicCalculatorProcessor(operationHolder, negativeChecker, numberBuilder));
            IResultViewer viewer = new ResultViewer();
            IHistoryHolder historyHolder = new HistoryHolder();

            ICalculableService service = new BasicCalculatorService(processor, viewer, historyHolder);
            service.run();
        } catch (Throwable t) {
            t.printStackTrace();
        }
    }
}
