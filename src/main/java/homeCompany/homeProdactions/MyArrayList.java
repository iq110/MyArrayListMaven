package homeCompany.homeProdactions;

import java.util.*;

/**
 * Class created by analogy as ArrayList which is Java base
 * and has same methods
 * @param <E>
 */
public class MyArrayList<E> implements List<E> {

    protected int  startSize = 10;

    //real size of array, numbers of used cells
    protected int usedCells = 0;

    //main variable, capacity for stored objects
    protected Object[] array = new Object[startSize];

    public MyArrayList() {
        this(10);
    }

    public MyArrayList(int size) {
        if(size<0)
            this.startSize = 10;
        else this.startSize = size;
    }

    //Full size of array
    public int arraySize() {
        return array.length;
    }

    //Busy cells
    @Override
    public int size() {
        return usedCells;
    }

    //Return true if array has no used cells
    @Override
    public boolean isEmpty() {
        return this.usedCells == 0;
    }
    //if o!=null
    private boolean checkObjekt(Object o){
        return o != null;
    }

    //Has array some cell contains o
    //true - yes
    @Override
    public boolean contains(Object o) {
        if(checkObjekt(o))
        for (int i = 0; i < this.arraySize(); i++) {
            if(this.array[i]==(null))     //check by null
                return false;

            if (this.array[i].equals(o))    //check by equals
                return true;
        }
        return false;
    }


    //return new Iterator for
    @Override
    public Iterator<E> iterator() {
        return new MyIterator();
    }

    //Convert objectList to object[]
    @Override
    public Object[] toArray() {
        return array;
    }

    @Override
    public <T> T[] toArray(T[] a) {
        return null;
    }

    /**
     * increase real array size to it could fit needSize cells
     * @param needSize
     */
    private void increaseSize(int needSize){
        if(this.arraySize()- usedCells - needSize <= -1) {
            Object[] newArray = new Object[(this.array.length * 3) / 2];
            System.arraycopy(array, 0, newArray, 0, array.length);
            this.array = newArray;
            increaseSize(needSize);
        }
    }

    //Add new element to array
    @Override
    public boolean add(E e) {
        increaseSize(1);
        this.array[usedCells]=e;
        usedCells++;
        return true;
    }

    //Remove element from array
    @Override
    public boolean remove(Object o) {
        if(checkObjekt(o)) {
            Object[] newArray = new Object[this.arraySize() - 1];
            for (int i = 0; i < array.length; i++) {
                if (array[i].equals(o)) {
                    System.arraycopy(this.array, 0, newArray, 0, i);
                    System.arraycopy(this.array, i + 1, newArray, i, newArray.length - (i));
                    this.array = newArray;
                    usedCells--;
                    return true;
                }
            }
        }
        return false;
    }

    //Does array contains in itself sequence of elements c?
    @Override
    public boolean containsAll(Collection<?> c) {
        for (int i = 0; i < c.size(); i++) {
            if((c.toArray()[i]==null)&&(i>0))
                return true;
            if (!this.contains(c.toArray()[i]))
                return false;
        }
        return true;
    }

    //Add to the array all components of c
    @Override
    public boolean addAll(Collection<? extends E> c) {
        increaseSize(c.size());
        System.arraycopy(c.toArray(),0,this.array,usedCells,c.size());
        usedCells+=c.size();
        return true;
    }


    private boolean checkIndex(int index){
        if ((index>usedCells)||(index<0)||(this.arraySize()==0))
            return false;
        return true;
    }

    @Override
    public boolean equals(Object obj){
        MyArrayList<E> list = (MyArrayList<E>)obj;
        if(list.size() == this.size()){
            for (int i = 0; i < list.size(); i++)
                if(list.get(i)!=this.get(i))
                    return false;
            return true;
        }
        return false;
    }

    //Add to the array all components of c from index place
    @Override
    public boolean addAll(int index, Collection<? extends E> c) {
        if(checkIndex(index)) {
            increaseSize(c.size());
            Object[] result = new Object[this.arraySize()];
            System.arraycopy(this.array, 0, result, 0, index);//first part without c
            System.arraycopy(c.toArray(), 0, result, index, c.size());//first part with c
            System.arraycopy(this.array, index, result, index + c.size(), this.size() - index);
            this.array = result;
            usedCells += c.size();
            return true;
        }
        return false;
    }

    //Remove from array any component if c has same in itself
    @Override
    public boolean removeAll(Collection<?> c) {
        for (int i = 0; i < this.arraySize(); i++) {
            if(c.contains(this.array[i])) {
                this.remove(i);
                i--;
            }
        }
        return true;
    }

    //Remove from array any component if c hasn't same in itself
    @Override
    public boolean retainAll(Collection<?> c) {
                for (int i = 0; i < this.size(); i++) {
            if(!c.contains(this.array[i])) {
                remove(i);
                i--;
            }
        }
        return true;
    }

    //Clear array
    @Override
    public void clear() {
        this.array=new Object[10];
    }

    @Override
    public E get(int index) {
        if(checkIndex(index))
            return (E)this.array[index];
        return null;
    }

    @Override
    public E set(int index, E element) {
        if (!checkIndex(index))
            return null;
        this.add(index,element);
        this.remove(index+1);
        return element;
    }

    @Override
    public void add(int index, E element) {
        if (checkIndex(index)){
            increaseSize(1);
            Object[] result = new Object[this.arraySize()+1];
            System.arraycopy(this.array,0,result,0,index);
            result[index]=element;
            System.arraycopy(this.array,index,result,index+1,this.arraySize()-index);
            this.array=result;
            this.usedCells++;
        }
    }

    @Override
    public E remove(int index) {
        if(checkIndex(index)) {
            E removedElement = (E) this.array[index];
            Object[] newArray = new Object[this.arraySize() - 1];
            System.arraycopy(this.array, 0, newArray, 0, index);
            System.arraycopy(this.array, index + 1, newArray, index, newArray.length - (index));
            this.array = newArray;
            usedCells--;
            return removedElement;
        }
        return null;
    }

    /**
     *
     * @param o element
     * @return -2                -  o == null
     *         -1                -  array has no this o
     *          index of element -  if all right
     */
    @Override
    public int indexOf(Object o) {
        if (!checkObjekt(o))
            return -2;

        if(this.contains(o)){
            for (int i = 0; i < this.arraySize(); i++) {
                if(this.array[i].equals(o))
                    return i;
            }
        }
        return -1;
    }

    //Return las index of same object as o in array
    @Override
    public int lastIndexOf(Object o) {
        if (!checkObjekt(o))
            return -2;

        if(this.contains(o)){
            for (int i = this.size()-1; i > -1; i--) {
                if(this.array[i].equals(o))
                    return i;
            }
        }
        return -1;
    }

    @Override
    public ListIterator<E> listIterator() {
        return new MyListIterator(-1);
    }

    @Override
    public ListIterator<E> listIterator(int index) {
        return new MyListIterator(index);
    }

    @Override
    public List<E> subList(int fromIndex, int toIndex) {
        MyArrayList<E> newList = new MyArrayList<E>();
        Object[] newAray = new Object[toIndex-fromIndex];
        System.arraycopy(this.array,fromIndex,newAray,0,toIndex-fromIndex);
        newList.array = newAray;
        return newList;
    }

    /**
     * Class created by analogy as Iterator which is Java base
     * and has same methods
     */
    private class MyIterator implements Iterator<E>{

        protected int cursor = -1;

        @Override
        public boolean hasNext() {
            if(cursor >= usedCells-1)
                return false;
            return true;
        }

        @Override
        public E next() {
            cursor++;
            return (E)array[cursor];
        }

        @Override
        public void remove() {
            if(cursor>=0) {
                Object[] newArray = new Object[arraySize() - 1];
                System.arraycopy(array, 0, newArray, 0, cursor);
                System.arraycopy(array, cursor + 1, newArray, cursor, newArray.length - (cursor));
                array = newArray;
                usedCells--;
            }
        }
    }

    /**
     * Class created by analogy as ListIterator which is Java base
     * and has same methods
     */
    private class MyListIterator extends MyIterator implements ListIterator<E> {

        MyListIterator(int index){
            super();
               if((index >= array.length)||(index<=-1))
                   cursor=-1;
               else
                   cursor=index;
        }

        @Override
        public boolean hasNext() {
            if((cursor >= usedCells-1)||(cursor<-1))
                return false;
            return true;
        }

        @Override
        public E next() {
            cursor++;
            return (E)array[cursor];
        }

        @Override
        public boolean hasPrevious() {
            if((cursor >= usedCells)||(cursor<=0))
                return false;
            return true;
        }

        @Override
        public E previous() {
            cursor--;
            if(cursor<=-1)
                return null;
            return (E)array[cursor];
        }

        @Override
        public int nextIndex() {
            return cursor+1;
        }

        @Override
        public int previousIndex() {
            if(cursor<=-1){
                cursor = -1;
                return cursor;}
            return cursor-1;
        }

        @Override
        public void remove() {
            if(cursor>=0) {
                Object[] newArray = new Object[arraySize() - 1];
                System.arraycopy(array, 0, newArray, 0, cursor);
                System.arraycopy(array, cursor + 1,
                                 newArray, cursor,
                                 newArray.length - (cursor));
                array = newArray;
                usedCells--;
            }
        }

        @Override
        public void set(E e) {
            if(cursor>-1)
            if(checkObjekt(e))
                array[cursor]=e;
        }

        @Override
        public void add(E e) {
            if(checkObjekt(e)){
                increaseSize(1);
                array[usedCells]=e;
                usedCells++;
            }
        }
    }
}
