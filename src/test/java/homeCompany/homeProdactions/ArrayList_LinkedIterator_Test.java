package homeCompany.homeProdactions;

import org.junit.Before;
import org.junit.Test;

import java.util.ListIterator;

import static org.junit.Assert.*;

public class ArrayList_LinkedIterator_Test {
    MyArrayList<Integer> list;
    ListIterator<Integer> it;

    @Before
    public void setup() {
        list = new MyArrayList<Integer>();
        for (int i = 0; i < 5; i++)
            list.add(i);
        it = list.listIterator();
    }


    @Test
    public void testHasNext() throws Exception {
        assertTrue(it.hasNext());
        for (int i = 0; i < 5; i++) {
            it.next();
        }
        assertFalse(it.hasNext());
    }

    @Test
    public void testNext() throws Exception {
        assertEquals(0,(int)it.next());
        assertEquals(1,(int)it.next());
    }

    @Test
    public void testHasPrevious() throws Exception {
        assertFalse(it.hasPrevious());
        it.next();
        it.next();
        assertTrue(it.hasPrevious());
    }

    @Test
    public void testPrevious() throws Exception {
        assertFalse(it.hasPrevious());
        it.next();
        it.next();
        assertTrue(it.hasPrevious());
    }

    @Test
    public void testNextIndex() throws Exception {
        assertEquals(0, it.nextIndex());
        it.next();
        assertEquals(1, it.nextIndex());
        it.previous();
        assertEquals(0, it.nextIndex());
    }

    @Test
    public void testPreviousIndex() throws Exception {
        assertEquals(-1,it.previousIndex());
        it.next();
        it.next();
        assertEquals(0,it.previousIndex());
    }

    @Test
    public void testRemove() throws Exception {
        it.next();
        it.remove();
        assertFalse(list.contains(0));
    }

    @Test
    public void testSet() throws Exception {
        it.next();
        it.set(222);
        assertEquals(222,(int)list.get(0));
    }

    @Test
    public void testAdd() throws Exception {
        it.add(128);
        assertTrue(list.contains(128));
    }
}