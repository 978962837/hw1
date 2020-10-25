package es.datastructur.synthesizer;
import java.util.Iterator;


//TODO: Make sure to that this class and all of its methods are public
//TODO: Make sure to add the override tag for all overridden methods
//TODO: Make sure to make this class implement BoundedQueue<T>

public class ArrayRingBuffer<T> implements BoundedQueue<T>, Iterable<T> {
    /* Index for the next dequeue or peek. */
    private int first;
    /* Index for the next enqueue. */
    private int last;
    /* Variable for the fillCount. */
    private int fillCount;
    /* Array for storing the buffer data. */
    private T[] rb;

    /**
     * Create a new ArrayRingBuffer with the given capacity.
     */
    public ArrayRingBuffer(int capacity) {
        // TODO: Create new array with capacity elements.
        //       first, last, and fillCount should all be set to 0.
        rb = (T[]) new Object[capacity];
        fillCount = 0;
        first = 0;
        last = 0;
    }

    @Override
    public int capacity() {
        return rb.length;
    }

    @Override
    public int fillCount() {
        return fillCount;
    }

    /**
     * Adds x to the end of the ring buffer. If there is no room, then
     * throw new RuntimeException("Ring buffer overflow").
     */
    @Override
    public void enqueue(T x) {
        // TODO: Enqueue the item. Don't forget to increase fillCount and update
        //       last.
        if (isFull())
            throw new RuntimeException("Ring Buffer overflow");

        rb[last] = x;
        last = indexAdd(last);
        fillCount += 1;
    }

    /**
     * Dequeue oldest item in the ring buffer. If the buffer is empty, then
     * throw new RuntimeException("Ring buffer underflow").
     */
    @Override
    public T dequeue() {
        // TODO: Dequeue the first item. Don't forget to decrease fillCount and
        //       update first.
        if (isEmpty())
            throw new RuntimeException("Ring Buffer underflow");

        T item = rb[first];
        first = indexAdd(first);
        fillCount -= 1;
        return item;
    }

    /**
     * Return oldest item, but don't remove it. If the buffer is empty, then
     * throw new RuntimeException("Ring buffer underflow").
     */
    @Override
    public T peek() {
        // TODO: Return the first item. None of your instance variables should
        //       change.
        return rb[first];
    }

    // index add one
    private int indexAdd(int x) {
        return (x+1)%capacity();
    }

    @Override
    public Iterator<T> iterator() {
        return new ArrayRingBufferIterator();
    }

    // iterator class
    private class ArrayRingBufferIterator implements Iterator{
        int pos;

        private ArrayRingBufferIterator(){
            pos = 0;
        }

        @Override
        public boolean hasNext() {
            return pos < fillCount;
        }

        @Override
        public T next() {
            T item = rb[(first+pos)%capacity()];
            pos = pos+1;
            return item;
        }

    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (o == null)
            return false;

        // check if o is an ArrayRingBuffer
        if (o.getClass() != ArrayRingBuffer.class)
            return false;

        ArrayRingBuffer arb = (ArrayRingBuffer) o;

        // check if items in o is type T
        if (arb.peek().getClass() != this.peek().getClass())
            return false;

        // check length
        if (arb.fillCount()!=this.fillCount())
            return false;

        // elementwise checking
        Iterator thisIter = this.iterator();
        for (Object item:arb){
            if (!thisIter.next().equals(item))
                return false;
        }

        return true;

    }

    // TODO: When you get to part 4, implement the needed code to support
    //       iteration and equals.
}
    // TODO: Remove all comments that say TODO when you're done.
