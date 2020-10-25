package es.datastructur.synthesizer;
import org.junit.Test;
import static org.junit.Assert.*;

/** Tests the ArrayRingBuffer class.
 *  @author Josh Hug
 */

public class TestArrayRingBuffer {
    @Test
    public void someTest() {
        ArrayRingBuffer<Double> arb = new ArrayRingBuffer<>(10);
        arb.enqueue(10.3);
        arb.enqueue((double) 8);
        arb.enqueue(6.2);

        double exp = 10.3;
        double actual = arb.dequeue();
        assertEquals(exp,actual,1e-10);
        assertEquals(8.0,(double)arb.peek(),1e-10);

    }

    @Test
    public void equalTest() {
        ArrayRingBuffer<Double> arb1 = new ArrayRingBuffer<>(10);
        arb1.enqueue(10.3);
        arb1.enqueue(8.0);
        arb1.enqueue(6.2);

        ArrayRingBuffer<Double> arb2 = new ArrayRingBuffer<>(10);
        arb2.enqueue(10.3);
        arb2.enqueue(8.0);
        arb2.enqueue(6.2);

        assertTrue(arb1.equals(arb2));

    }

}
