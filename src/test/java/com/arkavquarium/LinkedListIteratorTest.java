import org.junit.Test;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertSame;
import com.arkavquarium.models.LinkedListIterator;

public class LinkedListIteratorTest {
    @Test
    public void testContentConstructor() {
        LinkedListIterator<Integer> it1 = new LinkedListIterator<Integer>(2);
        LinkedListIterator<Double> it2 = new LinkedListIterator<Double>(2.5);
        assertTrue("constructor should set content to arguments", it1.getContent() == 2);
        assertTrue("constructor should set content to arguments", it2.getContent() == 2.5);
    }

    @Test
    public void testNextPrevConstructor() {
        LinkedListIterator<Integer> it = new LinkedListIterator<Integer>(2);
        assertTrue("constructor should set prev to null", it.getPrev() == null);
        assertTrue("constructor should set next to null", it.getNext() == null);
    }

    @Test
    public void testContentSetter() {
        LinkedListIterator<Integer> it1 = new LinkedListIterator<Integer>(2);
        LinkedListIterator<Double> it2 = new LinkedListIterator<Double>(2.5);
        it1.setContent(3);
        it2.setContent(3.5);
        assertTrue("setContent set content to arguments", it1.getContent() == 3);
        assertTrue("setContent set content to arguments", it2.getContent() == 3.5);
    }
    
    @Test
    public void testGetterPrev() {
        LinkedListIterator<Integer> it1 = new LinkedListIterator<Integer>(2);
        LinkedListIterator<Integer> it2 = new LinkedListIterator<Integer>(3);
        it1.setPrev(it2);
        assertSame("setPrev set previous to other iterator", it2, it1.getPrev());
    }

    @Test
    public void testGetterNext() {
        LinkedListIterator<Integer> it1 = new LinkedListIterator<Integer>(2);
        LinkedListIterator<Integer> it2 = new LinkedListIterator<Integer>(3);
        it1.setNext(it2);
        assertSame("setNext set next to other iterator", it2, it1.getNext());
    }
}