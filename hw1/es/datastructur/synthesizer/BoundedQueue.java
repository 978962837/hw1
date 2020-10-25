package es.datastructur.synthesizer;

public interface BoundedQueue<T> {
    // return size of the buffer
    int capacity();

    // return num of items in buffer
    int fillCount();

    // add item to the end
    void enqueue(T item);

    // remove item from the front
    T dequeue();

    // return item from front (without remove)
    T peek();

    // empty?
    default boolean isEmpty() {
        return fillCount() == 0;
    }

    // full?
    default boolean isFull() {
        return fillCount() == capacity();
    }
}
