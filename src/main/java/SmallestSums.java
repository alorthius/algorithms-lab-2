import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.Scanner;


public class SmallestSums {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        while (in.hasNextLine()) {
            int arraysNum = Integer.parseInt(in.nextLine());
            int[][] matrix = new int[arraysNum][arraysNum];

            for (int index = 0; index < arraysNum; ++index) {
                String[] line = in.nextLine().split(" ");
                int[] array = new int[line.length];
                for (int ind = 0; ind < line.length; ++ind) {
                    array[ind] = Integer.parseInt(line[ind]);
                }
                Arrays.sort(array);
                matrix[index] = array;
            }
            int[] results = countResults(matrix, arraysNum);
            printMaxSums(results, arraysNum);
        }
    }

    public static class QItem implements Comparable<QItem> {
        public int index;
        public int sum;

        public QItem(int index, int sum) {
            this.index = index;
            this.sum = sum;
        }

        @Override
        public int compareTo(QItem anotherItem) {
            return Integer.compare(this.sum, anotherItem.sum);
        }
    }

    public static int[] countResults(int[][] matrix, int arraysNum) {
        int[] results = matrix[0];
        for (int index = 1; index < arraysNum; ++index) {
            countSum(matrix[0], matrix[index], results, arraysNum);
        }
        return results;
    }

    public static void printMaxSums(int[] results, int arraysNum) {
        for (int index = 0; index < arraysNum; ++index) {
            System.out.print(results[index] + " ");
        }
        System.out.println();
    }

    public static void countSum(int[] firstRow, int[] ithRow, int[] results, int arraysNum) {
        PriorityQueue<QItem> queue = new PriorityQueue<>();
        for (int index = 0; index < arraysNum; ++index) {
            queue.add(new QItem(0, firstRow[index] + ithRow[0]));
        }

        for (int index = 0; index < arraysNum; ++index) {
            QItem item = queue.peek();
            queue.poll();
            results[index] = item.sum;
            int ind = item.index;
            if (ind < arraysNum - 1) {
                queue.add(new QItem(ind + 1, item.sum - ithRow[ind] + ithRow[ind + 1]));
            }
        }
    }
}
