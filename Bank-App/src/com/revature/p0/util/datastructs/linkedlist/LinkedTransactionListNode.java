package com.revature.p0.util.datastructs.linkedlist;

import com.revature.p0.models.account.AccountTransaction;
import com.revature.p0.util.iterator.DoubleIterator;

/**
 * Created by IntelliJ IDEA.
 * User: Jbialon
 * Date: 5/1/2021
 * Time: 2:02 PM
 * Description: {Insert Description}
 */
public class LinkedTransactionListNode<T> implements DoubleIterator<LinkedTransactionListNode<T>> {
    private AccountTransaction data;
    private LinkedTransactionListNode<T> nextNode;
    private LinkedTransactionListNode<T> prevNode;
    private int index;

    public LinkedTransactionListNode(int index, AccountTransaction data) {
        this.index = index;
        this.data = data;
    }

    public LinkedTransactionListNode(int index, AccountTransaction data, LinkedTransactionListNode<T> nextNode, LinkedTransactionListNode<T> prevNode) {
        this.index = index;
        this.data = data;
        this.nextNode = nextNode;
        this.prevNode = prevNode;
    }

    public AccountTransaction getData() {
        return data;
    }

    public void setData(AccountTransaction data) {
        this.data = data;
    }

    public void setNextNode(LinkedTransactionListNode<T> nextNode) {
        this.nextNode = nextNode;
    }

    public void setPrevNode(LinkedTransactionListNode<T> prevNode) {
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
    public LinkedTransactionListNode<T> next() {
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
    public LinkedTransactionListNode<T> prev() {
        return prevNode;
    }
}

