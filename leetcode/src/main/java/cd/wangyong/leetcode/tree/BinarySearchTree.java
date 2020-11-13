package cd.wangyong.leetcode.tree;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.EmptyStackException;
import java.util.List;
import java.util.Objects;

/**
 * @author wangong
 */
public class BinarySearchTree<T extends Comparable<T>> {

    static class Node<T> {
        private T key;
        private Object value;
        private Node<T> left;
        private Node<T> right;
        private Node<T> parent;

        public Node(T key) {
            this.key = key;
        }

        public T getKey() {
            return key;
        }

        @Override
        public String toString() {
            return "{"
                + "key=" + getKey()
                + "}";
        }
    }

    private Node<T> root;
    private int size;

    private BinarySearchTree() {
    }

    public static <T extends Comparable<T>> BinarySearchTree<T> build(T[] keys) {
        BinarySearchTree<T> tree = new BinarySearchTree<T>();

        for (T key : keys) {
            Node<T> node = new Node<>(key);
            tree.insert(node);
        }
        return tree;
    }

    public static <T extends Comparable<T>> BinarySearchTree<T> build(List<T> keys) {
        BinarySearchTree<T> tree = new BinarySearchTree<T>();

        for (T key : keys) {
            Node<T> node = new Node<>(key);
            tree.insert(node);
        }
        return tree;
    }

    public void insert(Node<T> z) {
        Node<T> x = this.root;
        Node<T> y = null;

        // data_structure.tree
        while (x != null) {
            y = x;
            int result = z.key.compareTo(x.key);
            if (result < 0) {
                x = x.left;
            }
            else {
                x = x.right;
            }
        }

        // insert
        z.parent = y;
        if (y == null) {
            this.root = z;
        }
        else if (z.key.compareTo(y.key) < 0) {
            y.left = z;
        }
        else {
            y.right = z;
        }
        this.size++;
    }

    public void addRecursive(Node<T> node) {
        addRecursive(node, null, this.root);
    }

    private void addRecursive(Node<T> node, Node<T> parent, Node<T> child) {
        if (child != null) {
            int result = node.key.compareTo(child.key);
            if (result < 0) {
                addRecursive(node, child, child.left);
            }
            else {
                addRecursive(node, child, child.right);
            }
            return;
        }

        node.parent = parent;
        if (parent == null) {
            this.root = node;
        }
        else if (node.key.compareTo(parent.key) <= 0) {
            parent.left = node;
        }
        else {
            parent.right = node;
        }
        this.size++;
    }

    public Node<T> delete(Node<T> node) {
        Objects.requireNonNull(node, "node is null");

        Node<T> y = null;
        Node<T> x = null;

        // 1.ȷ����ɾ�����y
        if (node.left == null || node.right == null) {
            y = node;
        }
        else {
            y = successor(node);
        }

        if (y.left != null) {
            x = y.left;
        }
        else {
            x = y.right;
        }
        if (x != null) {
            x.parent = y.parent;
        }

        if (y.parent == null) {
            this.root = x;
        }
        else if (y.parent.left == y) {
            y.parent.left = x;
        }
        else {
            y.parent.right = x;
        }

        if (y != node) {
            node.key = y.key;
            node.value = y.value;
        }
        this.size--;
        return y;
    }

    public Node<T> search(T key) {
        return search(this.root, key);
    }

    public Node<T> search(Node<T> node, T key) {
        while (node != null && !node.key.equals(key)) {
            if (node.key.compareTo(key) > 0) {
                node = node.left;
            }
            else {
                node = node.right;
            }
        }
        return node;
    }

    public Node<T> searchRecursive(T key) {
        return searchRecursive(this.root, key);
    }

    /**
     * �ݹ�ʵ��
     */
    public Node<T> searchRecursive(Node<T> node, T key) {
        if (node == null || node.key.compareTo(key) == 0) {
            return node;
        }

        if (node.key.compareTo(key) > 0) {
            return searchRecursive(node.left, key);
        }
        else {
            return searchRecursive(node.right, key);
        }
    }

    public Node<T> minimum() {
        return minimum(this.root);
    }

    public Node<T> minimum(Node<T> node) {
        Objects.requireNonNull(node);
        while (node.left != null) {
            node = node.left;
        }
        return node;
    }

    public Node<T> maxmum() {
        return minimum(this.root);
    }

    public Node<T> maxmum(Node<T> node) {
        Objects.requireNonNull(node, "input node is null.");
        while (node.right != null) {
            node = node.right;
        }
        return node;
    }

    public Node<T> predecessor(Node<T> node) {
        Objects.requireNonNull(node, "input node is null.");
        if (node.left != null) {
            return maxmum(node.left);
        }

        Node<T> p = node.parent;
        while (p != null && p.left == node) {
            node = p;
            p = p.parent;
        }
        return p;
    }

    public Node<T> successor(Node<T> node) {
        Objects.requireNonNull(node, "input node is null.");

        if (node.right != null) {
            return minimum(node.right);
        }

        Node<T> p = node.parent;
        while (p != null && p.right == node) {
            node = p;
            p = p.parent;
        }
        return p;
    }


    class Stack<E> {

        private static final int DEFAULT_INITIAL_CAPACITY = 16;

        private E[] elements;
        private int size = 0;

        @SuppressWarnings("unchecked")
        Stack() {
            elements = (E[]) new Object[DEFAULT_INITIAL_CAPACITY];
        }

        private void ensureCapacity() {
            if (elements.length == size) {
                elements = Arrays.copyOf(elements, 2 * size + 1);
            }
        }

        void push(E node) {
            ensureCapacity();
            elements[size++] = node;
        }

        E pop() {
            if (size == 0) {
                throw new EmptyStackException();
            }
            E result = elements[--size];
            elements[size] = null;
            return result;
        }

        boolean isNotEmpty() {
            return size > 0;
        }

    }

    public List<Node<T>> inOrderWalk() {
        List<Node<T>> list = new ArrayList<>(this.size);
        inOrderWalk(list, this.root);
        return list;
    }

    private void inOrderWalk(List<Node<T>> list, Node<T> node) {
        if (node == null) {
            return;
        }

        Stack<Node<T>> stack = new Stack<>();

        while (node != null) {
            stack.push(node);
            Node<T> cursor = node.left;
            while (cursor != null) {
                stack.push(cursor);
                cursor = cursor.left;
            }

            while (stack.isNotEmpty()) {
                Node<T> element = stack.pop();
                list.add(element);

                node = element.right;
                if (node != null) {
                    break;
                }
            }
        }
    }

    public List<Node<T>> inOrderWalkRecursive() {
        List<Node<T>> list = new ArrayList<>(this.size);
        inOrderWalkRecursive(list, this.root);
        return list;
    }

    private void inOrderWalkRecursive(List<Node<T>> list, Node<T> node) {
        if (node != null) {
            inOrderWalkRecursive(list, node.left);
            list.add(node);
            inOrderWalkRecursive(list, node.right);
        }
    }
}
