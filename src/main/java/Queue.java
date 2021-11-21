import java.util.PriorityQueue;
import java.util.Scanner;


public class Queue {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int queueLength = in.nextInt();
        int officesNum = in.nextInt();

        PriorityQueue<Integer> queue = new PriorityQueue<>();

        for (int index = 0; index < queueLength; ++index) {
            int newClient = in.nextInt();
            if (queue.size() != officesNum) {
                queue.add(newClient);
            } else {
                queue.add(queue.poll() + newClient);
            }
        }

        while (queue.size() > 1) {
            queue.poll();
        }
        System.out.println(queue.poll());
    }
}
