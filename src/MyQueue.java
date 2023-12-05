/**
 * MyQueue represents a basic implementation of a circular queue using an array.
 * Elements are enqueued at the back and dequeued from the front.
 * The circular queue allows efficient use of space by wrapping around the array.
 */
public class MyQueue{
    private static final int DEFAULT_INITIAL_CAPACITY = 10;
    String[] backingStore;
    private int size = 0;
    private int front;
    private int back;

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
     * Enqueues a new element at the back of the queue.
     *
     * @param s The string to be enqueued.
     * @throws IllegalArgumentException if the queue is full.
     */
    public void enqueue(String s){
        //Todo find out how this should actually be handled
        if(size + 1 == Integer.MAX_VALUE)
            throw new IllegalArgumentException("may not add more values to this Stack");

        if(size == backingStore.length)
            refactor();

        back = (back + 1) % backingStore.length;
        size++;

        backingStore[back] = s;
    }

    /**
     * Dequeues an element from the front of the queue.
     *
     * @return The dequeued string.
     * @throws IllegalStateException if the queue is empty.
     */
    public String dequeue(){
        String returnVal = backingStore[front];
        front = (front + 1) % backingStore.length;
        size--;

        return returnVal;
    }

    /**
     * Returns an array representation of the elements in the queue.
     *
     * @return An array of strings representing the elements in the queue.
     */
    public String[] toArray(){
        String[] outray = new String[size];
        for (int i = 0; i < backingStore.length; i++) {
            outray[i] = backingStore[(i + front) % backingStore.length];
        }
        return outray;
    }

    /**
     * Refactors the backing store array to accommodate more elements.
     * Invoked when the current capacity is reached during an enqueue operation.
     */
    private void refactor(){
        String[] holder = backingStore;
        backingStore = new String[backingStore.length + DEFAULT_INITIAL_CAPACITY];
        for (int i = 0; i < backingStore.length; i++) {
            backingStore[i] = holder[(i + front) % backingStore.length];
        }
        front = 0;
        back = size;
    }
}
