package net.bondar;

import net.bondar.interfaces.*;
import net.bondar.service.api.BasicCalculatorProcessor;
import net.bondar.service.api.BasicCalculatorService;
import net.bondar.utils.*;

/**
 *
 */
public class Main {

    public static void main(String[] args) {
        try {
            IOperationHolder operationHolder = new OperationHolder();
            INegativeChecker negativeChecker = new NegativeChecker();
            INumberBuilder numberBuilder = new NumberBuilder();
            ICalculableProcessor processor = new BasicCalculatorProcessor(operationHolder, negativeChecker, numberBuilder);
            IResultViewer viewer = new ResultViewer();
            HistoryHolder historyHolder = new HistoryHolder();
            ILauncher launcher = new CalculatorLauncher(processor, viewer, historyHolder);

            ICalculableService service = new BasicCalculatorService(launcher);
            service.doCalculate();

        } catch (Throwable t) {
            t.printStackTrace();
        }
    }
}
