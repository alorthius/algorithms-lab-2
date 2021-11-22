import java.util.Scanner;


public class KSorting {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int length = in.nextInt();
        int[] array = new int[length];
        for (int index = 0; index < length; ++index) {
            array[index] = in.nextInt();
        }
        int k = in.nextInt();
        System.out.println(findSwapsNum(array, length, k));
    }

    public static void swap(int[] array, int index1, int index2) {
        int temp = array[index1];
        array[index1] = array[index2];
        array[index2] = temp;
    }

    public static boolean isArraySorted(int[] array, int length) {
        for (int index = 1; index < length; ++index) {
            if (array[index - 1] > array[index]) {
                return false;
            }
        }
        return true;
    }

    public static int findSwapsNum(int[] array, int length, int k) {
        int swapsNum = 0;
        for (int index = 0; index < length; ++index) {
            for (int indFromK = k; indFromK < length; ++indFromK) {
                if (array[indFromK - k] > array[indFromK]) {
                    swap(array, indFromK - k, indFromK);
                    ++swapsNum;
                }
            }
        }
        return isArraySorted(array, length) ? swapsNum : -1;
    }
}
