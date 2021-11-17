import java.util.Scanner;


public class Skiing {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int length = in.nextInt();
        int[] path = new int[length];

        for (int index = 0; index < length; ++index) {
            path[index] = in.nextInt();
        }
        System.out.println(count(path));
    }

    public static int count(int[] repetitions) {
        int maxLength = 0;
        for (int mainInd = 0; mainInd < repetitions.length; ++mainInd) {
            int sum = (int) Math.pow(-1, repetitions[mainInd]);
            int count = 1;

            for (int indToRight = mainInd + 1; indToRight < repetitions.length; ++indToRight) {
                sum += (int) Math.pow(-1, repetitions[indToRight]);
                count += 1;
                if (sum == 0 && count > maxLength) {
                    maxLength = count;
                }
            }
        }
        return maxLength;
    }
}
