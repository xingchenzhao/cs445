package cs445.a5;

import java.util.Iterator;
import java.util.NoSuchElementException;

import cs445.StackAndQueuePackage.*;

public class TernaryTree<T extends Comparable<T>> implements TernaryTreeInterface<T>  {
    private TernaryNode<T> root;

    //CONSTRUCTOR METHODS
    public TernaryTree() { //empty constructor
        root = null;
    }

    public TernaryTree(T rootData) { // 1 object constructor
        root = new TernaryNode<>(rootData);
    }

    public TernaryTree(T rootData, TernaryTree<T> leftTree,
                       TernaryTree<T> middleTree, TernaryTree<T> rightTree) {

        privateSetTree(rootData, leftTree, middleTree, rightTree);

    }

    //Set Tree Methods
    public void setTree(T rootData) {
        root = new TernaryNode<>(rootData);
    }

    public void setTree(T rootData, TernaryTreeInterface<T> leftTree,
                        TernaryTreeInterface<T> middleTree, TernaryTreeInterface<T> rightTree) {
        privateSetTree(rootData, (TernaryTree<T>) leftTree,
                (TernaryTree<T>) middleTree, (TernaryTree<T>) rightTree);
    }

    private void privateSetTree(T rootData, TernaryTree<T> leftTree,
                                TernaryTree<T> middleTree, TernaryTree<T> rightTree) {
        root = new TernaryNode<>(rootData);

        if ((leftTree != null) && !leftTree.isEmpty()) {
            root.setLeftChild(leftTree.root);
        }

        if ((middleTree != null) && !middleTree.isEmpty()) {
            if (middleTree != leftTree) {
                root.setMiddleChild(middleTree.root);
            } else {
                root.setMiddleChild(middleTree.root.copy());
            }
        }

        if ((rightTree != null) && !rightTree.isEmpty()) {
            if (rightTree != leftTree && rightTree != middleTree) {
                root.setRightChild(rightTree.root);
            } else {
                root.setRightChild(rightTree.root.copy());
            }
        }

        if ((leftTree != null) && (leftTree != this)) {
            leftTree.clear();
        }

        if ((middleTree != null) && (middleTree != this)) {
            middleTree.clear();
        }

        if ((rightTree != null) && (rightTree != this)) {
            rightTree.clear();
        }

    }


    public T getRootData() {
        if (isEmpty()) {
            throw new EmptyTreeException();
        } else {
            return root.getData();
        }
    }

    public int getHeight() {
        int height = 0;
        if (!isEmpty()) {
            height = root.getHeight(); // call the TernaryNode Class
        }
        return height;
    }

    public int getNumberOfNodes() {
        int numberOfNodes = 0;
        if (!isEmpty()) {
            numberOfNodes = root.getNumberOfNodes();
        }
        return numberOfNodes;
    }

    public boolean isEmpty() {
        return root == null;
    }

    public void clear() {
        root = null;
    }

    //Iterators
    public Iterator getPreorderIterator() {

        return new PreorderIterator();
    }

    public Iterator getPostorderIterator() {
        return new PostorderIterator();
    }

    public Iterator getInorderIterator() {
        throw new UnsupportedOperationException("Inorder Iterator is unsupported for Ternary Tree");
        /**
         An In-Order Iterator is unsupported in Ternary Tree. If using In-Oder Iterator for Ternary
         Tree, the logic will be (1)leftTree (2)output root (3)MiddleTree (4)output root (5)RightTree .
         Therefore, a node with three children will be output twice.
         */
    }

    public Iterator getLevelOrderIterator() {
        return new LevelOrderIterator();
    }

    public boolean contains(T elem) {
        Iterator<T> find = getLevelOrderIterator();
        if (elem == null){
            return false;
        }
        boolean found = false;
        while(!found && find.hasNext()){
            if(elem.compareTo(find.next()) == 0 ){
                found = true;
            }
        }
        return found;
    }

    public boolean isBalanced(){
        return isBalanced(root);
    }

    private boolean isBalanced(TernaryNode root) {
        int leftHeight;
        int middleHeight;
        int rightHeight;

        if(root == null){
            return true;
        }
        try {
            leftHeight = root.getLeftChild().getHeight();
        }catch (NullPointerException e){
            leftHeight = 0;
        }
        try {
            middleHeight = root.getMiddleChild().getHeight();
        }catch (NullPointerException e){
            middleHeight = 0;
        }
        try {
            rightHeight = root.getRightChild().getHeight();
        }catch (NullPointerException e){
            rightHeight = 0;
        }

        if((Math.abs(leftHeight - middleHeight)<=1) && (Math.abs(leftHeight - rightHeight) <= 1)
                && isBalanced(root.getLeftChild()) && isBalanced(root.getMiddleChild())
             && isBalanced(root.getRightChild())){
            return true;
        }

        return false;
    }


    /**
     * Post-Order
     **/
    private class PreorderIterator implements Iterator<T> {
        private StackInterface<TernaryNode<T>> nodeStack; // stack of Ternary Nodes

        public PreorderIterator() {
            nodeStack = new LinkedStack<>();
            if (root != null) {
                nodeStack.push(root);
            }
        }

        public boolean hasNext() {
            return !nodeStack.isEmpty();
        }

        public T next() {
            TernaryNode<T> nextNode;

            if (hasNext()) {//if node stack is not empty
                nextNode = nodeStack.pop();
                TernaryNode<T> leftChild = nextNode.getLeftChild();
                TernaryNode<T> middleChild = nextNode.getMiddleChild();
                TernaryNode<T> rightChild = nextNode.getRightChild();

                // push child nodes into stack in reverse order by recursive calls
                if (rightChild != null) {
                    nodeStack.push(rightChild);
                }
                if (middleChild != null) {
                    nodeStack.push(middleChild);
                }

                if (leftChild != null) {
                    nodeStack.push(leftChild);
                }
            } else {
                throw new NoSuchElementException();
            }

            return nextNode.getData();
        }

        public void remove() {
            throw new UnsupportedOperationException("Remove method not supported in Preorder Iterator");
        }
    }


    private class PostorderIterator implements Iterator<T> {
        private StackInterface<TernaryNode<T>> nodeStack;
        private TernaryNode<T> currentNode;

        public PostorderIterator() {
            nodeStack = new LinkedStack<>();
            currentNode = root;
        }


        public boolean hasNext() {
            return !nodeStack.isEmpty() || (currentNode != null);
        }

        public T next() {
            boolean foundNext = false;
            TernaryNode<T> leftChild, middleChild, rightChild, nextNode = null;

            //Find leftmost leaf
            while (currentNode != null) {
                nodeStack.push(currentNode);
                leftChild = currentNode.getLeftChild();
                if (leftChild == null) {
                    middleChild = currentNode.getMiddleChild();
                    if (middleChild == null) {
                        currentNode = currentNode.getRightChild();
                    } else {
                        currentNode = middleChild;
                    }
                } else {
                    currentNode = leftChild;
                }
            }

            // Stack is not empty either because we just pushed a node, or
            // it wasn't empty to begin with since hasNext() is true.
            // But Iterator specifies an exception for next() in case
            // hasNext() is false.

            if (!nodeStack.isEmpty()) {
                nextNode = nodeStack.pop();
                //nextNode != null since stack was not empty before pop

                TernaryNode<T> parent = null;
                if (!nodeStack.isEmpty()) {

                    parent = nodeStack.peek();
                    //left child nextNode, there exists a middle child, move to the next
                    if (nextNode == parent.getLeftChild() && parent.getMiddleChild() != null) {
                        currentNode = parent.getMiddleChild();
                    }
                    //left child nextNode, there does not exist a middle child, move to the right child
                    else if (nextNode == parent.getLeftChild() && parent.getMiddleChild() == null) {
                        currentNode = parent.getRightChild();
                    }
                    //middle child nextNode, move to the right child
                    else if (nextNode == parent.getMiddleChild()) {
                        currentNode = parent.getRightChild();
                    }
                    //all other cases including right child nextNode, set current to null
                    else {
                        currentNode = null;
                    }
                    //nodeStack empty
                } else {
                    currentNode = null;
                }
            } else {
                throw new NoSuchElementException();
            }
            return nextNode.getData();
        }

        public void remove() {
            throw new UnsupportedOperationException();
        }
    }

    private class LevelOrderIterator implements Iterator<T> {
        private QueueInterface<TernaryNode<T>> nodeQueue;

        public LevelOrderIterator() {
            nodeQueue = new LinkedQueue<>();
            if (root != null) {
                nodeQueue.enqueue(root);
            }
        }


        public boolean hasNext() {
            return !nodeQueue.isEmpty();
        }

        public T next() {
            TernaryNode<T> nextNode;
            if (hasNext()) {
                nextNode = nodeQueue.dequeue();
                TernaryNode<T> leftChild = nextNode.getLeftChild();
                TernaryNode<T> middleChild = nextNode.getMiddleChild();
                TernaryNode<T> rightChild = nextNode.getRightChild();

                //Add to queue in order of recursive calls

                if (leftChild != null) {
                    nodeQueue.enqueue(leftChild);
                }
                if (middleChild != null) {
                    nodeQueue.enqueue(middleChild);
                }
                if (rightChild != null) {
                    nodeQueue.enqueue(rightChild);
                }
            } else {
                throw new NoSuchElementException();
            }
            return nextNode.getData();
        }

        public void remove() {
            throw new UnsupportedOperationException();
        }
    }


}
