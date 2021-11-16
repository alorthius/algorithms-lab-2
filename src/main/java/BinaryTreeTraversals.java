import java.util.Arrays;
import java.util.HashMap;
import java.util.Scanner;


public class BinaryTreeTraversals {
    int nodesNum;
    String[] preOrder;
    String[] inOrder;
    HashMap<String, Integer> dictionary = new HashMap<>();                  // hashmap to store element and its index
    String postOrder = "";

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int testsNum = in.nextInt();

        for (int num = 0; num < testsNum; ++num) {
            int nodesNum = in.nextInt();
            String[] orders = in.nextLine().split(" ");
            BinaryTreeTraversals tree = new BinaryTreeTraversals(nodesNum, orders[1].split(""), orders[2].split(""));
            System.out.println(tree.findPostOrder());
        }
    }

    public BinaryTreeTraversals(int nodesNum, String[] preOrder, String[] inOrder) {
        this.nodesNum = nodesNum;
        this.preOrder = preOrder;
        this.inOrder = inOrder;
    }

    public String toString() {
        return String.format("%d %s %s", this.nodesNum, Arrays.toString(this.preOrder), Arrays.toString(inOrder));
    }

    public String findPostOrder() {
        // fill the dictionary
        for (int index = 0; index < this.nodesNum; ++index) {
            this.dictionary.put(this.inOrder[index], index);
        }
        recurse(0, this.nodesNum - 1, 0, this.nodesNum - 1);
        return this.postOrder;
    }

    private void recurse(int preStart, int preEnd, int inStart, int inEnd) {
        if (inStart > inEnd || preStart > preEnd) {
            return;
        }
        String root = this.preOrder[preStart];
        int rootInd = this.dictionary.get(root);

        recurse(preStart + 1, preStart + rootInd - inStart, inStart, rootInd - 1);
        recurse(preStart + rootInd - inStart + 1, preEnd, rootInd + 1, inEnd);

        this.postOrder += root;
    }
}
