import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Queue;

import static org.junit.jupiter.api.Assertions.*;

class MyQueueTest {

    MyQueue myQueue;

    @BeforeEach
    void setUp() {
        myQueue = new MyQueue();
    }

    @Test
    void size_Normal() {
        prep();
        assertEquals(3, myQueue.size(), "size() is returning the wrong value");
    }

    @Test
    void size_Edge_FreshSet() {
        assertEquals(0, myQueue.size(), "Fresh set is not returning size() equals 0");
    }

    @Test
    void isEmpty_Normal() {
        prep();

        assertFalse(myQueue.isEmpty(), "isEmpty() returns true despite the Stack containing elements");

        for (int i = 0; i < 3; i++) {
            myQueue.dequeue();
        }

        assertTrue(myQueue.isEmpty(), "isEmpty() returns false despite the Stack containing no elements");
    }

    @Test
    void isEmpty_Edge_cleanedQueue(){
        prep();
        int limit = myQueue.size();
        for (int i = 0; i < limit; i++){
            myQueue.dequeue();
        }
        assertTrue(myQueue.isEmpty());
    }

    @Test
    void enqueue_Normal() {
        assertFalse(myQueue.contains("Poe"), "Values not enqueued are being found in the Queue");
        myQueue.enqueue("Poe");
        assertTrue(myQueue.contains("Poe"), "Values enqueued are not being found in the Queue");
    }

    @Test
    void enqueue_Edge_bigFill(){
        String valToAdd;

        // Adding a larger set of strings for testing
        for (int i = 0; i < 32; i++) {
            valToAdd = ((Integer) i).toString();
            myQueue.enqueue(valToAdd);
        }

        assertEquals(32, myQueue.size(), "size() is not being updated as we would expect");
        assertTrue(myQueue.contains("30"), "Element not found despite enqueuement");
    }

    @Test
    void dequeue_Normal() {
        String[] poem = new String[]{"Poe", "E.", "Near a Raven"};
        for (String word : poem){
            myQueue.enqueue(word);
        }

        for (int i = 0; i < 3; i++) {
            assertEquals(poem[i], myQueue.dequeue(), "Elements not dequeued in the expected order");
        }
    }

    void prep() {
        myQueue.enqueue("Poe");
        myQueue.enqueue("E.");
        myQueue.enqueue("Near a Raven");
    }

}