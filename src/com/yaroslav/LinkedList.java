package com.yaroslav;

import java.util.function.Consumer;

public class LinkedList<T> {
    private ListNode<T> head;
    private ListNode<T> tail;
    private int size;

    public int getSize() {
        return size;
    }

    public T getFirst() {
        return head.value;
    }

    public T getLast() {
        return tail.value;
    }

    public void forEach(Consumer<T> consumer) {
        for (ListNode<T> node = head; node != null; node = node.nextNode)
            consumer.accept(node.value);
    }

    private ListNode<T> getNodeAt(int index) throws Exception {
        if (index < 0 || index >= size) throw new Exception("Invalid index");

        var startFromHead = size < 4 || index < (size - 1) / 2;
        int i = startFromHead ? 0 : size - 1;
        ListNode<T> currentNode = startFromHead ? head : tail;

        while (currentNode != null) {
            if (i == index) return currentNode;

            currentNode = startFromHead ? currentNode.nextNode : currentNode.prevNode;

            if (startFromHead) i++;
            else i--;
        }

        throw new Exception("Node not found");
    }

    public T getValueAt(int index) throws Exception {
        return getNodeAt(index).value;
    }

    public LinkedList<T> add(T value) {
        ListNode<T> node = new ListNode<>(value);

        if (head == null) // no 1st node
            head = node; // 1st = current

        if (tail == null) { // no last node
            head.nextNode = node; // 1st.next = this
        } else { // replace last node
            node.prevNode = tail; // current.prev = last
            tail.nextNode = node; // last.next = current
        }

        tail = node; // last = current
        size++;

        return this;
    }

    public void insert(T value, int index) throws Exception {
        ListNode<T> insertedNode = new ListNode<>(value);
        ListNode<T> currentNode = getNodeAt(index);

        insertedNode.prevNode = currentNode.prevNode;
        insertedNode.nextNode = currentNode;

        if (currentNode.prevNode != null)
            currentNode.prevNode.nextNode = insertedNode;

        currentNode.prevNode = insertedNode;

        if (index == 0) head = insertedNode;
        else if (index == size - 1) tail = insertedNode;

        size++;
    }

    public void removeAt(int index) throws Exception {
        ListNode<T> currentNode = getNodeAt(index);

        if (currentNode.prevNode != null)
            currentNode.prevNode.nextNode = currentNode.nextNode;
        else // no prev node, this is the 1st one
            head = currentNode.nextNode;

        if (currentNode.nextNode != null) {
            currentNode.nextNode.prevNode = currentNode.prevNode;
        } else // no next node, this is the last one
            tail = currentNode.prevNode;

        size--;
    }

    public void replace(T newValue, int index) throws Exception {
        ListNode<T> newNode = new ListNode<>(newValue);
        ListNode<T> currentNode = getNodeAt(index);

        if (currentNode.prevNode != null) {
            currentNode.prevNode.nextNode = newNode;
            newNode.prevNode = currentNode.prevNode;
        }

        if (currentNode.nextNode != null) {
            currentNode.nextNode.prevNode = newNode;
            newNode.nextNode = currentNode.nextNode;
        }

        if (index == 0) head = newNode;
        else if (index == size - 1) tail = newNode;
    }
}
