package net.bondar.service.api;

import net.bondar.interfaces.ICalculableService;
import net.bondar.interfaces.ILauncher;

/**
 *
 */

public class BasicCalculatorService implements ICalculableService {
    private ILauncher launcher;

    /**
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
