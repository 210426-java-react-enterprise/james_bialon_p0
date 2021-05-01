package com.revature.p0.util.datastructs.linkedlist;

import com.revature.p0.util.iterator.DoubleIterator;

/**
 * Created by IntelliJ IDEA.
 * User: Jbialon
 * Date: 5/1/2021
 * Time: 2:02 PM
 * Description: {Insert Description}
 */
public class LinkedListNode<T> implements DoubleIterator<LinkedListNode<T>> {
    private T data;
    private LinkedListNode<T> nextNode;
    private LinkedListNode<T> prevNode;

    public LinkedListNode(T data) {
        this.data = data;
    }

    public LinkedListNode(T data, LinkedListNode<T> nextNode, LinkedListNode<T> prevNode) {
        this.data = data;
        this.nextNode = nextNode;
        this.prevNode = prevNode;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public void setNextNode(LinkedListNode<T> nextNode) {
        this.nextNode = nextNode;
    }

    public void setPrevNode(LinkedListNode<T> prevNode) {
        this.prevNode = prevNode;
    }

    @Override
    public boolean hasNext() {
        boolean returnValue = false;

        if (this.nextNode != null) {
            returnValue = true;
        }

        return returnValue;
    }

    @Override
    public LinkedListNode<T> next() {
        return this.nextNode;
    }

    @Override
    public boolean hasPrev() {
        boolean returnValue = false;

        if (this.prevNode != null) {
         returnValue = true;
        }

        return returnValue;
    }

    @Override
    public LinkedListNode<T> prev() {
        return prevNode;
    }
}

