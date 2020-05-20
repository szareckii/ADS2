package lesson4;


import java.util.Iterator;

public class LinkedIterator<E> implements Iterator<E> {

    LinkedList.Entry<E> current;

    public LinkedIterator(SimpleLinkedListImpl<E> list) {
        current = list.getHead();
    }

    @Override
    public boolean hasNext() {
        return current != null;
    }

    @Override
    public E next() {
        E data =  current.getData();
        current = current.getNext();

        return data;
    }

}
