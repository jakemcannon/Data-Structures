public class LinkedList<E> {
    private Node<E> head;
    private int size;

    public LinkedList() {
        head = null;
        size = 0;
    }

    public int size() {
        return this.size;
    }

    public E get(int index) {
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException("Not a valid index");
        }
        return getNode(index).item;
    }

    public E set(int index, E item) {
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException("Not a valid index");
        }
        Node<E> node = getNode(index); //setter method is passed an index and item and this line calls getNode on index
        E toReturn = node.item;
        node.item = item;
        return toReturn;
    }

    public boolean add(E item) {
        this.add(size, item);
        return true;
    }

    public void add(int index, E item) {
        if (index < 0 || index > size) { //Scenario 1 index is out of bounds
            throw new IndexOutOfBoundsException("Not a valid index");
        }

        Node<E> temp = new Node<E>(item);
        if(index == 0) { //Scenario 2: adding a new head
            temp.next = head;
            head = temp;
        } else { //Scenario 3: everything else such as the middle or the end
            Node<E> before = getNode(index -1);
            temp.next = before.next;
            before.next = temp;
        }
        size++;
    }

    public E remove(int index) {
        E toReturn = null;
        if (index < 0 || index >= size) { //Scenario 1: out of bounds
            throw new IndexOutOfBoundsException("Can't remove that");
        }

        if (index == 0) { //Scenario 2: removing the head of the list
            toReturn = head.item;
            head = head.next;
        } else { //Scenario 3: deleting everything else
            Node<E> before = getNode(index - 1);
            toReturn = before.next.item;
            before.next = before.next.next;
        }
        size--;
        return toReturn;
    }

    private Node<E> getNode(int index) {
        Node<E> current = head;
        for (int i = 0; i < index ; i++) {
            current = current.next;
        }
        return current;
    }

    //prints the string obviously
    @Override
    public String toString() {
        String output = "[ ";
        Node<E> current = head;
        while (current != null) {
            output += current.item.toString() + " ";
            current = current.next;
        }
        return output + "]";
    }

    private static class Node<E> {
        E item;
        Node<E> next;

        public Node(E item){
            this.item = item;
        }

        public static void main(String[] args) {
            LinkedList<String> l = new LinkedList<>();
            l.add(0,"a");
            l.add(1,"b");
            l.add(2,"c");
            l.add(0,"d");
            l.add(2,"e");
            System.out.println(l);
            l.set(0, "j");
            System.out.println(l);
        }
    }
}
