package net.bondar.utils;

import net.bondar.interfaces.ICalculableProcessor;
import net.bondar.interfaces.ICalculableProcessorDecorator;
import net.bondar.service.api.BasicCalculatorProcessor;
import org.apache.log4j.Logger;

/**
 * Provides calculation of expression with brackets.
 */
public class BracketsCalculatorProcessor implements ICalculableProcessor, ICalculableProcessorDecorator {
    /**
     * Logger.
     */
    private final static Logger log = Logger.getLogger(BracketsCalculatorProcessor.class);

    /**
     * Calculator processor.
     */
    private ICalculableProcessor processor;

    /**
     * Creates {@link BracketsCalculatorProcessor} instance.
     *
     * @param processor calculator processor
     */
    public BracketsCalculatorProcessor(ICalculableProcessor processor) {
        this.processor = processor;
    }

    /**
     * Calculates the value based on the availability of brackets.
     *
     * @param expression string with expression to be processed
     * @return value of calculation
     * @see {@link ICalculableProcessor}, {@link BasicCalculatorProcessor}
     */
    @Override
    public int process(String expression) {
        log.info("Checks brackets...");
        int lastIndex = expression.indexOf(")");
        int firstIndex = expression.indexOf("(");
        if (firstIndex != -1 && lastIndex != -1) {
            log.info("Processes expression in the brackets...");
            int digit = processor.process(expression.substring(firstIndex + 1, lastIndex));
            return process(expression.substring(0, firstIndex) + digit + expression.substring(lastIndex + 1));
        }
        return processor.process(expression);
    }
}
