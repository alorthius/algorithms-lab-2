import java.util.Scanner;

public class Skiing {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int length = in.nextInt();
        String i = in.nextLine();
        String[] input = in.nextLine().split(" ");
        System.out.println(findChunkLength(input, length));
    }

    public static int findChunkLength(String[] array, int length) {
        int ones = 0;

        for (int index = 0; index < length; ++index) {
            if (Integer.parseInt(array[index]) == 1) {
                ++ones;
            }
        }
        int zeros = length - ones;
        return Math.min(zeros, ones) * 2;
    }
}
