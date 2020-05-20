package lesson3.queue;

public class TestDeque {

    public static void main(String[] args) {
        Deque<Integer> deque = new DequeImpl<>(5);

        System.out.println(deque.insertLeft(1));
        System.out.println(deque.insertLeft(2));
        System.out.println(deque.insertRight(3));
        System.out.println(deque.insertLeft(4));
        System.out.println(deque.insertRight(5));
        System.out.println(deque.insertRight(6));

        System.out.println("Deque peek Front: " + deque.peekFront());
        System.out.println("Deque peek Tail: " + deque.peekTail());
        System.out.println("Deque size: " + deque.size());
        System.out.println("Deque is full: " + deque.isFull());

        while (!deque.isEmpty()) {
            //System.out.print(deque.removeRight() + ", ");
            System.out.print(deque.removeLeft() + ", ");
        }

    }
}
