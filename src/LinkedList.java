public class LinkedList {
    node head;

    LinkedList() {
        head = null;
    }

    public void add(int data) {
        node newNode = new node(data);
        newNode.next = head;
        head = newNode;
    }
}

