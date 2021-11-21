import java.util.Arrays;
import java.util.Scanner;
import java.util.Stack;

public class Replacement {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int length = in.nextInt();
        int[] array = new int[length];
        for (int index = 0; index < length; ++index) {
            array[index] = in.nextInt();
        }
        printArray(replaceArray(array, length));
    }

    public static int[] replaceArray(int[] array, int length) {
        Stack<Integer> stack = new Stack<>();
        int[] nextGreaterELem = new int[length];

        for (int index = length - 1; index >= 0; --index) {
            if (!stack.empty()) {
                while (!stack.empty() && stack.peek() <= array[index]) {
                    stack.pop();
                }
            }
            nextGreaterELem[index] = stack.empty() ? 0 : stack.peek();
            stack.push(array[index]);
        }
        return nextGreaterELem;
    }

    public static void printArray(int[] array) {
        for (int elem : array) {
            System.out.print(elem + " ");
        }
    }
}
