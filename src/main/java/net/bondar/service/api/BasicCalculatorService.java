package net.bondar.service.api;

import net.bondar.interfaces.ICalculableProcessor;
import net.bondar.interfaces.ICalculableService;
import net.bondar.interfaces.ILauncher;
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
    private ILauncher launcher;

    /**
     *
     * @param launcher
     */
    public BasicCalculatorService(ILauncher launcher) {
        this.launcher = launcher;
    }

    /**
     * @return
     */
    @Override
    public void doCalculate() {
        launcher.run();
    }
}
