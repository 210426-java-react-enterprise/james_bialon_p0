package com.revature.p0.util.datastructs.linkedlist;

/**
 * Created by IntelliJ IDEA.
 * User: Jbialon
 * Date: 5/1/2021
 * Time: 3:21 PM
 * Description: {Insert Description}
 */
public class Test {
    public static void main(String[] args) {
        LinkedList tester = new LinkedList();

        System.out.println("LinkedList created...");

        tester.add("Node1");
        tester.add("Node2");
        tester.add("Node3");
        tester.add("Node4");
        tester.add("Node5");

        System.out.println("Nodes added successfully...");
        System.out.println();

        System.out.println("Traversal Tests");
        System.out.println("-=============-");
        System.out.println();

        System.out.println("-Forwards-");
        System.out.println();
        tester.traverse();
        System.out.println();

        System.out.println("-Backwords-");
        System.out.println();
        tester.reverseTraverse();

        System.out.println();
        System.out.println("Traversal: PASS");
        System.out.println();

        System.out.println("Pop Contain Tests");
        System.out.println("-===============-");
        System.out.println();

        System.out.println(tester.size());
        System.out.println(tester.contains("Node1"));
        System.out.println(tester.pop());
        System.out.println(tester.contains("Node1"));
        System.out.println(tester.size());

        System.out.println();
        System.out.println("Pop Contain: PASS");
    }
}
