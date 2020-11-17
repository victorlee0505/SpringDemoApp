package com.example.demo.MiscAlg;

public class BinarySearch {

    public static boolean contains(Node root, int value) {

        // Base case
        if (value == root.value) {
            return true;
        }

        if (value > root.value) {
            if (root.right != null) {
                return contains(root.right, value);
            }
        } else {
            if (root.left != null) {
                return contains(root.left, value);
            }
        }

        return false;
    }

    public static void main(String[] args) {
        Node n1 = new Node(1, null, null);
        Node n3 = new Node(3, null, null);
        Node n2 = new Node(2, n1, n3);

        System.out.println(contains(n2, 3));
    }
}

class Node {
    public int value;
    public Node left, right;

    public Node(int value, Node left, Node right) {
        this.value = value;
        this.left = left;
        this.right = right;
    }
}