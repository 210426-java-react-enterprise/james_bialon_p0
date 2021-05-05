package com.revature.p0.util.datastructs.linkedlist;

import com.revature.p0.util.iterator.DoubleIterator;


/**
 * Created by IntelliJ IDEA.
 * User: Jbialon
 * Date: 5/4/2021
 * Time: 3:27 PM
 * Description: {Insert Description}
 */
public class LinkedListNode<T> implements DoubleIterator {

    private T data;
    private LinkedListNode<T> nextNode;
    private LinkedListNode<T> prevNode;
    private int index;

    public LinkedListNode(int index, T data) {
        this.index = index;
        this.data = data;
    }

    public LinkedListNode(int index, T data, LinkedListNode<T> nextNode, LinkedListNode<T> prevNode) {
        this.index = index;
        this.data = data;
        this.nextNode = nextNode;
        this.prevNode = prevNode;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
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
