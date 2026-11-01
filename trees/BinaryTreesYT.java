import java.util.LinkedList;
import java.util.Queue;

public class BinaryTreesYT {
    static class Node {
        int data;
        Node left, right;

        Node(int data) {
            this.data = data;
            this.left = null;
            this.right = null;
        }

        Node(int data, Node left, Node right) {
            this.data = data;
            this.left = left;
            this.right = right;
        }
    }

    static class BinaryTree {
        private static int indx;

        public static Node buildTree(int[] nodes) {
            if (nodes == null || nodes.length == 0) {
                throw new IllegalArgumentException("Node sequence must not be null or empty");
            }

            indx = 0;
            Node root = buildTreeFromSequence(nodes);
            if (indx != nodes.length) {
                throw new IllegalArgumentException("Node sequence contains unused values");
            }
            return root;
        }

        private static Node buildTreeFromSequence(int[] nodes) {
            if (indx >= nodes.length) {
                throw new IllegalArgumentException("Node sequence ended before the tree was complete");
            }

            if (nodes[indx] == -1) {
                indx++;
                return null;
            }

            Node newNode = new Node(nodes[indx]);
            indx++;
            newNode.left = buildTreeFromSequence(nodes);
            newNode.right = buildTreeFromSequence(nodes);

            return newNode;
        }

    }
    // Preorder Traversal:
    public static void preorder(Node root) {
        if(root == null)
            return;
        System.out.print(root.data + " ");
        preorder(root.left);
        preorder(root.right);
    }

    // Inorder Traversal:
    public static void inorder(Node root) {
        if (root == null)
            return;

        inorder(root.left);
        System.out.print(root.data + " ");
        inorder(root.right);
    }

    // Post order Traversal:
    public static void postorder(Node root) {
        if (root == null)
            return;
        postorder(root.left);
        postorder(root.right);
        System.out.print(root.data + " ");
    }

    // Level Order Traversal:
    public static void levelOrder(Node root) {
        if (root == null)
            return;
        Queue<Node> q = new LinkedList<>();
        q.add(root);
        q.add(null);

        while(!q.isEmpty()) {
            Node currNode = q.remove();
            if (currNode == null){
                System.out.println();
                if (q.isEmpty())
                    break;
                else
                    q.add(null);
            }

            else {
                System.out.print(currNode.data + " ");
                if (currNode.left != null) {
                    q.add(currNode.left);
                }
                if(currNode.right != null) {
                    q.add(currNode.right);
                }
            }
        }
    }

    // Count of Nodes:
    public static int countOfNodes(Node root) {
        if (root == null)
            return 0;
        int leftNodes = countOfNodes(root.left);
        int rightNodes = countOfNodes(root.right);

        return leftNodes + rightNodes + 1;
    }

    // Sum of the node values:
    public static int sumOfNodes(Node root) {
        if (root == null)
            return 0;
        int leftSum = sumOfNodes(root.left);
        int rightSum = sumOfNodes(root.right);

        // The real thing here is root.data: for every leaf it would be 0 + 0 + root.data/ leaf data
        return leftSum + rightSum + root.data;
    }

    // Height of the tree:
    public static int height(Node root) {
        if (root == null)
            return 0;

        int leftHeight = height(root.left);
        int rightHeight = height(root.right);

        int myHeight = Math.max(leftHeight, rightHeight) + 1;

        return myHeight;
    }

    // Diameter of a tree: O(n^2)
    public static int diameter(Node root) {
        if (root == null)
            return 0;

        int diam1 = diameter(root.left);
        int diam2 = diameter(root.right);
        int diam3 = height(root.left) + height(root.right) + 1;

        return Math.max(diam1, Math.max(diam2, diam3));
    }

    static class TreeInfo {
        int ht;
        public int diam;

        TreeInfo(int ht, int diam) {
            this.ht = ht;
            this.diam = diam;
        }
    }

    public static TreeInfo diameter2(Node root) {
        if (root == null)
            return new TreeInfo(0, 0);

        TreeInfo left = diameter2(root.left);
        TreeInfo right = diameter2(root.right);

        int myHeight = Math.max(left.ht, right.ht) + 1;
        int diam1 = left.diam;
        int diam2 = right.diam;
        int diam3 = left.ht + right.ht + 1;
        
        int myDiam = Math.max(diam1, Math.max(diam2, diam3));

        TreeInfo myInfo = new TreeInfo(myHeight, myDiam);
        return myInfo;
    }
    public static void main(String[] args) {
        int nodes[] = {1, 2, 4, -1, -1, 5, -1, -1, 3, -1, 6, -1, -1};
        BinaryTree tree = new BinaryTree();
        Node root = tree.buildTree(nodes);
        
        // System.out.print("Preorder: ");
        // preorder(root);

        // System.out.print("\nInorder: ");
        // inorder(root);

        // System.out.print("\nPostorder: ");
        // postorder(root);
        
        // System.out.println("\nLevel Order: ");
        // levelOrder(root);

        // System.out.println("Count of total nodes in the tree: " + countOfNodes(root));
        // System.out.println("sum of nodes in the tree: " + sumOfNodes(root));
        // System.out.println("Height of the tree: " + height(root));
        // System.out.println("Diameter of the tree(Approach # 01): " + diameter(root));
        System.out.println("Diameter of the tree(Approach # 02): " + diameter2(root).diam);
    }
}