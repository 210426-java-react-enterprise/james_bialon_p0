package com.revature.p0.util.datastructs.linkedlist;

import com.revature.p0.models.account.AccountTransaction;
import com.revature.p0.util.datastructs.list.List;

/**
 * Created by IntelliJ IDEA.
 * User: Jbialon
 * Date: 5/4/2021
 * Time: 3:24 PM
 * Description: {Insert Description}
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

    @Override
    public boolean contains(T data) {
        return false;
    }

    @Override
    public int size() {
        return size;
    }

}
