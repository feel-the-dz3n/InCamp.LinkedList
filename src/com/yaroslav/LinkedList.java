package com.yaroslav;

import java.util.function.Consumer;

public class LinkedList {
    private ListNode head;
    private ListNode tail;
    private int size;

    public int getSize() {
        return size;
    }

    public String getFirst() {
        return head.value;
    }

    public String getLast() {
        return tail.value;
    }

    public void forEach(Consumer<String> consumer) {
        for (ListNode node = head; node != null; node = node.nextNode)
            consumer.accept(node.value);
    }

    private ListNode getNodeAt(int index) throws Exception {
        if (index < 0 || index >= size) throw new Exception("Invalid index");

        int i = 0;
        for (ListNode currentNode = head; currentNode != null; currentNode = currentNode.nextNode, i++) {
            if (i == index) { // found required index
                return currentNode;
            }
        }

        throw new Exception("Node not found");
    }

    public String getValueAt(int index) throws Exception {
        return getNodeAt(index).value;
    }

    public void add(String value) {
        ListNode node = new ListNode(value);

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
    }

    public void insert(String value, int index) throws Exception {

        ListNode insertedNode = new ListNode(value);
        ListNode currentNode = getNodeAt(index);

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
        ListNode currentNode = getNodeAt(index);

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

    public void replace(String newValue, int index) throws Exception {
        ListNode newNode = new ListNode(newValue);
        ListNode currentNode = getNodeAt(index);
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
