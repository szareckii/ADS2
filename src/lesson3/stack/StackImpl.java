package lesson3.stack;

public class StackImpl<E> implements Stack<E> {

    private E[] date;
    private int size;

    @SuppressWarnings("unchecked")
    public StackImpl(int maxSize) {
        this.date = (E[]) new Object[maxSize];
    }

    @Override
    public void push(E value) {
        date[size++] = value;
    }

    @Override
    public E pop() {
        return date[--size];
    }

    @Override
    public E peek() {
        return date[size - 1];
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isFull() {
        return size == date.length;
    }
}
