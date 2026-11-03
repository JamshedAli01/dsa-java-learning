public class AVLTree {
    private int key, height;
    private AVLTree left, right;

    private static final AVLTree NIL = new AVLTree();

    public AVLTree(int key) {
        this.key = key;
        left = right = NIL;
    }
    private AVLTree() {
        left = right = this;
        height = -1;
    }

    private AVLTree(int key, AVLTree left, AVLTree right) {
        this.key  = key;
        this.left = left;
        this.right = right;

        height = 1 + Math.max(left.height, right.height);
    }

    // add a node
    public boolean add(int key) {
        int oldSize = size();
        grow(key);
        return (oldSize < size());
    }

    // size() method
    public int size() {
        if (this == NIL) return 0;
        return 1 + left.size() + right.size();
    }

    // Insert recursively, then restore the AVL balance invariant.
    public AVLTree grow(int key) {
        if (this == NIL) return new AVLTree(key);
        if (key == this.key) return this;
        if (key > this.key) right = right.grow(key);
        else 
            left = left.grow(key);

        rebalance();  // this balances the tree
        height = 1 + Math.max(left.height, right.height);
        return this;
    }

    // toString() to convert the AVL Tree into String
    public String toString() {
        if (this == NIL) return "";
        StringBuilder sb = new StringBuilder();
        
        if (left != NIL)
            sb.append(left + " ");

            sb.append(key);

        if (right != NIL)
            sb.append(" " + right);

        return sb.toString();
    }

    // AVL is auto-rebalancing tree. So, we call rebalace() method 
    // inside the grow method to rebalance the tree whenever it becomes unbalanced.
    public void rebalance() {
        if (right.height > left.height + 1) {
            if (right.left.height > right.right.height) right.rotateRight();
            rotateLeft();
        }

        else if (left.height > right.height + 1) {
            if(left.right.height > left.left.height) left.rotateLeft();
            rotateRight();
        }
    }


    // rotating a tree to the left side (Right-Right Rotation)
    public void rotateLeft() {
        left = new AVLTree(key, left, right.left);
        key = right.key;
        right = right.right;
    }

    // rotating a tree to the right side (Left-Left Rotataion)
    public void rotateRight() {
        right = new AVLTree(key, left.right, right);
        key = left.key;
        left = left.left;
    }
    
    public static void main(String[] args) {
        AVLTree avl = new AVLTree(50);
        avl.add(74);
        avl.add(43);
        avl.add(100);
        avl.add(43); // add returns false because 43 is already in the tree.
        avl.add(15);
        avl.add(12);
        avl.add(3);
        avl.add(83);

        System.out.println(avl);
    }
}
