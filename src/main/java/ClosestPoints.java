import java.util.Arrays;
import java.util.Comparator;
import java.util.Scanner;


public class ClosestPoints {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int length = in.nextInt();
        Point[] points = new Point[length];

        for (int index = 0; index < length; ++index) {
            points[index] = new Point(in.nextInt(), in.nextInt());
        }

        Point[] minPoints = findMinPointsPair(points, length);

        System.out.printf("%d %d\n", minPoints[0].x, minPoints[0].y);
        System.out.printf("%d %d\n", minPoints[1].x, minPoints[1].y);
    }

    public static Point[] findMinPointsPair(Point[] points, int length) {
        Point[] pointsByX = new Point[length];
        Point[] pointsByY = new Point[length];

        // copy an original array into another two
        for (int index = 0; index < length; ++index) {
            pointsByX[index] = points[index];
            pointsByY[index] = points[index];
        }

        Arrays.sort(pointsByX, Comparator.comparing(point -> point.x));         // sort by x coordinate
        Arrays.sort(pointsByY, Comparator.comparing(point -> point.y));         // sort by y coordinate

        return recurse(pointsByX, pointsByY, length);                           // recursive search of points with minimal distance
    }

    // recursive method to find the points with minimal distance
    public static Point[] recurse(Point[] pointsByX, Point[] pointsByY, int length) {
        if (length <= 8) {                                  // use brute force for small number of points
            return bruteForce(pointsByX, length);
        }

        int middle = length / 2;
        Point midPoint = pointsByX[middle];

        // divide the points on 2 half with a middle point, and find per each part a pair of points with minimal distance
        Point[] pointsLeft = recurse(Arrays.copyOfRange(pointsByX, 0, middle), pointsByY, middle);
        Point[] pointsRight = recurse(Arrays.copyOfRange(pointsByX, middle, length), pointsByY, length - middle);

        Point[] minPoints = calcMinPoints(pointsLeft, pointsRight);             // choose one pair of points with minimal distance
        double minDist = calcDistance(minPoints[0], minPoints[1]);              // calculate their distance

        // find the points with distance close to the middle point
        Point[] localPoints = new Point[length];
        int lastPointInd = 0;
        for (int index = 0; index < length; ++index) {
            if (Math.abs(pointsByY[index].x - midPoint.x) < minDist) {
                localPoints[lastPointInd] = pointsByY[index];
                ++lastPointInd;
            }
        }
        return calcMinPoints(minPoints, localClosest(localPoints, lastPointInd, minDist));
    }

    // brute force algorithm to find the minimal distance and points
    public static Point[] bruteForce(Point[] points, int length) {
        double min = Double.MAX_VALUE;
        Point[] minPoints = new Point[2];

        for (int index_1 = 0; index_1 < length; ++index_1) {
            for (int index_2 = 0; index_2 < length; ++index_2) {
                if (index_1 == index_2) {
                    continue;
                }
                Point point1 = points[index_1];
                Point point2 = points[index_2];
                double distance = calcDistance(point1, point2);

                if (distance < min) {
                    min = distance;
                    minPoints[0] = point1;
                    minPoints[1] = point2;
                }
            }
        }
        return minPoints;
    }

    // return one pair of points of two with the minimal distance between
    public static Point[] calcMinPoints(Point[] points1, Point[] points2) {
        if (calcDistance(points1[0], points1[1]) <= calcDistance(points2[0], points2[1])) {
            return points1;
        }
        return points2;
    }

    // calculate the distance between two points
    public static double calcDistance(Point pointA, Point pointB) {
        if (pointA == null || pointB == null) {
            return Double.MAX_VALUE;
        }
        return Math.sqrt(Math.pow(pointB.x - pointA.x, 2) + Math.pow(pointB.y - pointA.y, 2));
    }

    // find the points from the local points array with the distance less than given
    public static Point[] localClosest(Point[] points, int length, double minDistance) {
        Point[] minPoints = new Point[2];

        int auxInd;
        for (int mainInd = 0; mainInd < length; ++mainInd) {
            auxInd = mainInd + 1;
            while ((auxInd < length) && (points[auxInd].y - points[mainInd].y) < minDistance) {
                minPoints[0] = points[mainInd];
                minPoints[1] = points[auxInd];
                minDistance = calcDistance(points[mainInd], points[auxInd]);
                ++auxInd;
            }
        }
        return minPoints;
    }

    // class to represent a single point
    public static class Point {
        int x;
        int y;

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }

        public String toString() {
            return String.format("(%d, %d)", this.x, this.y);
        }
    }
}
