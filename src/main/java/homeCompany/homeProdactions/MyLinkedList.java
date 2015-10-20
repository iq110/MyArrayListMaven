package homeCompany.homeProdactions;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

/**
 * Class created by analogy as LinkedList which is Java base
 * and has same methods
 * @param <E>
 */
public class MyLinkedList<E> implements List<E> {

    //size of container
    int size = 0;

    //first and last element of container
    ListElement head = new ListElement();


    @Override
    public int size() {
        return this.size;
    }

    //if empty - return true
    @Override
    public boolean isEmpty() {
        if (this.size() != 0)
            return false;
        return true;
    }
    //check all items from thiss about comparison with o
    protected boolean compareAllElementsFromThiss(Object o, ListElement thiss){
        if(thiss!=head)
            if(!o.equals(thiss.value))
                return compareAllElementsFromThiss(o,thiss.nextElement);
            else return true;
        return false;
    }

    //has container as element as o
    @Override
    public boolean contains(Object o) {
        return compareAllElementsFromThiss(o, head.nextElement);
    }

    //return iterator of container
    @Override
    public Iterator<E> iterator() {
        return new MyListIterator();
    }

    //return next element after startElement
    protected ListElement getNextElement(ListElement startElement)
    {
        if(startElement!=head)
            return startElement.nextElement;
        return null;
    }

    //Convert objectList to object[]
    @Override
    public Object[] toArray() {
        Object [] mas = new Object[this.size()];
        ListIterator<E> it = this.listIterator();
        int i = 0;
        while(it.hasNext())
        {
            mas[i] = it.next();
            i++;
        }
        return mas;
    }

    @Override
    public <T> T[] toArray(T[] a) {
        return null;
    }

    //Add new element to array
    @Override
    public boolean add(E e) {
        if(size>0) {
            ListElement newEl = new ListElement(head.preElement, e , head);
            head.preElement.nextElement=newEl;
            head.preElement = newEl;
            size++;
            return true;
        }
        if(size==0){
            ListElement newEl = new ListElement(head, e , head);
            head.nextElement= newEl ;
            head.preElement = newEl;
            size++;
            return true;
        }
        return false;
    }

    protected ListElement getFirstElementWithValue(E value, ListElement startElem){
        if(startElem!=head)
            if(!startElem.value.equals(value))
                return getFirstElementWithValue(value,startElem.nextElement);
            else return startElem;
        return null;
    }

    //remove from container first element with values o
    @Override
    public boolean remove(Object o) {
        ListElement el = getFirstElementWithValue((E)o, head);
        if(el!=null) {
            el.preElement.nextElement = el.nextElement;
            el.nextElement.preElement = el.preElement;
            this.size--;
            return true;
        }
        return false;
    }

    //Does array contains in itself sequence of elements c?
    @Override
    public boolean containsAll(Collection<?> c) {
        ListIterator<E> it = (ListIterator<E>) c.iterator();
        while(it.hasNext()){
            if(!this.contains(it.next()))
                return false;
        }
        return true;
    }

    //Add to the array all components of c
    @Override
    public boolean addAll(Collection<? extends E> c) {
        if(!c.isEmpty()){
            for (int i = 0; i < c.size(); i++) {
                this.add((E)c.toArray()[i]);
            }
            this.size+=c.size();
            return true;
        }
        return false;
    }

    //Add to the array all components of c from index place
    @Override
    public boolean addAll(int index, Collection<? extends E> c) {
        if(!c.isEmpty()) {
            ListIterator<E> it = (ListIterator<E>) c.iterator();
            while (it.hasNext()) {
                this.add(index, it.next());
                index++;
            }
            this.size+=c.size();
            return true;
        }
        return false;
    }

    //Remove from array any component if c has same in itself
    @Override
    public boolean removeAll(Collection<?> c) {
        if(!c.isEmpty()) {
            ListIterator<E> it = this.listIterator();
            while (it.hasNext()) {
                if (c.contains(it.next())) {
                    it.remove();
                    this.size--;
                }
            }
            return true;
        }
        return false;
    }

    //Remove from array any component if c hasn't same in itself
    @Override
    public boolean retainAll(Collection<?> c) {
        if(!c.isEmpty()) {
            ListIterator<E> it = this.listIterator();
            while (it.hasNext()) {
                if (!c.contains(it.next())) {
                    it.remove();
                    this.size--;
                }
            }
            return true;
        }
        return false;
    }

    //Clear container
    @Override
    public void clear() {
        head.preElement = null;
        head.nextElement = null;
        size=0;
    }

    @Override
    public E get(int index) {
        if(checkIndex(index))
            return getIndexElement(index, head.nextElement, 0).value;
        return null;
    }

    @Override
    public E set(int index, E element) {
        if(checkIndex(index))
            return getIndexElement(index, head.nextElement, 0).value = element;
        return null;
    }

    @Override
    public void add(int index, E element) {
        if(checkIndex(index)){
            ListElement el = getIndexElement(index,head.nextElement,0);
            ListElement newEl = new ListElement(el.preElement, element, el);
            el.preElement.nextElement = newEl;
            el.preElement = newEl;
            this.size++;
        }
    }

    protected boolean checkIndex(int index){
            return (index<this.size) && (index>=0);
    }
    protected ListElement getIndexElement(int index, ListElement startEl, int counter){
        if(startEl!=head)
            if(counter!=index)
                return getIndexElement(index, startEl.nextElement, counter+1);
            else return startEl;
        return null;
    }

    @Override
    public E remove(int index) {
        if(checkIndex(index)){
            ListElement el = getIndexElement(index,head.nextElement,0);
            el.nextElement.preElement = el.preElement;
            el.preElement.nextElement = el.nextElement;
            this.size--;
            return el.value;
        }
        return null;
    }


    protected int getFirstIndexOfElement(Object value , ListElement startElem, int counter){
        if(startElem!=head)
            if(!startElem.value.equals(value))
                return getFirstIndexOfElement(value, startElem.nextElement, counter+1);
            else return counter;
        return -1;
    }

    protected boolean checkObject(Object o){
        return o!=null;
    }

    @Override
    public int indexOf(Object o) {
        if(checkObject(o)){
            return getFirstIndexOfElement(o,head.nextElement,0);
        }
        return -1;
    }

    protected int getLastIndexOfElement(Object value , ListElement startElem, int counter){
        if(startElem!=head)
            if(!startElem.value.equals(value))
                return getLastIndexOfElement(value, startElem.preElement, counter-1);
            else return counter;
        return -1;
    }

    @Override
    public int lastIndexOf(Object o) {
        if(checkObject(o)){
            return getLastIndexOfElement(o,head.preElement,size-1);
        }
        return -1;
    }

    @Override
    public ListIterator<E> listIterator() {
        return new MyListIterator();
    }

    @Override
    public ListIterator<E> listIterator(int index) {
        return new MyListIterator(index);
    }

    protected List<E> getSubList(ListElement start, ListElement finish, List<E> list){
        if(start!=head)
            if(start!=finish){
                list.add(start.value);
                return getSubList(start.nextElement,finish,list);
            }
            else return list;
        return null;
    }

    @Override
    public List<E> subList(int fromIndex, int toIndex) {
        if(checkIndex(fromIndex))
            if(checkIndex(toIndex)){
                MyLinkedList<E> subList = new MyLinkedList<E>();
                ListElement startEl = getIndexElement(fromIndex, head.nextElement, 0);
                ListElement finishEl = getIndexElement(toIndex, head.nextElement, 0);
                return getSubList(startEl,finishEl,subList);
                }
        return null;
    }

    /**
     * Element which one is based on MyLinkedList
     */
    protected class ListElement {
        ListElement preElement, nextElement;
        E value;

        ListElement() {

        }

        ListElement(ListElement pre, E val, ListElement next) {
            preElement = pre;
            value = val;
            nextElement = next;
        }

        @Override
        public boolean equals(Object o) {
            if (o != null) {
                ListElement el = (ListElement) o;
                if (this.value.equals(el.value))
                    return true;
            }
            return false;
        }

        //if o!=null
        private boolean checkObjekt(Object o) {
            return o != null;
        }


}

    /**
     * Class created by analogy as ListIterator which is Java base
     * and has same methods
     */
    protected class MyListIterator implements ListIterator<E> {

        ListElement element = head;
        int index = -1;

        MyListIterator(){
        }

        MyListIterator(int index){
            this.element = getIndexElement(index,head.nextElement,0);
            this.index = index;
        }

        @Override
        public boolean hasNext() {
            if ( element.nextElement == head )
                return false;
            return true;
        }

        @Override
        public E next() {
            index++;
            element = element.nextElement;
            return (E) element.value;
        }

        @Override
        public boolean hasPrevious() {
            if (element.preElement == head)
                return false;
            return true;
        }

        @Override
        public E previous() {
            if ((E) element.preElement != head)
                index--;
            return (E) element.preElement;
        }

        @Override
        public int nextIndex() {
            if (index + 1 <= size())
                return index + 1;
            return index;
        }

        @Override
        public int previousIndex() {
            if (index - 1 < 0)
                return index - 1;
            return index;
        }

        @Override
        public void remove() {
            ListElement pre = element.preElement;
            ListElement next = element.nextElement;
            pre.nextElement = next;
            next.preElement = pre;
            size--;
        }

        @Override
        public void set(E e) {
            this.element.value = e;
        }

        @Override
        public void add(E e) {
            if(size()>0) {
                ListElement newEl = new ListElement(null, e, null);

                //          pre |  el  |  next
                // index     1  |   2  |   3
                //          * 2 |  1 3 |  2 *
                ListElement next = element.nextElement;

                //          pre |  el  | newEl| next
                // index     1  |   2  |   5  |   3
                //          * 2 |  1 3 |  * 3 |  5 *
                next.preElement = newEl;
                newEl.nextElement = next;

                //          pre |  el  | newEl| next
                // index     1  |   2  |   5  |   3
                //          * 2 |  1 5 |  2 3 |  5 *
                element.nextElement = newEl;
                newEl.preElement = element;

                size++;
            }

            else {
                ListElement newEl = new ListElement(head, e, head);
                head.nextElement = newEl;
                head.preElement = newEl;
            }
        }
    }

}

