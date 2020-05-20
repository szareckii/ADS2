package lesson3.queue;

public class QueueImpl<E> implements Queue<E> {

    protected static final int DEFAULT_TAIL = -1;
    protected static final int DEFAULT_HEAD = 0;

    protected final E[] data;
    protected int size;

    protected int tail;
    protected int head;

    @SuppressWarnings("unchecked")
    public QueueImpl(int maxSize) {
        this.data = (E[]) new Object[maxSize];
        this.tail = DEFAULT_TAIL;
        this.head = DEFAULT_HEAD;
    }

    @Override
    public boolean insert(E value) {
        if (isFull()) {
            return false;
        }

        if (tail == lastIndex()) {
            tail = DEFAULT_TAIL;
            return false;
        }

        data[++tail] = value;
        size++;
        return true;
    }

    protected int lastIndex() {
        return data.length - 1;
    }

    @Override
    public E remove() {
        if (isEmpty()) {
            return null;
        }
        if (head == data.length) {
            head = DEFAULT_HEAD;
        }

        E removeValue = data[head++];
        size--;
        return removeValue;
    }

    @Override
    public E peekFront() {
        return data[head];
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isFull() {
        return data.length == size;
    }
}
