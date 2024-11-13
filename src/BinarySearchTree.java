public class BinarySearchTree {

    // Define a Node class that represents each node in the BST
    static class Node {
        int data;
        Node left, right;

        // Constructor to create a new node
        Node(int item) {
            data = item;
            left = right = null;
        }
    }

    // Root of the BST
    Node root;

    // Constructor to create an empty BST
    public BinarySearchTree() {
        root = null;
    }

    // Insert a new node with given data
    public void insert(int data) {
        root = insertRec(root, data);
    }

    // A recursive function to insert a new node with given data
    private Node insertRec(Node root, int data) {
        // If the tree is empty, return a new node
        if (root == null) {
            root = new Node(data);
            return root;
        }

        // Otherwise, recur down the tree
        if (data < root.data) {
            root.left = insertRec(root.left, data); // Insert in the left subtree
        } else if (data > root.data) {
            root.right = insertRec(root.right, data); // Insert in the right subtree
        }

        // Return the (unchanged) node pointer
        return root;
    }

    // Search for a node with given data
    public boolean search(int data) {
        return searchRec(root, data);
    }

    // A recursive function to search for a node with given data
    private boolean searchRec(Node root, int data) {
        // Base case: root is null or data is present at the root
        if (root == null) {
            return false;
        }
        if (root.data == data) {
            return true;
        }

        // Otherwise, recur down the tree
        if (data < root.data) {
            return searchRec(root.left, data); // Search in the left subtree
        } else {
            return searchRec(root.right, data); // Search in the right subtree
        }
    }

    // Delete a node with the given data
    public void delete(int data) {
        root = deleteRec(root, data);
    }

    // A recursive function to delete a node with the given data
    private Node deleteRec(Node root, int data) {
        // Base case: root is null
        if (root == null) {
            return root;
        }

        // Otherwise, recur down the tree
        if (data < root.data) {
            root.left = deleteRec(root.left, data); // Delete in the left subtree
        } else if (data > root.data) {
            root.right = deleteRec(root.right, data); // Delete in the right subtree
        } else {
            // Node with only one child or no child
            if (root.left == null) {
                return root.right;
            } else if (root.right == null) {
                return root.left;
            }

            // Node with two children: Get the inorder successor (smallest in the right subtree)
            root.data = minValue(root.right);

            // Delete the inorder successor
            root.right = deleteRec(root.right, root.data);
        }

        return root;
    }

    // A utility function to get the smallest value node in the tree
    private int minValue(Node root) {
        int minValue = root.data;
        while (root.left != null) {
            minValue = root.left.data;
            root = root.left;
        }
        return minValue;
    }

    // In-order traversal of the BST
    public void inorder() {
        inorderRec(root);
    }

    // A recursive function to perform in-order traversal
    private void inorderRec(Node root) {
        if (root != null) {
            inorderRec(root.left); // Traverse the left subtree
            System.out.print(root.data + " "); // Print the data
            inorderRec(root.right); // Traverse the right subtree
        }
    }

    // Main method to test the BST operations
    public static void main(String[] args) {
        BinarySearchTree tree = new BinarySearchTree();

        // Inserting nodes into the BST
        tree.insert(50);
        tree.insert(30);
        tree.insert(20);
        tree.insert(40);
        tree.insert(70);
        tree.insert(60);
        tree.insert(80);

        // In-order traversal
        System.out.println("In-order traversal:");
        tree.inorder();  // Expected output: 20 30 40 50 60 70 80
        System.out.println();

        // Search for a node
        System.out.println("Searching for 40 in the tree: " + tree.search(40)); // true
        System.out.println("Searching for 90 in the tree: " + tree.search(90)); // false

        // Delete a node
        tree.delete(20);  // Delete node with value 20
        System.out.println("In-order traversal after deleting 20:");
        tree.inorder();  // Expected output: 30 40 50 60 70 80
        System.out.println();

        tree.delete(30);  // Delete node with value 30
        System.out.println("In-order traversal after deleting 30:");
        tree.inorder();  // Expected output: 40 50 60 70 80
        System.out.println();
    }
}
