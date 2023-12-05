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
    boolean overFlowFlag;

    public MyQueue() {
        this(DEFAULT_INITIAL_CAPACITY);
    }

    MyQueue(int capacity) {
        if (capacity < 0) {
            throw new IllegalArgumentException("capacity cannot be negative");
        }

        backingStore = new String[capacity];
    }

    int size() {
        return size;
    }

    boolean isEmpty() {
        return size == 0;
    }

    public void enqueue(String s){
        //Todo find out how this should actually be handled
        if(size + 1 == Integer.MAX_VALUE)
            throw new IllegalArgumentException("may not add more values to this Stack");

        if(size() == backingStore.length)
            refactor();

        back = (back + 1) % backingStore.length;
        size++;

        backingStore[back] = s;
    }

    public String dequeue(){
        String returnVal = backingStore[front];
        front = (front + 1) % backingStore.length;
        size--;

        return returnVal;
    }

    String[] toArray(){
        String[] outray = new String[size];
        for (int i = 0; i < backingStore.length; i++) {
            outray[i] = backingStore[(i + front) % backingStore.length];
        }
        return outray;
    }

    void refactor(){
        String[] holder = backingStore;
        backingStore = new String[backingStore.length + DEFAULT_INITIAL_CAPACITY];
        for (int i = 0; i < backingStore.length; i++) {
            backingStore[i] = holder[(i + front) % backingStore.length];
        }
        front = 0;
        back = size;
    }
}
