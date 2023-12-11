import java.util.*;

/**
 * MyQueue represents a basic implementation of a circular queue using an array.
 * Elements are enqueued at the back and dequeued from the front.
 * The circular queue allows efficient use of space by wrapping around the array.
 */
public class MyQueue {
    private static final int DEFAULT_INITIAL_CAPACITY = 10;
    String[] backingStore;
    private int size = 0;
    private int front = 0;
    private int back = 0;

    public MyQueue() {
        this(DEFAULT_INITIAL_CAPACITY);
    }

    MyQueue(int capacity) {
        if (capacity < 0) {
            throw new IllegalArgumentException("capacity cannot be negative");
        }

        backingStore = new String[capacity];
    }

    /**
     * Returns the current size of the queue.
     *
     * @return The number of elements in the queue.
     */
    public int size() {
        return size;
    }

    /**
     * Checks if the queue is empty.
     *
     * @return true if the queue is empty, false otherwise.
     */
    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * Returns {@code true} if this collection contains the specified element.
     * More formally, returns {@code true} if and only if this collection
     * contains at least one element {@code e} such that
     * {@code Objects.equals(o, e)}.
     *
     * @param o element whose presence in this collection is to be tested
     * @return {@code true} if this collection contains the specified
     * element
     * @throws ClassCastException   if the type of the specified element
     *                              is incompatible with this collection
     *                              (<a href="{@docRoot}/java.base/java/util/Collection.html#optional-restrictions">optional</a>)
     * @throws NullPointerException if the specified element is null and this
     *                              collection does not permit null elements
     *                              (<a href="{@docRoot}/java.base/java/util/Collection.html#optional-restrictions">optional</a>)
     */
    public boolean contains(Object o) {
        boolean returnVal = false;

        for (int i = 0; i < size; i++) {
            if (Objects.equals(backingStore[(i + front) % backingStore.length], o)) {
                returnVal = true;
                break;
            }
        }

        return returnVal;
    }

    /**
     * Returns an iterator over the elements in this collection.  There are no
     * guarantees concerning the order in which the elements are returned
     * (unless this collection is an instance of some class that provides a
     * guarantee).
     *
     * @return an {@code Iterator} over the elements in this collection
     */
    public Iterator<String> iterator() {
        return new MyIterator();
    }

    private class MyIterator implements Iterator<String> {
        int cursor = front;
        int counter = 0;

        /**
         * Returns {@code true} if the iteration has more elements.
         * (In other words, returns {@code true} if {@link #next} would
         * return an element rather than throwing an exception.)
         *
         * @return {@code true} if the iteration has more elements
         */
        @Override
        public boolean hasNext() {
            return counter < size;
        }

        /**
         * Returns the next element in the iteration.
         *
         * @return the next element in the iteration
         * @throws NoSuchElementException if the iteration has no more elements
         */
        @Override
        public String next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }

            counter++;
            return backingStore[cursor++ % backingStore.length];
        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException("remove() is not supported");
        }
    }

    /**
     * Enqueues a new element at the back of the queue.
     *
     * @param s The string to be enqueued.
     * @throws IllegalArgumentException if the queue is full.
     */
    public void enqueue(String s) {
        if (size + 1 == Integer.MAX_VALUE)
            throw new IllegalArgumentException("may not add more values to this Stack");

        if (size == backingStore.length)
            resize();

        if (isEmpty()) {
            backingStore[0] = s;
        } else {
            back = (back + 1) % backingStore.length;
            backingStore[back] = s;
        }

        size++;
    }

    /**
     * Refactors the backing store array to accommodate more elements.
     * Invoked when the current capacity is reached during an enqueue operation.
     */
    private void resize() {
        String[] holder = backingStore;
        backingStore = new String[backingStore.length + DEFAULT_INITIAL_CAPACITY];
        for (int i = 0; i < holder.length; i++) {
            backingStore[i] = holder[(i + front) % holder.length];
        }
        front = 0;
        back = size - 1;
    }

    /**
     * Dequeues an element from the front of the queue.
     *
     * @return The dequeued string.
     * @throws IllegalStateException if the queue is empty.
     */
    public String dequeue() {
        if (isEmpty())
            throw new IllegalStateException("Can not dequeue from an empty que");

        String returnVal = backingStore[front];
        front = (front + 1) % backingStore.length;
        size--;

        if (isEmpty()) {
            front = 0;
            back = 0;
        }

        return returnVal;
    }

    /**
     * Returns an array representation of the elements in the queue.
     *
     * @return An array of strings representing the elements in the queue.
     */
    public String[] toArray() {
        Iterator sitter = iterator();
        String[] outray = new String[size];
        int counter = 0;

        while (sitter.hasNext()) {
            outray[counter++] = (String) sitter.next();
        }

        return outray;
    }

    /**
     * Removes all of the elements from this collection (optional operation).
     * The collection will be empty after this method returns.
     *
     * @throws UnsupportedOperationException if the {@code clear} operation
     *                                       is not supported by this collection
     */
    public void clear() {
        backingStore = new String[DEFAULT_INITIAL_CAPACITY];
        size = 0;
        front = 0;
        back = 0;
    }

    /**
     * Retrieves, but does not remove, the head of this queue,
     * or returns {@code null} if this queue is empty.
     *
     * @return the head of this queue, or {@code null} if this queue is empty
     */
    public Object peek() {
        return backingStore[front];
    }
}
