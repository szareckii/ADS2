package lesson3.queue;

public class DequeImpl<E> extends QueueImpl<E> implements Deque<E> {

    public DequeImpl(int maxSize) {
        super(maxSize);
    }

    @Override
    public boolean insertRight(E value) {
        if (isFull()) {
            return false;
        }

        if (head == DEFAULT_HEAD) {
            head = data.length;
        }

        data[--head] = value;
        size++;
        return true;
    }

    @Override
    public boolean insertLeft(E value) {
        return super.insert(value);
    }

    @Override
    public E removeLeft() {
        if (isEmpty()) {
            return null;
        }
        if (tail == DEFAULT_TAIL) {
            tail = data.length - 1;
        }

        E removeValue = data[tail--];
        size--;
        return removeValue;
    }

    @Override
    public E removeRight() {
        return super.remove();
    }

    @Override
    public E peekTail() {
        return data[tail];
    }
}