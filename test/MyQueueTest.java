import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.NoSuchElementException;
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
    void isEmpty_Edge_cleanedQueue() {
        prep();
        int limit = myQueue.size();
        for (int i = 0; i < limit; i++) {
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
    void enqueue_Edge_bigFill() {
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
        for (String word : poem) {
            myQueue.enqueue(word);
        }

        for (int i = 0; i < 3; i++) {
            assertEquals(poem[i], myQueue.dequeue(), "Elements not dequeued in the expected order");
        }
    }

    @Test
    void dequeue_Edge_emptyDequeue() {
        prep();
        while (!myQueue.isEmpty()) {
            myQueue.dequeue();
        }

        assertThrows(IllegalStateException.class, myQueue::dequeue);
    }

    @Test
    void order_test() {
        String[] contra = new String[]{"Poe", "E.", "Near a Raven"};
        int counter = 0;

        for (String word : contra) {
            myQueue.enqueue(word);
        }

        for (String word : contra) {
            assertEquals(word, myQueue.dequeue());
        }
    }

    @Test
    void peek_Normal() {
        myQueue.enqueue("Poe");
        assertEquals("Poe", myQueue.peek(), "single el peek() failed");
        myQueue.enqueue("E.");
        assertEquals("Poe", myQueue.peek(), "Multi el peek() failed");

        assertEquals(2, myQueue.size(), "At a guess, peek() is changing the size. size() is failing after a peek()");
    }

    @Test
    void peek_Edge_againstDequeue() {
        prep();
        myQueue.dequeue();
        assertEquals(myQueue.peek(), myQueue.dequeue(), "peek() and dequeue() are finding different elements");
        assertNotEquals(myQueue.dequeue(), myQueue.peek(), "peek() is finding the same element as the previous dequeue()");
    }

    void prep() {
        myQueue.enqueue("Poe");
        myQueue.enqueue("E.");
        myQueue.enqueue("Near a Raven");
    }

}