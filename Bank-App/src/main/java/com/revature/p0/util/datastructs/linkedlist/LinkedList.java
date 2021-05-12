package com.revature.p0.util.datastructs.linkedlist;

import com.revature.p0.util.datastructs.queue.Queue;
import com.revature.p0.util.datastructs.list.List;

/**
 * Created by IntelliJ IDEA.
 * User: Jbialon
 * Date: 5/4/2021
 * Time: 3:24 PM
 * Description: {Insert Description}
 */
public class LinkedList<T> implements List<T>, Queue<T> {

    private int size;
    private LinkedListNode<T> head;
    private LinkedListNode<T> tail;

    public void add(T data) throws IllegalArgumentException {

        if (data == null) {
            throw new IllegalArgumentException("This linked list does not accept null values");
        }

        LinkedListNode<T> newNode = new LinkedListNode<T>(size, data);
        if (head == null) {
            tail = head = newNode; // sets both head and tail as the same new node
        } else {
            tail.setNextNode(newNode);
            newNode.setPrevNode(tail);
            tail = newNode;
        }

        size++;

    }


    public int size() {
        return size;
    }


    @Override
    public T get(int index) {
        if (index < 0 || index > size) {
            throw new IllegalArgumentException("The provided index would be out of bounds.");
        }

        LinkedListNode<T> runner = head;
        for (int i = 0; i < size; i++) {
            if (i == index) {
                return runner.getData();
            }
            runner = runner.next();
        }

        return null;
    }

    @Override
    public T poll() {

        if (head == null) {
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

}
