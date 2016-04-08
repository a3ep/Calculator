package net.bondar;

import net.bondar.interfaces.ICalculableProcessor;
import net.bondar.interfaces.ICalculableService;
import net.bondar.service.api.BasicCalculatorProcessor;
import net.bondar.service.api.BasicCalculatorService;
import net.bondar.utils.CalculatorLauncher;
import net.bondar.utils.NegativeChecker;
import net.bondar.utils.NumberBuilder;
import net.bondar.utils.OperationHolder;

/**
 *
 */
public class Main {

    public static void main(String[] args) {
        try {
            OperationHolder operationHolder = new OperationHolder();
            NegativeChecker negativeChecker = new NegativeChecker();
            NumberBuilder numberBuilder = new NumberBuilder();
            ICalculableProcessor processor = new BasicCalculatorProcessor(operationHolder, negativeChecker, numberBuilder);
            CalculatorLauncher launcher = new CalculatorLauncher(processor);

            ICalculableService service = new BasicCalculatorService(processor, operationHolder, negativeChecker, numberBuilder, launcher);
            service.doCalculate();

        } catch (Throwable t) {
            t.printStackTrace();
        }
    }
}
