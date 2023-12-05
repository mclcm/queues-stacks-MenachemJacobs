public class MyQueue<E> {
    private static final int INITIAL_CAPACITY = 10;
    private Object[] backingStore;
    private int size = 0;
    boolean overFlowFlag;

    /**
     * A private inner class representing a node in the linked list.
     * Each node contains data and a reference to the next node in the list.
     */
    private class Node {
        //The information contained in a given node
        private E data;
        //A reference to the next node down the line, allowing for iteration
        private Node next;
    }

    //Global values to be maintained at all times for all lists. Start pos, end pos, and total size.
    private Node head = null;
    private Node tail = null;
    private int size = 0;

    public MyQueue() {
        this(INITIAL_CAPACITY);
    }

    MyQueue(int capacity) {
        if (capacity < 0) {
            throw new IllegalArgumentException("capacity cannot be negative");
        }


        backingStore = new Object[capacity];
    }

    int size() {
        return !overFlowFlag ? size : Integer.MAX_VALUE;
    }

    boolean isEmpty() {
        return size() == 0;
    }
}
