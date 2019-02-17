package cs445.a5;

import cs445.a5.*;

import java.util.Iterator;

public class TreeTester {
    public static void main(String[] args) {
        System.out.println("*********Test One***********");
        testOne();
        System.out.println();
        System.out.println();
        System.out.println("*********Test Two***********");
        testTwo();
        System.out.println();
        System.out.println();
        System.out.println();
        System.out.println();
        System.out.println("*********Test Three***********");
        testThree();
        System.out.println();
        System.out.println();
        System.out.println("*********Test Four***********");
        testFour();
    }

    public static void testOne() {
        System.out.println("Let's make a ternary tree with 12 nodes of Alphabet,including root");
        String a, b, c, d, e, f, g, h, i, j, k, l,m,n,o,p,q,r,s,t,u;
        a = "A";
        b = "B";
        c = "C";
        d = "D";
        e = "E";
        f = "F";
        g = "G";
        h = "H";
        i = "I";
        j = "J";
        k = "K";
        l = "L";
        m = "M";
        n = "N";
        o = "O";
        p = "P";
        q = "Q";

        TernaryTree<String> rootTree = new TernaryTree<>(); //empty constructor

        TernaryTree<String> bTree = new TernaryTree<>();
        TernaryTree<String> cTree = new TernaryTree<>();
        TernaryTree<String> dTree = new TernaryTree<>();
        TernaryTree<String> eTree = new TernaryTree<>();
        TernaryTree<String> fTree = new TernaryTree<>();
        TernaryTree<String> gTree = new TernaryTree<>();
        TernaryTree<String> hTree = new TernaryTree<>();
        TernaryTree<String> iTree = new TernaryTree<>();
        TernaryTree<String> jTree = new TernaryTree<>();
        TernaryTree<String> kTree = new TernaryTree<>();
        TernaryTree<String> lTree = new TernaryTree<>();

        //leaf nodes
        eTree.setTree(e);
        fTree.setTree(f);
        gTree.setTree(g);
        hTree.setTree(h);
        jTree.setTree(j);
        kTree.setTree(k);
        lTree.setTree(l);

        iTree.setTree(i, jTree, kTree, lTree);
        bTree.setTree(b, eTree, fTree, null);
        cTree.setTree(c, gTree, null, hTree);
        dTree.setTree(d, null, null, iTree);

        rootTree.setTree(a, bTree, cTree, dTree);


        //Calling methods
        System.out.println("rootTree.isBalanced() returns: " +rootTree.isBalanced());
        System.out.println("rootTree.getRootData() returns: " + rootTree.getRootData());
        System.out.println("rootTree.isEmpty() returns: " + rootTree.isEmpty());
        System.out.println("rootTree.getHeight() returns: " + rootTree.getHeight());
        System.out.println("rootTree.getNumberOfNodes() returns: " + rootTree.getNumberOfNodes());

        //Iterator methods
        System.out.println("****Iterators*****");
        Iterator<String> preOrder = rootTree.getPreorderIterator();
        System.out.println("Preorder Iterator: ");
        int preCount = 1;
        while (preOrder.hasNext()) {
            System.out.println("next() number " + preCount + " = " + preOrder.next());
            preCount++;
        }

        System.out.println("rootTree.contains(a) returns: " + rootTree.contains(a));
        System.out.println("rootTree.contains(b) returns: " + rootTree.contains(b));
        System.out.println("rootTree.contains(c) returns: " + rootTree.contains(d));
        System.out.println("rootTree.contains(e) returns: " + rootTree.contains(f));
        System.out.println("rootTree.contains(g) returns: " + rootTree.contains(h));
        System.out.println("rootTree.contains(i) returns: " + rootTree.contains(i));
        System.out.println("rootTree.contains(j) returns: " + rootTree.contains(j));
        System.out.println("rootTree.contains(k) returns: " + rootTree.contains(k));
        System.out.println("rootTree.contains(l) returns: " + rootTree.contains(l));
        System.out.println("rootTree.contains(m) returns: " + rootTree.contains(m));
        System.out.println("rootTree.contains(n) returns: " + rootTree.contains(n));
        System.out.println("rootTree.contains(o) returns: " + rootTree.contains(o));
        System.out.println("rootTree.contains(p) returns: " + rootTree.contains(p));
        System.out.println("rootTree.contains(q) returns: " + rootTree.contains(q));

        System.out.println("rootTree.contains(V) returns: " + rootTree.contains("V"));
        System.out.println("rootTree.contains(balabala) returns: " + rootTree.contains("balabala"));
        System.out.println("rootTree.contains(null) returns: " + rootTree.contains(null));


        System.out.println();
        try {
            Iterator<String> inOrder = rootTree.getInorderIterator();
        } catch (UnsupportedOperationException v) {
            System.out.println("UnsupportedOperationException: " + v.getMessage());
        }
        System.out.println("Inorder Iterator (unsupported)");

        System.out.println();
        Iterator<String> postOrder = rootTree.getPostorderIterator();
        System.out.println("Postorder Iterator: ");
        int postCount = 1;
        while (postOrder.hasNext()) {
            System.out.println("next() number " + postCount + " = " + postOrder.next());
            postCount++;
        }

        System.out.println();
        Iterator<String> levelOrder = rootTree.getLevelOrderIterator();
        System.out.println("LevelOrder Iterator: ");
        int levelCount = 1;
        while (levelOrder.hasNext()) {
            System.out.println("next() number " + levelCount + " = " + levelOrder.next());
            levelCount++;
        }



        System.out.println();
        System.out.println("Testing clear method");
        rootTree.clear();
        System.out.println("rootTree.isEmpty() returns: " + rootTree.isEmpty());
        try {
            System.out.println("rootTree.getRootData() returns: " + rootTree.getRootData());

        } catch (EmptyTreeException x) {
            System.out.println("EmptyTreeException: " + x.getMessage() + "(no message set for EmptyTreeException)");
        }
    }


    public static void testTwo() {
        System.out.println("Let's make a ternary tree with 12 nodes of Number,including root");
        int a, b, c, d, e, f, g, h, i, j, k, l;
        a = 1;
        b = 2;
        c = 3;
        d = 4;
        e = 5;
        f = 6;
        g = 7;
        h = 8;
        i = 9;
        j = 10;
        k = 11;
        l = 12;

        TernaryTree<Integer> rootTree = new TernaryTree<>(); //empty constructor

        TernaryTree<Integer> bTree = new TernaryTree<>();
        TernaryTree<Integer> cTree = new TernaryTree<>();
        TernaryTree<Integer> dTree = new TernaryTree<>();
        TernaryTree<Integer> eTree = new TernaryTree<>();
        TernaryTree<Integer> fTree = new TernaryTree<>();
        TernaryTree<Integer> gTree = new TernaryTree<>();
        TernaryTree<Integer> hTree = new TernaryTree<>();
        TernaryTree<Integer> iTree = new TernaryTree<>();
        TernaryTree<Integer> jTree = new TernaryTree<>();
        TernaryTree<Integer> kTree = new TernaryTree<>();
        TernaryTree<Integer> lTree = new TernaryTree<>();

        //leaf nodes
        eTree.setTree(e);
        fTree.setTree(f);
        gTree.setTree(g);
        hTree.setTree(h);
        jTree.setTree(j);
        kTree.setTree(k);
        lTree.setTree(l);

        iTree.setTree(i, jTree, kTree, lTree);
        bTree.setTree(b, null, eTree, fTree);
        cTree.setTree(c, gTree, null, hTree);
        dTree.setTree(d, iTree, null, null);

        rootTree.setTree(a, bTree, cTree, dTree);


        //Calling methods
        System.out.println("rootTree.isBalanced() returns: " +rootTree.isBalanced());
        System.out.println("rootTree.getRootData() returns: " + rootTree.getRootData());
        System.out.println("rootTree.isEmpty() returns: " + rootTree.isEmpty());
        System.out.println("rootTree.getHeight() returns: " + rootTree.getHeight());
        System.out.println("rootTree.getNumberOfNodes() returns: " + rootTree.getNumberOfNodes());

        //Iterator methods
        System.out.println("****Iterators*****");
        Iterator<Integer> preOrder = rootTree.getPreorderIterator();
        System.out.println("Preorder Iterator: ");
        int preCount = 1;
        while (preOrder.hasNext()) {
            System.out.println("next() number " + preCount + " = " + preOrder.next());
            preCount++;
        }


        for(int x = 1; x <=20; x++){
            System.out.println("rootTree.contains("+x+") returns: " + rootTree.contains(x));
        }


        System.out.println();
        try {
            Iterator<Integer> inOrder = rootTree.getInorderIterator();
        } catch (UnsupportedOperationException v) {
            System.out.println("UnsupportedOperationException: " + v.getMessage());
        }
        System.out.println("Inorder Iterator (unsupported)");

        System.out.println();
        Iterator<Integer> postOrder = rootTree.getPostorderIterator();
        System.out.println("Postorder Iterator: ");
        int postCount = 1;
        while (postOrder.hasNext()) {
            System.out.println("next() number " + postCount + " = " + postOrder.next());
            postCount++;
        }

        System.out.println();
        Iterator<Integer> levelOrder = rootTree.getLevelOrderIterator();
        System.out.println("LevelOrder Iterator: ");
        int levelCount = 1;
        while (levelOrder.hasNext()) {
            System.out.println("next() number " + levelCount + " = " + levelOrder.next());
            levelCount++;
        }

        System.out.println();
        System.out.println("Testing clear method");
        rootTree.clear();
        System.out.println("rootTree.isEmpty() returns: " + rootTree.isEmpty());
        try {
            System.out.println("rootTree.getRootData() returns: " + rootTree.getRootData());

        } catch (EmptyTreeException x) {
            System.out.println("EmptyTreeException: " + x.getMessage() + "(no message set for EmptyTreeException)");
        }
    }

    public static void testThree() {
        System.out.println("Let's make a balanced ternary tree with 12 nodes of Number,including root");
        int a, b, c, d, e, f, g, h, i, j, k, l,m;
        a = 1;
        b = 2;
        c = 3;
        d = 4;
        e = 5;
        f = 6;
        g = 7;
        h = 8;
        i = 9;
        j = 10;
        k = 11;
        l = 12;
        m = 13;

        TernaryTree<Integer> rootTree = new TernaryTree<>(); //empty constructor

        TernaryTree<Integer> bTree = new TernaryTree<>();
        TernaryTree<Integer> cTree = new TernaryTree<>();
        TernaryTree<Integer> dTree = new TernaryTree<>();
        TernaryTree<Integer> eTree = new TernaryTree<>();
        TernaryTree<Integer> fTree = new TernaryTree<>();
        TernaryTree<Integer> gTree = new TernaryTree<>();
        TernaryTree<Integer> hTree = new TernaryTree<>();
        TernaryTree<Integer> iTree = new TernaryTree<>();
        TernaryTree<Integer> jTree = new TernaryTree<>();
        TernaryTree<Integer> kTree = new TernaryTree<>();
        TernaryTree<Integer> lTree = new TernaryTree<>();
        TernaryTree<Integer> mTree = new TernaryTree<>();

        //leaf nodes
        eTree.setTree(e);
        fTree.setTree(f);
        gTree.setTree(g);
        hTree.setTree(h);
        iTree.setTree(i);
        jTree.setTree(j);
        kTree.setTree(k);
        lTree.setTree(l);
        mTree.setTree(m);

        bTree.setTree(b,eTree,fTree,gTree);
        cTree.setTree(c,hTree,iTree,jTree);
        dTree.setTree(d,kTree,lTree,mTree);
        rootTree.setTree(a,bTree,cTree,dTree);

        //Calling methods
        System.out.println("rootTree.isBalanced() returns: " +rootTree.isBalanced());
        System.out.println("rootTree.getRootData() returns: " + rootTree.getRootData());
        System.out.println("rootTree.isEmpty() returns: " + rootTree.isEmpty());
        System.out.println("rootTree.getHeight() returns: " + rootTree.getHeight());
        System.out.println("rootTree.getNumberOfNodes() returns: " + rootTree.getNumberOfNodes());

        //Iterator methods
        System.out.println("****Iterators*****");
        Iterator<Integer> preOrder = rootTree.getPreorderIterator();
        System.out.println("Preorder Iterator: ");
        int preCount = 1;
        while (preOrder.hasNext()) {
            System.out.println("next() number " + preCount + " = " + preOrder.next());
            preCount++;
        }


        for(int x = 1; x <=20; x++){
            System.out.println("rootTree.contains("+x+") returns: " + rootTree.contains(x));
        }


        System.out.println();
        try {
            Iterator<Integer> inOrder = rootTree.getInorderIterator();
        } catch (UnsupportedOperationException v) {
            System.out.println("UnsupportedOperationException: " + v.getMessage());
        }
        System.out.println("Inorder Iterator (unsupported)");

        System.out.println();
        Iterator<Integer> postOrder = rootTree.getPostorderIterator();
        System.out.println("Postorder Iterator: ");
        int postCount = 1;
        while (postOrder.hasNext()) {
            System.out.println("next() number " + postCount + " = " + postOrder.next());
            postCount++;
        }

        System.out.println();
        Iterator<Integer> levelOrder = rootTree.getLevelOrderIterator();
        System.out.println("LevelOrder Iterator: ");
        int levelCount = 1;
        while (levelOrder.hasNext()) {
            System.out.println("next() number " + levelCount + " = " + levelOrder.next());
            levelCount++;
        }

        System.out.println();
        System.out.println("Testing clear method");
        rootTree.clear();
        System.out.println("rootTree.isEmpty() returns: " + rootTree.isEmpty());
        try {
            System.out.println("rootTree.getRootData() returns: " + rootTree.getRootData());

        } catch (EmptyTreeException x) {
            System.out.println("EmptyTreeException: " + x.getMessage() + "(no message set for EmptyTreeException)");
        }

    }

    public static void testFour(){
        System.out.println("Let's make an unbalanced ternary tree with 12 nodes of Number,including root");
        int a, b, c, d, e, f, g, h, i, j, k, l,m;
        a = 1;
        b = 2;
        c = 3;
        d = 4;
        e = 5;
        f = 6;
        g = 7;
        h = 8;
        i = 9;
        j = 10;
        k = 11;
        l = 12;
        m = 13;

        TernaryTree<Integer> rootTree = new TernaryTree<>(); //empty constructor

        TernaryTree<Integer> bTree = new TernaryTree<>();
        TernaryTree<Integer> cTree = new TernaryTree<>();
        TernaryTree<Integer> dTree = new TernaryTree<>();
        TernaryTree<Integer> eTree = new TernaryTree<>();
        TernaryTree<Integer> fTree = new TernaryTree<>();
        TernaryTree<Integer> gTree = new TernaryTree<>();
        TernaryTree<Integer> hTree = new TernaryTree<>();
        TernaryTree<Integer> iTree = new TernaryTree<>();
        TernaryTree<Integer> jTree = new TernaryTree<>();
        TernaryTree<Integer> kTree = new TernaryTree<>();
        TernaryTree<Integer> lTree = new TernaryTree<>();
        TernaryTree<Integer> mTree = new TernaryTree<>();

        //leaf nodes

        hTree.setTree(h);
        iTree.setTree(i);
        jTree.setTree(j);

        fTree.setTree(f,null,iTree,null);
        gTree.setTree(g,null,null,jTree);
        eTree.setTree(e,hTree,null,null);
        bTree.setTree(b,eTree,fTree,gTree);
        cTree.setTree(c,null,null,null);
        dTree.setTree(d,null,null,null);
        rootTree.setTree(a,bTree,cTree,dTree);

        //Calling methods
        System.out.println("rootTree.isBalanced() returns: " +rootTree.isBalanced());
        System.out.println("rootTree.getRootData() returns: " + rootTree.getRootData());
        System.out.println("rootTree.isEmpty() returns: " + rootTree.isEmpty());
        System.out.println("rootTree.getHeight() returns: " + rootTree.getHeight());
        System.out.println("rootTree.getNumberOfNodes() returns: " + rootTree.getNumberOfNodes());

        //Iterator methods
        System.out.println("****Iterators*****");
        Iterator<Integer> preOrder = rootTree.getPreorderIterator();
        System.out.println("Preorder Iterator: ");
        int preCount = 1;
        while (preOrder.hasNext()) {
            System.out.println("next() number " + preCount + " = " + preOrder.next());
            preCount++;
        }


        for(int x = 1; x <=20; x++){
            System.out.println("rootTree.contains("+x+") returns: " + rootTree.contains(x));
        }


        System.out.println();
        try {
            Iterator<Integer> inOrder = rootTree.getInorderIterator();
        } catch (UnsupportedOperationException v) {
            System.out.println("UnsupportedOperationException: " + v.getMessage());
        }
        System.out.println("Inorder Iterator (unsupported)");

        System.out.println();
        Iterator<Integer> postOrder = rootTree.getPostorderIterator();
        System.out.println("Postorder Iterator: ");
        int postCount = 1;
        while (postOrder.hasNext()) {
            System.out.println("next() number " + postCount + " = " + postOrder.next());
            postCount++;
        }

        System.out.println();
        Iterator<Integer> levelOrder = rootTree.getLevelOrderIterator();
        System.out.println("LevelOrder Iterator: ");
        int levelCount = 1;
        while (levelOrder.hasNext()) {
            System.out.println("next() number " + levelCount + " = " + levelOrder.next());
            levelCount++;
        }

        System.out.println();
        System.out.println("Testing clear method");
        rootTree.clear();
        System.out.println("rootTree.isEmpty() returns: " + rootTree.isEmpty());
        try {
            System.out.println("rootTree.getRootData() returns: " + rootTree.getRootData());

        } catch (EmptyTreeException x) {
            System.out.println("EmptyTreeException: " + x.getMessage() + "(no message set for EmptyTreeException)");
        }
    }

}
