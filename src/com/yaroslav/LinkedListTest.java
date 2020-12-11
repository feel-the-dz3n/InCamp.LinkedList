package com.yaroslav;

import static org.junit.jupiter.api.Assertions.*;

class LinkedListTest {

    @org.junit.jupiter.api.Test
    void forEach() {
        LinkedList<String> list = new LinkedList<>();

        list.add("one").add("two").add("three");

        StringBuilder b = new StringBuilder();
        list.forEach((t) -> b.append(t + " "));

        assertEquals("one two three ", b.toString());
    }

    @org.junit.jupiter.api.Test
    void add() {
        LinkedList<String> list = new LinkedList<>();

        list.add("one");
        list.add("two");

        assertEquals(2, list.getSize());
        assertEquals("one", list.getFirst());
        assertEquals("two", list.getLast());
    }

    @org.junit.jupiter.api.Test
    void addClass() {
        LinkedList<Person> list = new LinkedList<>();

        list.add(new Person("Yaroslav", 18));
        list.add(new Person("Somebody", 22));

        assertEquals(2, list.getSize());
        assertEquals("Yaroslav", list.getFirst().name);
        assertEquals("Somebody", list.getLast().name);
    }

    @org.junit.jupiter.api.Test
    void getValueAt() throws Exception {
        LinkedList<String> list = new LinkedList<>();

        list.add("one");
        list.add("two");
        list.add("three");
        list.add("four");
        list.add("five");
        list.add("six");

        assertEquals("two", list.getValueAt(1));
        assertEquals("five", list.getValueAt(4));
    }

    @org.junit.jupiter.api.Test
    void insert() throws Exception {
        LinkedList<String> list = new LinkedList<>();

        list.add("one"); // 0
        list.add("three"); // 1

        list.insert("two", 1);
        list.insert("zero", 0);

        StringBuilder b = new StringBuilder();
        list.forEach((t) -> b.append(t + " "));

        assertEquals(4, list.getSize());
        assertEquals("zero one two three ", b.toString());
    }

    @org.junit.jupiter.api.Test
    void removeAt() throws Exception {
        LinkedList<String> list = new LinkedList<>();

        list.add("one");
        list.add("two");
        list.add("three");
        list.add("four");

        StringBuilder b = new StringBuilder();
        list.forEach((t) -> b.append(t + " "));
        assertEquals(4, list.getSize());
        assertEquals("one two three four ", b.toString());

        list.removeAt(0); // one
        list.removeAt(1); // three

        StringBuilder b2 = new StringBuilder();
        list.forEach((t) -> b2.append(t + " "));

        assertEquals(2, list.getSize());
        assertEquals("two four ", b2.toString());
    }

    @org.junit.jupiter.api.Test
    void addAndRemoveALot() throws Exception {
        LinkedList<String> list = new LinkedList<>();

        for (int i = 0; i < 5000; i++)
            list.add("item " + i);

        while (list.getSize() != 0)
            list.removeAt(0);
    }

    @org.junit.jupiter.api.Test
    void removeAtAll() throws Exception {
        LinkedList<String> list = new LinkedList<>();

        list.add("one");
        list.add("two");
        list.add("three");
        list.add("four");

        StringBuilder b = new StringBuilder();
        list.forEach((t) -> b.append(t + " "));
        assertEquals(4, list.getSize());
        assertEquals("one two three four ", b.toString());

        list.removeAt(0); // one
        list.removeAt(1); // three
        list.removeAt(0); // two
        list.removeAt(0); // four

        StringBuilder b2 = new StringBuilder();
        list.forEach((t) -> b2.append(t + " "));

        assertEquals(0, list.getSize());
        assertEquals("", b2.toString());
    }

    @org.junit.jupiter.api.Test
    void replace() throws Exception {
        LinkedList<String> list = new LinkedList<>();

        list.add("one"); // 0
        list.add("two"); // 1

        StringBuilder b = new StringBuilder();
        list.forEach((t) -> b.append(t + " "));
        assertEquals(2, list.getSize());
        assertEquals("one two ", b.toString());

        list.replace("first", 0);
        list.replace("second", 1);

        StringBuilder b2 = new StringBuilder();
        list.forEach((t) -> b2.append(t + " "));

        assertEquals(2, list.getSize());
        assertEquals("first second ", b2.toString());
    }
}