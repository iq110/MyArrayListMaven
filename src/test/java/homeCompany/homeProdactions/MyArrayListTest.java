package homeCompany.homeProdactions;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class MyArrayListTest {
    MyArrayList<Integer> list,c;

    @Before
    public void setup() {
        list = new MyArrayList<Integer>();
        for (int i = 0; i < 5; i++) {
            list.add(i);
        }


        c = new MyArrayList<Integer>();
        for (int i = 2; i < 5; i++) {
            c.add(i);
        }
    }

    @Test
    public void testSize() throws Exception {

        assertEquals(5, list.size());
    }

    @Test
    public void testIsEmpty() throws Exception {
        list = new MyArrayList<Integer>(0);
        assertTrue(list.isEmpty());
    }

    @Test
    public void testContains() throws Exception {
        assertEquals(true, list.contains(3));
        assertEquals(false, list.contains(500));
    }

    @Test
    public void testIterator() throws Exception {
        assertEquals(true, list.iterator().hasNext());
        assertEquals(0, (int) list.iterator().next());
    }

    @Test
    public void testToArray() throws Exception {
        int[] mas = {0,1,2,3,4};
        for (int i = 0; i < mas.length; i++) {
            assertEquals(mas[i],list.toArray()[i]);
        }
    }

    @Test
    public void testAdd() throws Exception {
        list.add(555);
        assertTrue(list.contains(555));
    }

    @Test
    public void testRemove() throws Exception {
        list.remove(0);
        assertFalse(list.contains(0));
    }

    @Test
    public void testContainsAll() throws Exception {
        assertTrue(list.containsAll(c));
    }

    @Test
    public void testAddAll() throws Exception {
        MyArrayList<Integer> test = new MyArrayList<Integer>();
        test.addAll(list);
        assertEquals(list, test);
    }

    @Test
    public void testAddAll1() throws Exception {
        MyArrayList<Integer> test = new MyArrayList<Integer>();
        test.addAll(list);
        test.add(2);
        test.add(3);
        test.add(4);

        list.addAll(list.size(),c);

        assertEquals(test, list);
    }

    @Test
    public void testRemoveAll() throws Exception {
        list.removeAll(c);
        assertFalse(list.contains(4));
    }

    @Test
    public void testRetainAll() throws Exception {
        list.retainAll(c);
        assertFalse(list.contains(0));
    }

    @Test
    public void testClear() throws Exception {
        list.clear();
        assertFalse(list.contains(1));
    }

    @Test
    public void testGet() throws Exception {
        assertEquals(1, (int) list.get(1));
    }

    @Test
    public void testSet() throws Exception {
        list.set(0,1010);
        assertEquals(1010, (int) list.get(0));
    }

    @Test
    public void testAdd1() throws Exception {
        list.add(0,1010);
        assertEquals(1010, (int) list.get(0));
        assertEquals(5, list.size());
    }

    @Test
    public void testRemove1() throws Exception {
        list.remove(0);
        assertFalse(list.contains(0));
    }

    @Test
    public void testLastIndexOf() throws Exception {
        assertEquals(0, list.lastIndexOf(0));
    }

    @Test
    public void testListIterator() throws Exception {
        assertEquals(0, (int) list.listIterator().next());
    }

    @Test
    public void testListIterator1() throws Exception {
        assertEquals(4, (int) list.listIterator(3).next());
    }

    @Test
    public void testSubList() throws Exception {
        MyArrayList<Integer> test;
        test=(MyArrayList<Integer>)list.subList(1,3);
        assertTrue(test.contains(2));
    }
}