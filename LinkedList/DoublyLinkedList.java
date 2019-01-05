
public class DoublyLinkedList<E> {
    private Node<E> head;
    private Node<E> tail;
    private int size;

    DoublyLinkedList() {
        this.head = null;
        this.tail = null;
        size = 0;
    }

    public boolean add(E item) {
        add(size, item);
        return true;
    }

    //Scenario 1: Out of bounds
    //Scenario 2: Adding the very first item (not at the first location, meaning the size was previously 0
    //Scenario 3: Adding a new head
    //Scenario 4: Adding a new tail
    //Scenario 5: Any other case
    public void add(int index, E item) {
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException("ERROR");
        }

        Node<E> temp = new Node<E>(item);
        if (size == 0) {
            this.head = temp;
            this.tail = temp;
        } else if (index == 0) {
            temp.next = head;
            head.prev = temp;
            head = temp;

        } else if (index == size) {
            temp.prev = tail;
            tail.next = temp;
            tail = temp;
        } else {
            Node<E> before = this.getNode(index -1);
            temp.next = before.next;
            temp.prev = before;
            before.next = temp;
            temp.next.prev = temp;
        }

        size ++;
    }

    public E remove(int index) {
        E removed = null;
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Error");
        }

        if (size == 1) {
            removed = head.item;
            head = null;
            tail = null;
        } else if (index == 0) {
            removed = head.item;
            head = head.next;
            head.prev = null;
        } else if (index == size -1) {
            removed = tail.item;
            tail = tail.prev;
            tail.next = null;
        } else {
            Node<E> before = getNode(index -1);
            removed = before.next.item;
            before.next = before.next.next;
            before.next.prev = before;

        }

        size--;
        return removed;
    }

    private Node<E> getNode(int index) {
        Node<E> current = head;
        for (int i = 0; i < index ; i++) {
            current = current.next;
        }
        return current;
    }

    public String toString() {
        String output = "[ ";
        Node<E> current = head;
        while (current != null) {
            output += current.item.toString() + " ";
            current = current.next;
        }
        return output + "]";
    }

    public static void main(String[] args) {
        DoublyLinkedList<String> l = new DoublyLinkedList<>();
        l.add("a");
        l.add("b");
        l.add("c");

        System.out.println(l.remove(1));
        System.out.println(l.remove(0));
        System.out.println(l.remove(0));

        System.out.println(l);

    }

    private static class Node<E> {
        private E item;
        private Node<E> next;
        private Node<E> prev;

        public Node(E item) {
            this.item = item;

        }
    }
}
