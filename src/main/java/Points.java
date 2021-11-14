import java.util.Scanner;


public class Points {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int number = in.nextInt();
        int[][] points = new int[number][2];

        for (int count = 0; count < number; ++count) {
            points[count][0] = in.nextInt();
            points[count][1] = in.nextInt();
        }
        int[][] minPoints = findMinDistance(points, number);
        System.out.printf("%d %d\n", minPoints[0][0], minPoints[0][1]);
        System.out.printf("%d %d\n", minPoints[1][0], minPoints[1][1]);
    }

    public static double calcDistance(int[] pointA, int[] pointB) {
        return Math.sqrt(Math.pow(pointB[0] - pointA[0], 2) + Math.pow(pointB[1] - pointA[1], 2));
    }

    public static int[][] findMinDistance(int[][] points, int length) {
        double minDistance = Integer.MAX_VALUE;
        int[][] minPoints = new int[2][2];

        for (int index_1 = 0; index_1 < length; ++index_1) {
            for (int index_2 = 0; index_2 < length; ++index_2) {
                if (index_1 == index_2) {
                    continue;
                }
                double distance = calcDistance(points[index_1], points[index_2]);
                if (distance < minDistance) {
                    minDistance = distance;
                    minPoints[0] = points[index_1];
                    minPoints[1] = points[index_2];
                }
            }
        }
        return minPoints;
    }
}
