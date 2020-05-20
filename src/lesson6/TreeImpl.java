package lesson6;

import java.util.Stack;

public class TreeImpl<E extends Comparable<? super E>> implements Tree<E> {

    Node<E> root;
    int size;

    @Override//O(logN)
    public boolean add(E value) {
        Node<E> newNode = new Node<>(value);
        if (isEmpty()) {
            root = newNode;
            size++;
            return true;
        }

        Node<E> parent = findParentForNewNode(value);
        Node<E> current = findCurrentForNewNode(value);

        if (parent == null || current != null) {
            return false;
        }
        else if (value.compareTo(parent.getValue()) > 0) {
            parent.setRightChild(newNode);
        }
        else {
            parent.setLeftChild(newNode);
        }

        size++;
        return true;

    }

    @Override//O(logN)
    public boolean contains(E value) {
        return doFind(value).current != null;
    }

    @Override//O(logN)
    public boolean remove(E value) {
        NodeAndItsParent nodeAndItsParent = doFind(value);
        Node<E> removedNode = nodeAndItsParent.current;
        Node<E> parent = nodeAndItsParent.parent;

        if (removedNode == null) {
            return false;
        }

        if (removedNode.isLeaf()) {
            removeLeafNode(removedNode, parent);
        }
        else if (hasOnlySingleChildNode(removedNode)) {
            removeNodeWithSingleChild(parent, removedNode);
        }
        else {
            removeCommonNode(parent, removedNode);
        }

        size--;
        return true;
    }

    private void removeCommonNode(Node<E> parent, Node<E> removedNode) {
        Node<E> successor = getSuccessor(removedNode);
        if (removedNode == root) {
            root = successor;
        }
        else if (parent.getLeftChild() == removedNode) {
            parent.setLeftChild(successor);
        }
        else {
            parent.setRightChild(successor);
        }

        successor.setLeftChild(removedNode.getLeftChild());
    }

    private Node<E> getSuccessor(Node<E> removedNode) {
        Node<E> successor = removedNode;
        Node<E> successorParent = null;
        Node<E> current = removedNode.getRightChild();

        while (current != null) {
            successorParent = successor;
            successor = current;
            current = current.getLeftChild();
        }

        if (successor != removedNode.getRightChild() && successorParent != null) {
            successorParent.setLeftChild(successor.getRightChild());
            successor.setRightChild(removedNode.getRightChild());
        }
        return successor;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public void display() {
        Stack<Node<E>> globalStack = new Stack<>();
        globalStack.push(root);
        int nBlanks = 64;

        boolean isRowEmpty = false;
        System.out.println("................................................................");

        while (!isRowEmpty) {
            Stack<Node<E>> localStack = new Stack<>();

            isRowEmpty = true;
            for (int i = 0; i < nBlanks; i++) {
                System.out.print(" ");
            }

            while (!globalStack.isEmpty()) {
                Node<E> tempNode = globalStack.pop();
                if (tempNode != null) {
                    System.out.print(tempNode.getValue());
                    localStack.push(tempNode.getLeftChild());
                    localStack.push(tempNode.getRightChild());
                    if (tempNode.getLeftChild() != null || tempNode.getRightChild() != null) {
                        isRowEmpty = false;
                    }
                } else {
                    System.out.print("--");
                    localStack.push(null);
                    localStack.push(null);
                }
                for (int j = 0; j < nBlanks * 2 - 2; j++) {
                    System.out.print(" ");
                }
            }

            System.out.println();

            while (!localStack.isEmpty()) {
                globalStack.push(localStack.pop());
            }

            nBlanks /= 2;
        }
        System.out.println("................................................................");

    }

    @Override
    public void traverse(TraverseMode mode) {
        switch (mode) {
            case IN_ORDER:
                inOrder(root);
                break;
            case PRE_ORDER:
                preOrder(root);
                break;
            case POST_ORDER:
                postOrder(root);
                break;
            default:
                throw new IllegalArgumentException("Unknown value: " + mode);
        }
    }

    @Override
    public boolean isBal() {
        return isBalanced(root);
    }

    @Override
    public boolean isBalanced(Node<E> root) {
        return (root == null) ||
                isBalanced(root.getLeftChild()) &&
                        isBalanced(root.getRightChild()) &&
                        Math.abs(height(root.getLeftChild()) - height(root.getRightChild())) <= 1;
    }

    @Override
    public int height(Node<E> node) {
        return node == null ? 0 : 1 + Math.max(height(node.getLeftChild()), height(node.getRightChild()));
    }

    private void inOrder(Node<E> current) {
        if (current == null) {
            return;
        }
        inOrder(current.getLeftChild());
        System.out.println(current.getValue());
        inOrder(current.getRightChild());
    }

    private void preOrder(Node<E> current) {
        if (current == null) {
            return;
        }
        System.out.println(current.getValue());
        preOrder(current.getLeftChild());
        preOrder(current.getRightChild());
    }

    private void postOrder(Node<E> current) {
        if (current == null) {
            return;
        }
        postOrder(current.getLeftChild());
        postOrder(current.getRightChild());
        System.out.println(current.getValue());

    }

    private void removeNodeWithSingleChild(Node<E> parent, Node<E> removedNode) {
        Node<E> childNode = removedNode.getLeftChild() != null
                ? removedNode.getLeftChild()
                : removedNode.getRightChild();

        if (removedNode == root) {
            root = childNode;
        }
        else if (parent.getLeftChild() == removedNode) {
            parent.setLeftChild(childNode);
        }
        else {
            parent.setRightChild(childNode);
        }
    }

    private boolean hasOnlySingleChildNode(Node<E> removedNode) {
        return removedNode.getLeftChild() != null ^ removedNode.getRightChild() != null;
    }

    private void removeLeafNode(Node<E> removedNode, Node<E> parent) {
        if (parent == null) {
            root = null;
        }
        else if (parent.getLeftChild() == removedNode) {
            parent.setLeftChild(null);
        }
        else {
            parent.setRightChild(null);
        }
    }

    public Node<E> findParentForNewNode(E value) {
        return doFind(value).parent;
    }

    public Node<E> findCurrentForNewNode(E value) {
        return doFind(value).current;
    }

    public NodeAndItsParent doFind(E value) {
        Node<E> parent = null;
        Node<E> current = root;
        while (current != null) {
            if (current.getValue().equals(value)) {
                return new NodeAndItsParent(current, parent, 0);
            }

            parent = current;
            if (value.compareTo(current.getValue()) > 0) {
                current = current.getRightChild();
            }
            else {
                current = current.getLeftChild();
            }

        }
        return new NodeAndItsParent(null, parent, 0);
    }

    public class NodeAndItsParent {
        Node<E> current;
        Node<E> parent;
        int level;

        public NodeAndItsParent(Node<E> current, Node<E> parent, int level) {
            this.current = current;
            this.parent = parent;
            this.level = level;
        }
    }

}
