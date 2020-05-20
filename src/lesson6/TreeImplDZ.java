package lesson6;

public class TreeImplDZ<E extends Comparable<? super E>> extends TreeImpl<E> {

    private int level;

    @Override
    public NodeAndItsParent doFind(E value) {
        Node<E> parent = null;
        Node<E> current = root;
        level = 1;

        while (current != null) {
            if (current.getValue().equals(value)) {
                return new NodeAndItsParent(current, parent, level);
            }

            parent = current;
            level++;

            if (value.compareTo(current.getValue()) > 0) {
                current = current.getRightChild();
            } else {
                current = current.getLeftChild();
            }
        }
        return new NodeAndItsParent(null, parent, level);
    }

    @Override
    public boolean add(E value) {
        Node<E> newNode = new Node<>(value);
        if (isEmpty()) {
            root = newNode;
            size++;
            return true;
        }

        Node<E> parent = findParentForNewNode(value);
        Node<E> current = findCurrentForNewNode(value);

        int level = findLevelForNewNode(value);


            if (parent == null || current != null || level > 4) {
                return false;
            }
            else if (value.compareTo(parent.getValue()) > 0) {
                parent.setRightChild(newNode);
            } else {
                parent.setLeftChild(newNode);
            }

            size++;
            return true;
    }

    private int findLevelForNewNode(E value) {
        return doFind(value).level;
    }

}
