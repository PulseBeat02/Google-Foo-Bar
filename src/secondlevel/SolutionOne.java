package secondlevel;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;

public class SolutionOne {

    public static void main(String[] args) {
        System.out.println(Arrays.toString(solution(3, new int[]{7, 3, 5, 1})));
    }

    public static int[] solution(int h, int[] q) {
        Node tree = createEmptyTree(h);
        colorTree(tree);
        int[] ans = new int[q.length];
        for (int i = 0; i < q.length; i++) {
            Queue<Node> queue = new ArrayDeque<>();
            queue.add(tree);
            boolean flag = false;
            while (queue.size() > 0) {
                Node current = queue.remove();
                if (current.value == q[i]) {
                    if (current.parent != null) {
                        ans[i] = current.parent.value;
                        flag = true;
                    }
                    break;
                }
                if (current.left != null && current.right != null) {
                    queue.add(current.left);
                    queue.add(current.right);
                }
            }
            if (!flag) {
                ans[i] = -1;
            }
        }

        return ans;

    }

    // Colors the Tree using Post-Order Traversal
    static int count = 1;
    private static void colorTree(Node current) {
        if (current == null) {
            return;
        }
        colorTree(current.left);
        colorTree(current.right);
        current.value = count;
        count++;
    }

    // Creates Empty Tree
    private static Node createEmptyTree(int height) {
        Node root = new Node(0, 1, null);
        Queue<Node> queue = new ArrayDeque<>();
        queue.add(root);
        Node n = queue.remove();
        while (n.height != height) {
            n.left = new Node(0, n.height + 1, n);
            n.right = new Node(0, n.height + 1, n);
            queue.add(n.left);
            queue.add(n.right);
            n = queue.remove();
        }
        return root;
    }

    // Node Class
    private static class Node {

        private int value;
        private final int height;
        private final Node parent;
        private Node left;
        private Node right;

        public Node(final int value,
                    final int height,
                    final Node parent) {
            this.value = value;
            this.height = height;
            this.parent = parent;
        }

    }


}
