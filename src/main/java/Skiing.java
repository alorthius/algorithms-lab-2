import java.util.HashMap;
import java.util.Random;
import java.util.Scanner;


public class Skiing {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int length = in.nextInt();
        int[] path = new int[length];

        for (int index = 0; index < length; ++index) {
            path[index] = in.nextInt();
        }

//        int length = 100000;
//        Random rd = new Random();
//        int[] path = new int[length];
//
//        for (int i = 0; i < length; ++i) {
//            path[i] = rd.nextInt(2);
//        }
//
//        long start = System.currentTimeMillis();
//
        System.out.println(count(path));

//        long end =System.currentTimeMillis();
//        System.out.println((end - start) / 1000F);
    }

    public static int count(int[] path) {
        int pathLength = path.length;
        int maxLength = 0;
        int[] tabulation = new int[pathLength];

        int s = 0;
        for (int mainInd = 0; mainInd < pathLength; ++mainInd) {
            s += Math.pow(-1, path[mainInd]);
            tabulation[mainInd] = s;
//            tab.put(mainInd, s);
        }

        for (int mainInd = 0; mainInd < pathLength; ++mainInd) {

            for (int indToRight = pathLength - 1; indToRight > mainInd; --indToRight) {
                int sum;

                try {
                    sum = tabulation[indToRight] - tabulation[mainInd - 1];
//                    sum = tab.get(indToRight) - tab.get(mainInd - 1);
                }
//                catch (NullPointerException exception) {
//                    sum = tab.get(indToRight);
//                }
                catch (ArrayIndexOutOfBoundsException exception) {
                    sum = tabulation[indToRight];
                }

                if (sum == 0 && indToRight - mainInd + 1 > maxLength) {
                    maxLength = indToRight - mainInd + 1;
                    break;
                }
            }
            if (maxLength >= pathLength - mainInd ) {
                break;
            }
        }
        return maxLength;
    }
}
