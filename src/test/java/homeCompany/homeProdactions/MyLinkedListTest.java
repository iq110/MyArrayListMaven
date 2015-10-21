package homeCompany.homeProdactions;

import org.junit.Before;
import org.junit.Test;

import java.util.Iterator;
import java.util.ListIterator;

import static org.junit.Assert.*;

public class MyLinkedListTest {
    MyLinkedList<Integer> list;
    MyLinkedList<Integer> c;
    ListIterator<Integer> lIt;
    Iterator<Integer> it;

    @Before
    public void setup(){
        list  = new MyLinkedList<Integer>();
        for (int i = 0; i < 5; i++) {
            list.add(i);
        }
        lIt = list.listIterator();
        it = list.iterator();

        c = new MyLinkedList<Integer>();
        c.add(1);
        c.add(2);
        c.add(3);
        c.add(4);
    }

    @Test
    public void testSize() throws Exception {
        assertEquals(5,list.size());
    }

    @Test
    public void testIsEmpty() throws Exception {
        assertFalse(list.isEmpty());
        list = new MyLinkedList<Integer>();
        assertTrue(list.isEmpty());
    }

    @Test
    public void testContains() throws Exception {
        assertTrue(list.contains(4));
        assertFalse(list.contains(41));
    }

    @Test
    public void testIterator() throws Exception {
        assertTrue(it.hasNext());
        assertEquals(0, (int) it.next());
        it.next();
        it.next();
        it.next();
        it.next();
        assertFalse(it.hasNext());
    }

    @Test
    public void testToArray() throws Exception {
        for (int i = 0; i < list.size(); i++) {
            assertEquals(i,list.toArray()[i]);
        }
    }


    @Test
    public void testAdd() throws Exception {
        list.add(12);
        assertTrue(list.contains(12));
        assertFalse(list.contains(100));

    }

    @Test
    public void testRemove() throws Exception {
        list.remove(1);
        assertFalse(list.contains(1));
    }

    @Test
    public void testContainsAll() throws Exception {
        assertTrue(list.containsAll(c));
        c.add(32);
        assertFalse(list.containsAll(c));
    }


    @Test
    public void testAddAll1() throws Exception {
        list.addAll(2,c);
        assertEquals(1, (int) list.get(2));
        assertEquals(2, (int) list.get(3));
        assertEquals(3, (int) list.get(4));
        assertEquals(4, (int) list.get(5));
        assertEquals(2, (int)list.get(6));
    }

    @Test
    public void testRemoveAll() throws Exception {
        list.removeAll(c);
        assertFalse(list.contains(2));
        assertFalse(list.contains(3));
        assertFalse(list.contains(4));
        assertTrue(list.contains(0));
    }

    @Test
    public void testRetainAll() throws Exception {
        list.retainAll(c);
        assertTrue(list.contains(2));
        assertTrue(list.contains(3));
        assertTrue(list.contains(4));
        assertFalse(list.contains(0));
    }

    @Test
    public void testClear() throws Exception {
        list.clear();
        assertEquals(0,list.size());
    }

    @Test
    public void testGet() throws Exception {
        assertEquals(3, (int)list.get(3));
        list.add(111);
        assertEquals(111, (int)list.get(5));
    }

    @Test(expected = java.lang.IndexOutOfBoundsException.class)
    public void testSet() throws Exception {
        list.set(123,124);
        assertFalse(list.contains(124));
        list.set(3,123);
        assertEquals(123, (int)list.get(3));
    }

    @Test
    public void testAdd1() throws Exception {
        list = new MyLinkedList<Integer>();
        lIt = list.listIterator();
        lIt.add(81);
        assertTrue(list.contains(81));
    }

    @Test
    public void testRemove1() throws Exception {
        assertTrue(list.contains(0));
        lIt.next();
        lIt.remove();
        assertFalse(list.contains(0));
    }

    @Test
    public void testIndexOf() throws Exception {
        assertEquals(3, list.indexOf(3));
        assertEquals(-1, list.indexOf(222222312));
    }

    @Test
    public void testLastIndexOf() throws Exception {
        assertEquals(3, list.lastIndexOf(3));
        assertEquals(0, list.lastIndexOf(0));
        assertEquals(list.size()-1, list.lastIndexOf(list.size-1));
        list.add(3);
        assertEquals(5, list.lastIndexOf(3));
        assertEquals(-1, list.lastIndexOf(222222312));
    }

    @Test
    public void testListIterator() throws Exception {

        assertTrue(lIt.hasNext());
        lIt.next();
        assertEquals(1, (int)lIt.next());
    }

    @Test
    public void testListIterator1() throws Exception {
        lIt = list.listIterator(2);
        assertEquals(3, (int)lIt.next());

        lIt = list.listIterator(4);
        assertFalse(lIt.hasNext());
    }

    @Test
    public void testSubList() throws Exception {

        MyLinkedList<Integer> c = new MyLinkedList<Integer>();
        c = (MyLinkedList)list.subList(2,4);
        assertTrue(c.contains(2));
        assertTrue(c.contains(3));
        assertFalse(c.contains(5));
    }
}