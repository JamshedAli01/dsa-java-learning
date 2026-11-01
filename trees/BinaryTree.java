import java.util.Objects;

public class BinaryTree {
    private Object root;
    BinaryTree left, right;
    BinaryTree(Object root) {
        this.root = root;
    }

    BinaryTree(Object root, BinaryTree left, BinaryTree right) {
        this.root = root;
        this.left = left;
        this.right = right;
    }

    public Object getRoot() {
        return root;
    }
    public BinaryTree getLeft() {
        return left;
    }
    public BinaryTree getRight() {
        return right;
    }

    public void setRoot(Object root) {
        this.root = root;
    }

    public void setLeft(BinaryTree left) {
        this.left = left;
    }
    public void setRight(BinaryTree right) {
        this.right = right;
    }

    // toString method Or preorder traversal:
    public String toString() {
        StringBuffer sb = new StringBuffer();
        sb.append(root); // base case
        if (left != null) {
            sb.append(left); // append() here is recursive call.
        }
        if (right != null) {
            sb.append(right); // same goes here
        }
        return sb.toString() + " ";
    }

    // postorder:
    public String postorder() {
        StringBuffer sb = new StringBuffer();
        if (left != null) sb.append(left);
        if(right != null) sb.append(right);
        sb.append(root);

        return sb.toString() + " ";
    }

    // inorder:
    public String inorder() {
        StringBuffer sb = new StringBuffer();
        if (left != null) sb.append(left);
        sb.append(root);
        if(right != null) sb.append(right);
        

        return sb.toString() + " ";
    }

    public boolean isLeaf() {
        if (left == null && right == null)
            return true;
        return false;
    }

    public int size() {
        if (left == null && right == null) return 1;
        if (left == null) return 1 + right.size();
        if (right == null) return 1 + left.size();
        return 1 + left.size() + right.size();
    }

    public int height() {
        if (root == null)
            return 0;
        int lefts = 0, rights = 0;
        if (left != null) lefts = 1 + left.height();
        if (right != null) rights = 1 + right.height();
        // return 1 + Math.max(lefts, rights);
        return lefts > rights ? lefts : rights;
    }

    public boolean search(Object obj) {
        if (Objects.equals(root, obj)) return true;
        boolean leftVal = false, rightVal = false;
        if (left != null)  leftVal = left.search(obj);
        if (right != null) rightVal = right.search(obj);
        return leftVal || rightVal;
    }
    

    // equals method, and isFullTree method to be done
    public static void main(String[] args) {
        BinaryTree D = new BinaryTree("D");
        BinaryTree E = new BinaryTree("E");
        BinaryTree C = new BinaryTree("C", D, E);
        BinaryTree B = new BinaryTree("B");
        BinaryTree A = new BinaryTree("A", B, C);

        // System.out.println(A.getRoot());

        // System.out.println(A);
        // System.out.println(A.postorder());
        // System.out.println(A.inorder());
        // System.out.println(A.isLeaf());
        // System.out.println(A.size());
        // System.out.println(A.height());
        System.out.println(A.search("C"));

    }
}
