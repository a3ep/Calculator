package net.bondar.utils;

import net.bondar.interfaces.IComparableOperation;

import java.util.Comparator;

/**
 * Provides a comparison of mathematical operations by priority.
 */
class OperationPriorityComparator<T extends IComparableOperation> implements Comparator<T> {

    /**
     * Compares the mathematical operations by priority.
     *
     * @param o1 first operation
     * @param o2 second operation
     * @return 1 - if <code>o1</code> > <code>o2</code>, -1 - if <code>o1</code> < <code>o2</code> and 0 - if equal
     * @see {@link IComparableOperation}, {@link Comparator}
     */
    @Override
    public int compare(T o1, T o2) {
        return o1.getPriority() - o2.getPriority();
    }
}
