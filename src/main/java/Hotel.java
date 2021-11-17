import java.util.Scanner;

public class Hotel {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int length = in.nextInt();
        int maxSum = in.nextInt();
        int[] values = new int[length];

        for (int index = 0; index < length; ++index) {
            values[index] = in.nextInt();
        }

        System.out.println(calcTotalValue(values, maxSum));
    }

    public static int calcTotalValue(int[] values, int maxSum) {
        int sum = 0;
        int totalSum = 0;
        int backwards = 0;
        for (int index = backwards; index < values.length; ++index) {
            sum += values[index];

            while (sum > maxSum) {
                sum -= values[backwards];
                ++backwards;

                if (totalSum <= sum && sum <= maxSum) {
                    totalSum = sum;
                }
            }
        }
        return totalSum;
    }
}
