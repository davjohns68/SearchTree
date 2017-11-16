
import java.util.NoSuchElementException;

// Class SearchTree stores and prints a binary search tree of
// objects of type E.  E must implement the Comparable<E>
// interface.  from Reges and Stepp, BJP 3ed.
// modified by W.P. Iverson, to not allow duplicates added
// Bellevue College, November 2015
// modified by David Johnson to add methods from Assignment 17
// November 16, 2017

public class SearchTree<E extends Comparable<E>> {
    private SearchTreeNode<E> overallRoot; // root of overall tree

    // post: constructs an empty search tree
    public SearchTree() {
        overallRoot = null;
    }
    
    // WRITE ADDITIONAL METHODS HERE:
    // Exercise 7
    public boolean isFull() {
        return (overallRoot == null || isFull(overallRoot));
    }

    private boolean isFull(SearchTreeNode<E> root) {
        if(root.left == null && root.right == null) {
            return true;
        } else {
            return (root.left != null && root.right != null && isFull(root.left) && isFull(root.right));
        }
    }  
    
    // Exercise 9
    public boolean equals(SearchTree<E> other) {
        return equals(this.overallRoot, other.overallRoot);
    }

    private boolean equals(SearchTreeNode<E> myRoot, SearchTreeNode<E> otherRoot) {
        if (myRoot == null && otherRoot == null) {
            return true;
        } else if (myRoot == null || otherRoot == null) {
            return false;
        } else if (myRoot.data.compareTo(otherRoot.data) != 0) {
            return false;
        } else {
            return (equals(myRoot.left, otherRoot.left) && equals(myRoot.right, otherRoot.right));
        }
    }
    
    // Exercise 12
    public void removeLeaves() {
        removeLeaves(overallRoot, overallRoot);
    }
    
    private void removeLeaves(SearchTreeNode<E> prev, SearchTreeNode<E> root) {
        if (root != null) {
            if (root.left == null && root.right == null) {
                // Remove it
                if (prev.left == root) {
                    prev.left = null;
                } else {
                    prev.right = null;
                }
            }
            removeLeaves(root, root.left);
            removeLeaves(root, root.right);
        }
    }
    
    // PowerPoint Exercise
    public void remove(E value) {
        overallRoot = remove(overallRoot, value);
    }

    private SearchTreeNode<E> remove(SearchTreeNode<E> root, E value) {
        if (root == null) {
            return null;
        } else if (root.data.compareTo(value) > 0) {
            root.left = remove(root.left, value);
        } else if (root.data.compareTo(value) < 0) {
            root.right = remove(root.right, value);
        } else {  // root.data == value; remove this node
            if (root.right == null) {
                return root.left;    // no R child; replace w/ L
            } else if (root.left == null) {
                return root.right;   // no L child; replace w/ R
            } else {
                // both children; replace w/ min from R
                root.data = getMin(root.right);
                root.right = remove(root.right, root.data);
            }
        }
        return root;
}

    // post: value added to tree so as to preserve binary search tree
    public void add(E value) {
        overallRoot = add(overallRoot, value);
    }

    // post: value added to tree so as to preserve binary search tree
    private SearchTreeNode<E> add(SearchTreeNode<E> root, E value) {
        if (root == null) {
            root = new SearchTreeNode<E>(value);
        } else if (root.data.compareTo(value) > 0) {
            root.left = add(root.left, value);
        } else if (root.data.compareTo(value) < 0) {
            root.right = add(root.right, value);
        }
        return root;
    }

    // post: returns true if tree contains value, returns false otherwise
    public boolean contains(E value) {
        return contains(overallRoot, value);
    }   

    // post: returns true if given tree contains value, returns false otherwise
    private boolean contains(SearchTreeNode<E> root, E value) {
        if (root == null) {
            return false;
        } else {
            int compare = value.compareTo(root.data);
            if (compare == 0) {
                return true;
            } else if (compare < 0) {
                return contains(root.left, value);
            } else {   // compare > 0
                return contains(root.right, value);
            }
        }
    }

    // post: prints the data of the tree, one per line
    public void print() {
        printInorder(overallRoot);
    }

    // post: prints the data of the tree using an inorder traversal
    private void printInorder(SearchTreeNode<E> root) {
        if (root != null) {
            printInorder(root.left);
            System.out.println(root.data);
            printInorder(root.right);
        }
    }

    // Returns the minimum value from this BST.
    // Throws a NoSuchElementException if the tree is empty.
    public E getMin() {
        if (overallRoot == null) {
            throw new NoSuchElementException();
        }
        return getMin(overallRoot);
    }

    private E getMin(SearchTreeNode<E> root) {
        if (root.left == null) {
            return root.data;
        } else {
            return getMin(root.left);
        }
    }
    
    private static class SearchTreeNode<E> {
        public E data;                   // data stored in this node
        public SearchTreeNode<E> left;   // left subtree
        public SearchTreeNode<E> right;  //  right subtree

        // post: constructs a leaf node with given data
        public SearchTreeNode(E data) {
            this(data, null, null);
        }

        // post: constructs a node with the given data and links
        public SearchTreeNode(E data, SearchTreeNode<E> left,
                              SearchTreeNode<E> right) {
            this.data = data;
            this.left = left;
            this.right = right;
        }
    }
}
