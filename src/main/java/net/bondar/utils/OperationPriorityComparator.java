package net.bondar.utils;

import net.bondar.interfaces.IComparableOperation;

import java.util.Comparator;

/**
 *
 */
public class OperationPriorityComparator<T extends IComparableOperation> implements Comparator<T> {

    /**
     * @param o1
     * @param o2
     * @return
     */
    @Override
    public int compare(T o1, T o2) {
        return o1.getPriority() - o2.getPriority();
    }
}
