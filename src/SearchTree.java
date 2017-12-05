
import java.util.ArrayList;
import java.util.NoSuchElementException;

// Class SearchTree stores and prints a binary search tree of
// objects of type E.  E must implement the Comparable<E>
// interface.  from Reges and Stepp, BJP 3ed.
// modified by W.P. Iverson, to not allow duplicates added
// Bellevue College, November 2015
// modified by David Johnson to add methods from Assignment 17
// November 16, 2017
// Modified further for CS211 final exam
// December 5, 2017

public class SearchTree<E extends Comparable<E>> {
    private SearchTreeNode<E> overallRoot; // root of overall tree

    // post: constructs an empty search tree
    public SearchTree() {
        overallRoot = null;
    }
    
    // WRITE ADDITIONAL METHODS HERE:
    // Final question 1
    // Prints (System.out) this tree, more like the "normal" upright way, with the root at top.
    public void showTree() {
        int levelCount = this.countLevels();
        int maxWidth = getWidth();
        final int ELEMENTSIZE = 6;
        String output = "";
        
        for (int i = 0; i < levelCount; i++) {
            ArrayList<E> level = returnLevel(i);
            int whiteSpace = ((maxWidth * ELEMENTSIZE) - (level.size() * ELEMENTSIZE));
            for (int j = 0; j < whiteSpace / 2; j++) {
                output += " ";
            }
            for (E item: level) {
                if (item != null) {
                    output += item;
                    // Round out spacing to ELEMENTSIZE characters for each position
                    for (int j = 0; j < ELEMENTSIZE - String.valueOf(item).length(); j++) {
                        output += " ";
                    }
                } else {
                    for (int j = 0; j < ELEMENTSIZE - 1; j++) {
                        output += " ";
                    }
                }
            }
            output += "\n";
        }
        
        System.out.println(output);
    }
    
    private int getWidth() {
        int levelCount = this.countLevels();
        int width = 0;
        
        for (int i = 0; i < levelCount; i++) {
            ArrayList<E> level = returnLevel(i);
            if (level.size() > width) {
                width = level.size();
            }
        }
        
        return width;
    }
    
    // Final question 2
    // Returns the total number of nodes in this tree.
    public int countNodes() {
        return countNodes(this.overallRoot);
    }
    
    private int countNodes(SearchTreeNode<E> root) {
        if (root == null) {
            return 0;
        } else {
            //System.out.println("Node: " + root.data + "\tCount: " + count);
            return 1 + countNodes(root.left) + countNodes(root.right);
        }
    }
    
    // Final question 3
    // Returns the number of levels in this tree, null tree returns zero, single node 1, etc...
    public int countLevels() {
        return countLevels(this.overallRoot);
    }
    
    private int countLevels(SearchTreeNode<E> root) {
        if (root == null) {
            return 0;
        } else {
            return 1 + Math.max(countLevels(root.left), countLevels(root.right));
        }
    }
    
    // Final question 4
    // Return a Collection (a List is suggested, but there are many possibilities) of the data 
    // on leaves in this tree, null data does not make a leaf.
    public ArrayList<E> returnLeaves() {
        ArrayList<E> result = new ArrayList<>();
        
        returnLeaves(this.overallRoot, result);
        
        return result;
    }
    
    private void returnLeaves(SearchTreeNode<E> root, ArrayList<E> result) {
        if (root != null) {
            if (root.left == null && root.right == null) {
                result.add(root.data);
            } else {
                returnLeaves(root.left, result);
                returnLeaves(root.right, result);
            }
        }
    }
    
    // Final question 5
    // Return a Collection of all data at level i, the root being i==0.  I leave this to you as 
    // to how you handle null data, although you might consider how nulls actually appear as 
    // blank space back in problem #1.
    public ArrayList<E> returnLevel(int level) {
        ArrayList<E> result = new ArrayList<>();

        returnLevel(this.overallRoot, level, result);
        
        return result;
    }
    
    private void returnLevel(SearchTreeNode<E> root, int level, ArrayList<E> result) {
        if (root != null) {
            if (level == 0) {
                result.add(root.data);
            } else {
                returnLevel(root.left, level - 1, result);
                returnLevel(root.right, level - 1, result);
            }
        } else {
            if (level == 0) {
                result.add(null);
            }
        }
    }
    
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
            root = new SearchTreeNode<>(value);
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
