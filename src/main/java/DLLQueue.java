import java.util.Scanner;


public class DLLQueue {
    Node startingNode = null;
    Node endingNode = null;

    public static void main(String[] args) {
        DLLQueue q = new DLLQueue();
        Scanner in = new Scanner(System.in);

        while (in.hasNextLine()) {
            int command = in.nextInt();
            if (command == 0) {
                break;
            }
            else if (command == 2) {
                System.out.println(q.popMaxPrior());
            }
            else if (command == 3) {
                System.out.println(q.popMinPrior());
            }
            else {
                q.pushItem(in.nextInt(), in.nextInt());
            }
        }
    }

    public void pushItem(int value, int priority) {
        Node newNode = new Node(value, priority);

        if (startingNode == null) {
            endingNode = newNode;
            startingNode = newNode;
            return;
        }
        pushHelper(newNode);
    }

    private void pushHelper(Node newNode) {
        if (this.startingNode == null) {
            this.startingNode = newNode;
            this.endingNode = newNode;
        }
        else if (this.startingNode.priority <= newNode.priority) {
            newNode.next = this.startingNode;
            newNode.next.previous = newNode;
            this.startingNode = newNode;
        }
        else {
            Node current = this.startingNode;
            while (current.next != null && current.next.priority > newNode.priority) {
                current = current.next;
            }
            newNode.next = current.next;

            if (current.next != null) {
                newNode.next.previous = newNode;
            } else {
                this.endingNode = newNode;
            }
            current.next = newNode;
            newNode.previous = current;
        }
    }

    public int popMaxPrior() {
        if (this.startingNode == null) {
            return 0;
        }
        int value = this.startingNode.value;
        this.startingNode = this.startingNode.next;
        return value;
    }

    public int popMinPrior() {
        if (this.endingNode == null) {
            return 0;
        }
        int value = this.endingNode.value;
        this.endingNode.previous.next = null;
        this.endingNode = null;
        return value;
    }

    public void printQueue() {
        Node node = startingNode;
        System.out.println(node);

        while (node.next != null) {
            node = node.next;
            System.out.println(node);
        }
    }

    public static class Node {
        int value;
        int priority;
        Node previous = null;
        Node next = null;

        public Node(int value, int priority) {
            this.value = value;
            this.priority = priority;
        }

        public String toString() {
            return String.format("(%d: %d)%n", this.value, this.priority);
        }
    }
}
