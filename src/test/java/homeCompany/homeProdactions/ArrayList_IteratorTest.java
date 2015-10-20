package homeCompany.homeProdactions;

import org.junit.Before;
import org.junit.Test;

import java.util.Iterator;
import java.util.Random;

import static org.junit.Assert.*;

public class ArrayList_IteratorTest {
    MyArrayList<Integer> list;
    Iterator<Integer> it;

    @Before
    public void setup() {
        list = new MyArrayList<Integer>();
        for (int i = 0; i < 5; i++)
            list.add(i);
        it = list.iterator();
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
    public void testRemove() throws Exception {
        it.next();
        it.remove();
        assertFalse(list.contains(0));
    }
}