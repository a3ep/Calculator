package net.bondar;

import net.bondar.exceptions.CalculatorApplicationException;
import net.bondar.interfaces.ICalculableProcessor;
import net.bondar.interfaces.INegativeChecker;
import net.bondar.interfaces.INumberBuilder;
import net.bondar.interfaces.IOperationHolder;
import net.bondar.service.api.BasicCalculatorProcessor;
import net.bondar.service.api.BracketsCalculatorProcessor;
import net.bondar.utils.NegativeChecker;
import net.bondar.utils.NumberBuilder;
import net.bondar.utils.OperationHolder;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

/**
 * Tests application.
 */
public class CalculatorTest {

    /**
     * Calculator processor.
     */
    private static ICalculableProcessor processor;

    /**
     * List of arguments.
     */
    private List<String> args = new ArrayList<>();

    @BeforeClass

    /**
     * Initializes processor instance.
     */
    public static void start() {
        IOperationHolder operationHolder = new OperationHolder();
        INegativeChecker negativeChecker = new NegativeChecker();
        INumberBuilder numberBuilder = new NumberBuilder();
        processor = new BracketsCalculatorProcessor(new BasicCalculatorProcessor(operationHolder, negativeChecker, numberBuilder));
    }

    /**
     * Tests the applications work with incorrect arguments.
     */
    @Test(expected = CalculatorApplicationException.class)
    public void testWrongArguments() {
        args.add("abc");
        args.add("--2+2");
        args.add("10 15");
        args.add("2+(a-b)");
        args.add("100.a");
        args.add("");
        args.add("+/1");
        for (String arg : args) {
            processor.process(arg);
        }
    }

    /**
     * Tests the applications work with correct arguments.
     */
    @Test
    public void testCorrectArguments() {
        assertEquals(6, processor.process("2+2*2"));
        assertEquals(8, processor.process("-2*3+14"));
        assertEquals(11, processor.process("13-6+(2+6)/2"));
        assertEquals(4, processor.process("2+3*2-8/2"));
        assertEquals(16, processor.process("12+(-6/3)+3*2"));
    }

}
