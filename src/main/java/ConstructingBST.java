import java.util.Scanner;

public class ConstructingBST {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int counter = 0;

        while(in.hasNextLine()) {
            String input = in.nextLine();
            if (input.isEmpty()) {
                break;
            }
            int nodesNum = Integer.parseInt(input.split(" ")[0]);
            int height = Integer.parseInt(input.split(" ")[1]);

            if (nodesNum == 0 && height == 0) {
                continue;
            }
            ++counter;
            System.out.printf("Case %d:", counter);
            buildTree(1, nodesNum, height);
            System.out.println();
        }
    }

    public static void buildTree(int node1, int node2, int height) {
        int node = node2 - node1 + 1;
        if (node == 0 || height == 0) {
            return;
        }
        int powerHeight = (int) Math.pow(2, height);
        if (node > 1 || height > 1) {
            if (node > (powerHeight - 1)) {
                System.out.print(" Impossible.");
                return;
            }
        }
        int leftNode = Math.max(node - powerHeight / 2, 0);
        System.out.print(" " + (node1 + leftNode));

        if (leftNode > 0) {
            buildTree(node1, node1 + leftNode - 1, height - 1);
        }
        buildTree(node1 + leftNode + 1, node2, height - 1);
    }
}
