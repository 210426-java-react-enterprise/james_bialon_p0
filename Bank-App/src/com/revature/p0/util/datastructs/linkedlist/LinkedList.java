package com.revature.p0.util.datastructs.linkedlist;

import com.revature.p0.util.datastructs.list.List;

/**
 * Simple implementaion of a doubly linked list that will not take null data.
 *
 * @param <T>
 */
public class LinkedList<T> implements List<T> {

    private int size;
    private LinkedListNode<T> head;
    private LinkedListNode<T> tail;

    @Override
    public void add(T data) throws IllegalArgumentException {

        if (data == null) {
            throw new IllegalArgumentException("This linked list does not accept null values");
        }

        LinkedListNode<T> newNode = new LinkedListNode<T>(data);
        if (head == null) {
            tail = head = newNode; // sets both head and tail as the same new node
        } else {
            tail.setNextNode(newNode);
            newNode.setPrevNode(tail);
            tail = newNode;
        }

        size++;

    }

    @Override
    public T pop() {

        if(head == null) {
            return null;
        }

        T soughtData = head.getData();
        head = head.next();

        if (head != null) {
            head.setPrevNode(null);
        } else {
            tail = null;
        }

        size--;

        return soughtData;
    }

    @Override
    public boolean contains(T data) {
        LinkedListNode currNode = head;

        while (currNode.hasNext()) {
            if (currNode.getData().equals(data)) {
                return true;
            }

            currNode = currNode.next();
        }

        if (currNode.getData().equals(data)) {
            return true;
        }

        return false;
    }

    @Override
    public int size() {
        return size;
    }

    public void traverse() {
        LinkedListNode currNode = this.head;

        if (currNode != null) {
            while (currNode.hasNext()) {
                System.out.println(currNode.getData());
                currNode = currNode.next();
            }

            System.out.println(currNode.getData());
        } else {
            System.out.println("This list is empty.");
        }
    }

    public void reverseTraverse() {
        LinkedListNode currNode = this.tail;

        if (currNode != null) {
            while (currNode.hasPrev()) {
                System.out.println(currNode.getData());
                currNode = currNode.prev();
            }

            System.out.println(currNode.getData());
        } else {
            System.out.println("This list is empty.");
        }
    }
}
