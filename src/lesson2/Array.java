package lesson2;

public interface Array<E> {

    int INITIAL_CAPACITY = 4;

    void add(E value);

    E get(int index);

    boolean remove(E value);
    E remove(int index);

    default  boolean contains(E value) {
        return indexOf(value) != -1;
    }

    int indexOf(E value);

    int size();

    default boolean isEmpty() {
        return size() == 0;
    }

    default void display() {
        System.out.print("[");
        for (int i = 0; i < size(); i++) {
            E value = get(i);
            if (i < size() - 1) {
                System.out.print(value + ", ");
            }
            else {
                System.out.print(value);
            }
        }
        System.out.println("]");
    }

    void copyArray(Array<E> dataNew);

    void sortBubble(boolean wasChangedMode);
    void sortSelect();
    void sortInsert();

}
