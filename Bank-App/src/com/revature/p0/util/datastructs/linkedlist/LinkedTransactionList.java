package com.revature.p0.util.datastructs.linkedlist;

import com.revature.p0.models.account.AccountTransaction;
import com.revature.p0.util.datastructs.list.TransactionList;

/**
 * Simple implementaion of a doubly linked list that will not take null data.
 *
 * @param <T>
 */
public class LinkedTransactionList<T> implements TransactionList {
    private int size;
    private LinkedTransactionListNode<T> head;
    private LinkedTransactionListNode<T> tail;

    @Override
    public void add(AccountTransaction data) throws IllegalArgumentException {

        if (data == null) {
            throw new IllegalArgumentException("This linked list does not accept null values");
        }

        LinkedTransactionListNode<T> newNode = new LinkedTransactionListNode<T>(size, data);
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
    public AccountTransaction pop() {

        if(head == null) {
            return null;
        }

        AccountTransaction soughtData = head.getData();
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
    public int size() {
        return size;
    }

    @Override
    public void traverse() {
        LinkedTransactionListNode<T> currNode = this.head;

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

    @Override
    public void reverseTraverse() {
        LinkedTransactionListNode<T> currNode = this.tail;

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

    @Override
    public AccountTransaction getByAmount(double amt) {
        LinkedTransactionListNode currNode = head;

        if (currNode != null) {
            while (currNode.hasNext()) {
                if (Double.compare(currNode.getData().getTransactionAmt(), amt) == 0) {
                    return currNode.getData();
                }

                currNode = currNode.next();
            }

            if (Double.compare(currNode.getData().getTransactionAmt(), amt) == 0) {
                return currNode.getData();
            }
        }

        return null;
    }

}
