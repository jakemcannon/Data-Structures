public class BinaryTree<E extends Comparable<E>> {
    private Node<E> root;
    private int size;

    public int size() {
        return this.size;
    }

    public void add(E item) {
        this.root = add(this.root, item);
    }

    public Node<E> add(Node<E> root, E item) {
        if (root == null) {
            return new Node<E>(item);
        }
        int compareResult = item.compareTo(root.item);
        if (compareResult == 0) {
            root.left = add(root.left, item);
            return root;
        } else if (compareResult < 0) {
            root.left = add(root.left, item);
            return root;
        } else {
            root.right = add(root.right, item);
            return root;
        }
    }

    public boolean contains(E item) {
        return contains(this.root, item);
    }

    public boolean contains(Node<E> root, E item) {
        if (root.item == null) {
            return false;
        } else {
            int compareResult = item.compareTo(root.item);
            if (compareResult == 0) {
                return true;
            } else if (compareResult < 0) {
                return contains(root.left, item);
            } else {
                return contains(root.right, item);
            }
        }
    }

    public String toString() {
        return toString(this.root);
    }

    private String toString(Node<E> root) {
        if (root == null) {
            return "";
        }
        String output = "";
        output += root.item + " ";
        output += toString(root.left);
        output += toString(root.right);
        return output;
    }

    public void remove(E item) {
        this.root = remove(this.root, item);
    }

    private Node<E> remove(Node<E> root, E item) {

        if (root == null) {
            return null;
        }

        int compareResult = item.compareTo(root.item);

        if (compareResult < 0) {
            root.left = remove(root.left, item);
            return root;
        } else if (compareResult > 0) {
            root.right = remove(root.right, item);
            return root;
        } else {
            if (root.left == null && root.right == null) {
                return null;
            } else if (root.left != null && root.right == null) {
                return root.left;
            } else if (root.left == null && root.right != null){
                return root.right;
            } else {
                Node<E> rootOfLeftSubtree = root.left;
                Node<E> parentOfPredecessor = null;
                Node<E> predecessor = null;

                if (rootOfLeftSubtree.right == null) {
                    root.item = rootOfLeftSubtree.item;
                    root.left = rootOfLeftSubtree.left;
                    return root;
                } else {
                    parentOfPredecessor = rootOfLeftSubtree;
                    Node<E> current = rootOfLeftSubtree.right;
                    while (current.right != null) {
                        parentOfPredecessor = current;
                        current = current.right;
                    }
                    predecessor = current;
                    root.item = predecessor.item;
                    parentOfPredecessor.right = predecessor.left;
                    return root;
                }
            }
        }
    }

    private static class Node<E extends Comparable<E>> {
        E item;
        Node<E> left;
        Node<E> right;

        public Node(E item) {
            this.item = item;
        }
    }

    public static void main(String[] args) {
        BinaryTree<Integer> tree = new BinaryTree<Integer>();
        tree.add(5);
        tree.add(1);
        tree.add(0);
        tree.add(2);

        tree.add(4);
        tree.add(3);

        tree.add(12);
        tree.add(7);
        tree.add(15);
        tree.add(14);
        tree.add(20);

        tree.remove(1);

        System.out.println(tree);

    }
}
