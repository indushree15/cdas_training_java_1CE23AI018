public class AVLTree {

    // Define the structure of the node
    static class Node {
        int data;
        Node left, right;
        int height;

        Node(int data) {
            this.data = data;
            left = right = null;
            height = 1; // Height is 1 for a new node
        }
    }

    // Root node of the AVL tree
    private Node root;

    public AVLTree() {
        root = null;
    }

    // Get the height of a node
    private int height(Node node) {
        if (node == null) {
            return 0;
        }
        return node.height;
    }

    // Get the balance factor of a node (difference in height of left and right subtrees)
    private int getBalance(Node node) {
        if (node == null) {
            return 0;
        }
        return height(node.left) - height(node.right);
    }

    // Right rotate the subtree rooted at node
    private Node rightRotate(Node node) {
        Node newRoot = node.left;
        node.left = newRoot.right;
        newRoot.right = node;

        // Update heights
        node.height = Math.max(height(node.left), height(node.right)) + 1;
        newRoot.height = Math.max(height(newRoot.left), height(newRoot.right)) + 1;

        return newRoot;
    }

    // Left rotate the subtree rooted at node
    private Node leftRotate(Node node) {
        Node newRoot = node.right;
        node.right = newRoot.left;
        newRoot.left = node;

        // Update heights
        node.height = Math.max(height(node.left), height(node.right)) + 1;
        newRoot.height = Math.max(height(newRoot.left), height(newRoot.right)) + 1;

        return newRoot;
    }

    // Insert a node into the AVL tree and balance the tree
    public void insert(int data) {
        root = insertRec(root, data);
    }

    // A recursive function to insert a node and ensure the tree remains balanced
    private Node insertRec(Node node, int data) {
        if (node == null) {
            return new Node(data);
        }

        if (data < node.data) {
            node.left = insertRec(node.left, data);  // Insert in the left subtree
        } else if (data > node.data) {
            node.right = insertRec(node.right, data);  // Insert in the right subtree
        } else {
            return node;  // Duplicates are not allowed
        }

        // Update height of the current node
        node.height = Math.max(height(node.left), height(node.right)) + 1;

        // Check the balance factor of the node
        int balance = getBalance(node);

        // Left Heavy (Right Rotation)
        if (balance > 1 && data < node.left.data) {
            return rightRotate(node);
        }

        // Right Heavy (Left Rotation)
        if (balance < -1 && data > node.right.data) {
            return leftRotate(node);
        }

        // Left-Right Case (Left Rotation on left child, then Right Rotation on root)
        if (balance > 1 && data > node.left.data) {
            node.left = leftRotate(node.left);
            return rightRotate(node);
        }

        // Right-Left Case (Right Rotation on right child, then Left Rotation on root)
        if (balance < -1 && data < node.right.data) {
            node.right = rightRotate(node.right);
            return leftRotate(node);
        }

        return node;  // Return the (unchanged) node pointer
    }

    // In-order traversal to print the tree
    public void inorder() {
        inorderRec(root);
    }

    private void inorderRec(Node root) {
        if (root != null) {
            inorderRec(root.left);  // Traverse left subtree
            System.out.print(root.data + " ");  // Visit the node
            inorderRec(root.right);  // Traverse right subtree
        }
    }

    // Main method to test the AVL Tree
    public static void main(String[] args) {
        AVLTree tree = new AVLTree();

        // Insert nodes into the AVL Tree
        tree.insert(10);
        tree.insert(20);
        tree.insert(30);
        tree.insert(15);
        tree.insert(25);
        tree.insert(5);
        tree.insert(1);

        // Print the tree using in-order traversal
        System.out.println("In-order traversal of the AVL Tree:");
        tree.inorder();  // Expected output: 1 5 10 15 20 25 30
        System.out.println();
    }
}
