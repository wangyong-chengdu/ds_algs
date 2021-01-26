package cd.wangyong.leetcode.数据结构.树;

import static cd.wangyong.leetcode.数据结构.树.RedBlackTree.Color.BLACK;
import static cd.wangyong.leetcode.数据结构.树.RedBlackTree.Color.RED;

/**
 * @author wangyong
 * @since 2020/03/04
 */
public class RedBlackTree<T extends Comparable<T>> {

    enum Color {
        BLACK, RED;
    }

    static class Node<T> {
        private T key;
        private Color color;
        private Object value;
        private Node<T> left;
        private Node<T> right;
        private Node<T> parent;

        public Node(T key) {
            this.key = key;
            this.color = RED;
        }

        public Node(T key, Color color) {
            this.key = key;
            this.color = color;
            this.left = this.right = this.parent = NULL;
        }

        public Node<T> getUncle() {
            if (parent == NULL || parent.parent == NULL) {
                return NULL;
            }
            return this.parent.isOnLeft() ? this.parent.parent.right : this.parent.parent.left;
        }

        public Node<T> getSibling() {
            if (parent == NULL) {
                return NULL;
            }
            return this.isOnLeft() ? this.parent.right : this.parent.left;
        }

        public boolean isOnLeft() {
            return this == this.parent.left;
        }

        public boolean isOnRight() {
            return this == this.parent.right;
        }

        public Node<T> getGrandParent() {
            return this.parent.parent;
        }

        public void moveDownAnotherUp(Node<T> nParent) {
            if (this.parent != NULL) {
                if (this.isOnLeft()) {
                    this.parent.left = nParent;
                }
                else {
                    this.parent.right = nParent;
                }
            }
            nParent.parent = this.parent;
            this.parent = nParent;
        }

        public boolean hasRedChild() {
            return (this.left != NULL && this.left.color == RED) || (this.right != NULL && this.right.color == RED);
        }

        public T getKey() {
            return key;
        }

        public Color getColor() {
            return color;
        }

        @Override
        public String toString() {
            return "{key=" + getKey() + ",color=" + getColor() + "}";
        }
    }

    private static Node NULL = new Node(null, BLACK);

    private Node root;

    private int size = 0;

    private void leftRotate(Node<T> x) {
        // new parent will be node's right child
        Node<T> nParent = x.right;

        // upate this.root if current node is root
        if (x == this.root) {
            this.root = nParent;
        }

        // x move down and nParent move up
        x.moveDownAnotherUp(nParent);

        // deal nParent's left child
        x.right = nParent.left;
        if (nParent.left != NULL) {
            nParent.left.parent = x;
        }

        // nParent is x's parent
        nParent.left = x;
    }

    private void rightRotate(Node<T> x) {
        // new parent will be x's left child
        Node<T> nParent = x.left;

        // deal this.root
        if (this.root == x) {
            this.root = nParent;
        }

        // x move down and nParent move up
        x.moveDownAnotherUp(nParent);

        // deal nParent's right child
        x.left = nParent.right;
        if (nParent.right != NULL) {
            nParent.right.parent = x;
        }

        // nParent is x's Parent
        nParent.right = x;
    }

    public void insert(Node<T> z) {
        Node<T> y = NULL;
        Node<T> x = this.root;

        // data_structure.tree
        while (x != NULL) {
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
        if (y == NULL) {
            this.root = z;
        }
        else if (z.key.compareTo(y.key) < 0) {
            y.left = z;
        }
        else {
            y.right = z;
        }

        z.left = z.right = NULL;
        z.color = RED;
        insertFixUp(z);

        this.size++;
    }

    private void insertFixUp(Node<T> z) {

        if (z.parent == NULL) {
            z.color = BLACK;
            return;
        }

        if (z.parent.color == BLACK) {
            return;
        }

        Node<T> uncle = z.getUncle();
        Node<T> grandParent = z.getGrandParent();

        if (uncle != NULL && uncle.color == RED) {
            z.parent.color = BLACK;
            uncle.color = BLACK;
            grandParent.color = RED;
            insertFixUp(grandParent);
            return;
        }

        if (z.parent == grandParent.left && z == z.parent.left) {
            rightRotate(grandParent);
            grandParent.color = RED;
            z.parent.color = BLACK;
            return;
        }

        if (z.parent == grandParent.left && z == z.parent.right) {
            leftRotate(z.parent);
            insertFixUp(z.left);
            return;
        }

        if (z.parent == grandParent.right && z == z.parent.right) {
            leftRotate(grandParent);
            grandParent.color = RED;
            z.parent.color = BLACK;
            return;
        }

        if (z.parent == grandParent.right && z == z.parent.left) {
            rightRotate(z.parent);
            insertFixUp(z.right);
        }
    }

    private void swapColors(Node<T> x1, Node<T> x2) {
        Color temp;
        temp = x1.color;
        x1.color = x2.color;
        x2.color = temp;
    }

    private void swapValues(Node<T> u, Node<T> v) {
        T tmpKey = u.key;
        Object tmpValue = u.value;
        u.key = v.key;
        u.value = v.value;
        v.key = tmpKey;
        v.value = tmpValue;
    }

    /**
     * ���(ָ�������һ��)
     */
    public Node<T> successor(Node<T> x) {
        if (x.right != NULL) {
            return minimum(x.right);
        }

        Node<T> p = x.parent;
        while (p != NULL && p.right == x) {
            x = p;
            p = p.parent;
        }
        return p;
    }

    /**
     * ǰ��
     */
    public Node<T> predecessor(Node<T> node) {
        if (node.left != NULL) {
            return maxmum(node.left);
        }
        Node<T> p = node.parent;
        while (p != NULL && p.left == node) {
            node = p;
            p = p.parent;
        }
        return p;
    }

    /**
     * ��Сֵ
     */
    public Node<T> minimum() {
        return minimum(this.root);
    }

    public Node<T> minimum(Node<T> node) {
        if (node == NULL) {
            return NULL;
        }

        while (node.left != null) {
            node = node.left;
        }
        return node;
    }

    /**
     * ���ֵ
     */
    public Node<T> maxmum() {
        return minimum(this.root);
    }

    public Node<T> maxmum(Node<T> node) {
        if (node == NULL) {
            return NULL;
        }
        while (node.right != NULL) {
            node = node.right;
        }
        return node;
    }

    /**
     * ���Ҵ�ɾ������������
     *
     * @param x ��ɾ�����
     * @return ������
     */
    private Node<T> findReplaceNode(Node<T> x) {
        // when node have 2 children��get right child's predecessor
        if (x.left != NULL && x.right != NULL) {
            return predecessor(x.right);
        }

        // when leaf
        if (x.left == NULL && x.right == NULL) {
            return NULL;
        }

        // when single child
        if (x.left != NULL) {
            return x.left;
        }
        else {
            return x.right;
        }
    }

    public Node<T> delete(Node<T> v) {
        Node<T> u = findReplaceNode(v);
        boolean isUVBlack = (u == NULL || u.color == BLACK) && (v == NULL || v.color == BLACK);

        // case1:u is null and v is root
        if (u == NULL && v == root) {
            this.root = NULL;
            this.size--;
            return v;
        }

        // u is NULL therefore v is leaf
        if (u == NULL) {
            // case2:u is null and [v is red] (in this branch v is rad, because u is NULL, u.color is black)
            if (!isUVBlack) {
                if (v.getSibling() != NULL) {
                    // sibling is not null,make id red
                    v.getSibling().color = RED;
                }
            }
            // case3:u is null and v is black
            else {
                fixDoubleBlack(v);
            }

            // delete v from the tree
            if (v.isOnLeft()) {
                v.parent.left = NULL;
            }
            else {
                v.parent.right = NULL;
            }
            this.size--;
            return v;
        }

        // uv are both not null
        // v has one child
        if (v.left == NULL || v.right == NULL) {
            // case4: v has only one child and v is root
            if (v == root) {
                swapValues(u, v);
                v.left = v.right = NULL;
                this.size--;
                return u;
            }

            // uv both not null and v has only one child and v is not root
            if (v.isOnLeft()) {
                v.parent.left = u;
            }
            else {
                v.parent.right = u;
            }
            u.parent = v.parent;
            // case5: uv both not null and v has only one child and v is not root and uv has one red
            if (!isUVBlack) {
                u.color = BLACK;
            }
            // case6: uv both not null and v has only one child and v is not root and uv two black
            else {
                fixDoubleBlack(u);
            }
            this.size--;
            return v;
        }

        // uv both not null and v has two children
        swapValues(u, v);
        return delete(u);
    }

    private void fixDoubleBlack(Node<T> x) {
        if (x == root) {
            return;
        }

        Node<T> sibling = x.getSibling();
        Node<T> parent = x.parent;
        // case1:no sibiling, double black pushed up
        if (sibling == NULL) {
            fixDoubleBlack(parent);
            return;
        }

        // case2: sibling red, rotation to normal pattern
        if (sibling.color == RED) {
            // recolor
            parent.color = RED;
            sibling.color = BLACK;
            // rotation
            if (sibling.isOnLeft()) {
                rightRotate(parent);
            }
            else {
                leftRotate(parent);
            }
            fixDoubleBlack(x);
            return;
        }

        // case3:sibling black and has red child
        if (x.getSibling().hasRedChild()) {
            if (sibling.left != NULL && sibling.left.color == RED) {
                // case3.1: Left Left
                if (sibling.isOnLeft()) {
                    sibling.left.color = sibling.color;
                    sibling.color = parent.color;
                    rightRotate(parent);
                }
                // case3.2:Right Left
                else {
                    sibling.left.color = parent.color;
                    rightRotate(sibling);
                    leftRotate(parent);
                }
            }
            else {
                // case3.3 Right Right
                if (sibling.isOnRight()) {
                    sibling.right.color = sibling.color;
                    sibling.color = parent.color;
                    leftRotate(parent);
                }
                // case3.4 Left Right
                else {
                    sibling.right.color = parent.color;
                    leftRotate(sibling);
                    rightRotate(parent);
                }
            }
            parent.color = BLACK;
        }
        // 2 black child
        else {
            sibling.color = RED;
            if (parent.color == BLACK) {
                fixDoubleBlack(parent);
            }
            else {
                parent.color = BLACK;
            }
        }

    }
}
