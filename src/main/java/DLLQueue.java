import java.util.PriorityQueue;
import java.util.Scanner;


public class DLLQueue {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        PriorityQueue<QItem> queue = new PriorityQueue<>();
        PriorityQueue<QItem> queueReversed = new PriorityQueue<>(java.util.Collections.reverseOrder());

        while (in.hasNextLine()) {
            int command = in.nextInt();
            if (command == 0) {
                break;
            }
            else if (command == 1) {
                pushItem(in.nextInt(), in.nextInt(), queue, queueReversed);
            }
            else if (command == 2) {
                System.out.println(popMaxPrior(queue, queueReversed));
            }
            else if (command == 3) {
                System.out.println(popMinPrior(queue, queueReversed));
            }
        }
    }

    public static void pushItem(int value, int priority, PriorityQueue<QItem> queue, PriorityQueue<QItem> queueReversed) {
        queue.add(new QItem(value, priority));
        queueReversed.add(new QItem(value, priority));
    }

    public static int popMaxPrior(PriorityQueue<QItem> queue, PriorityQueue<QItem> queueReversed) {
        QItem popped;
        try {
            popped = queueReversed.poll();
            queue.remove(popped);
            return popped.value;
        } catch (NullPointerException exception) {
            return 0;
        }
    }

    public static int popMinPrior(PriorityQueue<QItem> queue, PriorityQueue<QItem> queueReversed) {
        QItem popped;
        try {
            popped = queue.poll();
            queueReversed.remove(popped);
            return popped.value;
        } catch (NullPointerException exception) {
            return 0;
        }
    }

    public static class QItem implements Comparable<QItem> {
        public int value;
        public int priority;

        public QItem(int value, int priority) {
            this.value = value;
            this.priority = priority;
        }

        @Override
        public int compareTo(QItem anotherItem) {
            return Integer.compare(this.priority, anotherItem.priority);
        }

        @Override
        public boolean equals(Object anotherItem) {
            return this.value == ((QItem) anotherItem).value;
        }
    }
}
