package net.bondar.service.api;

import net.bondar.interfaces.ICalculableProcessor;
import net.bondar.interfaces.ICalculableService;
import net.bondar.utils.CalculatorLauncher;
import net.bondar.utils.NegativeChecker;
import net.bondar.utils.NumberBuilder;
import net.bondar.utils.OperationHolder;
import org.apache.log4j.Logger;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 *
 */

public class BasicCalculatorService implements ICalculableService {
    private final static Logger log = Logger.getLogger(BasicCalculatorService.class);
    private ICalculableProcessor processor;
    private OperationHolder operationHolder;
    private NegativeChecker negativeChecker;
    private NumberBuilder numberBuilder;
    private CalculatorLauncher launcher;

    public BasicCalculatorService(ICalculableProcessor processor,
                                  OperationHolder operationHolder,
                                  NegativeChecker negativeChecker,
                                  NumberBuilder numberBuilder,
                                  CalculatorLauncher launcher) {
        this.processor = processor;
        this.operationHolder = operationHolder;
        this.negativeChecker = negativeChecker;
        this.numberBuilder = numberBuilder;
        this.launcher = launcher;
    }

    /**
     * @return
     */
    @Override
    public void doCalculate() {
        launcher.run();
        while (true) {
            log.info("- Perform another calculation? (yes/no)");
            try {
                BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
                switch (br.readLine()) {
                    case "yes":
                        launcher.run();
                        break;
                    case "no":
                        log.info("- Closing application.");
                        br.close();
                        System.exit(0);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
