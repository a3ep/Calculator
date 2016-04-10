package net.bondar.utils;

import org.apache.log4j.Logger;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;

/**
 *
 */
public class HistoryHolder {
    private final static Logger log = Logger.getLogger(HistoryHolder.class);
    private List<String> history = new ArrayList<>();

    /**
     *
     * @param expression
     */
    public void addToHistory(String expression){
        history.add(expression);
    }

    /**
     *
     */
    public void showHistory(){
        log.info("---------- Expressions history:");
        for(String str:history){
            log.info("------------  "+str);
        }
    }

    /**
     *
     */
    public void showUniqueHistory(){
        LinkedHashSet<String> historySet = new LinkedHashSet<>(history);
        log.info("---------- Unique expressions history:");
        for(String str:historySet){
            log.info("------------  "+str);
        }
    }
}
