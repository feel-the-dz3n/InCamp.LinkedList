package com.yaroslav;

public class ListNode<T> {
    public T value;
    public ListNode<T> nextNode;
    public ListNode<T> prevNode;

    public ListNode(T value) {
        this.value = value;
    }
}
