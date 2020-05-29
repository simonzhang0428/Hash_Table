/**
 * List.java
 * Lab2: Doubly-Linked List
 *
 * @author Simon Zhang
 */

import java.util.*;

public class List<T> {

    private class Node {
        private T data;
        private Node next;
        private Node prev;

        public Node(T data) {
            this.data = data;
            this.next = null;
            this.prev = null;
        }
    }

    private int length;
    private Node first;
    private Node last;
    private Node iterator;

    /*CONSTRUCTORS*/

    /**
     * Instantiates a new List with default values
     * @postcondition guarantee to give a new List with default values
     */
    public List() {
        length = 0;
        first = null;
        last = null;
        iterator = null;
    }

    /**
     * Copy constructor
     * @param original the list to make copy of
     * @postcondition a new list object, which is identical
     * but separate copy of the list original
     */
    public List(List<T> original) {
        if (original == null) {
            return;
        } else if (original.isEmpty()) {
            length = 0;
            first = null;
            last = null;
            iterator = null;
        } else {
            Node temp = original.first;
            while (temp != null) {
                addLast(temp.data);
                temp = temp.next;
            }
            iterator = null;
        }
    }



    /*ACCESSORS*/

    /**
     * Return whether or not the iterator if off the end of the list
     * @return whether or not the iterator if off the end of the list
     */
    public boolean offEnd() {
        return iterator == null;
    }

    /**
     * Return the data stored by the Node pointed by the iterator
     * @precondition iterator != null
     * @return the data stored by the Node pointed by the iterator
     * @throws NoSuchElementException when precondition is violated
     */
    public T getIterator() throws NullPointerException {
        if (iterator == null) {
            throw new NullPointerException("getIterator: iterator point to null, cannot access!");
        } else {
            return iterator.data;
        }
    }

    /**
     * Return the value stored in the first node
     * @precondition the List can not be empty/ length != 0/ first != null/ !isEmpty()
     * @return the value stored at node first
     * @throws NoSuchElementException when precondition is violated
     */
    public T getFirst() throws NoSuchElementException {
        if (length == 0) {
            throw new NoSuchElementException("getFirst(): List is Empty. No data to access!");
        }
        return first.data;
    }

    /**
     * Return the value stored in the node last
     * @precondition the List can not be empty
     * @return the value stored in the node last
     * @throws NoSuchElementException when precondition is violated
     */
    public T getLast() throws NoSuchElementException {
        if (length == 0) {
            throw new NoSuchElementException("getLast(): List is Empty. No data to access!");
        }
        return last.data;
    }

    /**
     * Return current length of the list
     * @return the length of the list from 0 to n
     */
    public int getLength() {
        return length;
    }

    /**
     * Return whether the list is currently empty
     * @return whether the list is currently empty
     */
    public boolean isEmpty() {
        return length == 0;
    }

    /**
     * Determines whether two Lists have the same data
     * in the same order
     * @param o the List to compare to this List
     * @return whether the two Lists are equal
     */
    @SuppressWarnings("unchecked")
    @Override
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        } else if (!(o instanceof List)) {
            return false;
        } else {
            List<T> L = (List<T>) o;
            if (this.length != L.getLength()) {
                return false;
            } else {
                Node temp1 = this.first;
                Node temp2 = L.first;
                while (temp1 != null) {
                    if (temp1.data != temp2.data) {
                        return false;
                    }
                    temp1 = temp1.next;
                    temp2 = temp2.next;
                }
                return true;
            }
        }
    }


    /*MUTATORS*/

    /**
     * Invoke iterator, place it point to first node
     */
    public void placeIterator() {
        iterator = first;
    }


    /**
     * Advances the iterator by one node in the List
     * @precondition iterator != null
     * @throws NullPointerException when precondition is violated
     */
    public void advanceIterator() throws NullPointerException {
        if (iterator == null) {
            throw new NullPointerException("advanceIterator: iterator point to null, cannot advance!");
        } else {
            iterator = iterator.next;
        }
    }

    /**
     * Move the iterator down by one node
     * @precondition !offend()
     * @throws NullPointerException when precondition is violated
     */
    public void reverseIterator() throws NullPointerException {
        if (offEnd()) {
            throw new NullPointerException("reverseIterator: iterator is null, cannot reverse!");
        } else {
            iterator = iterator.prev;
        }
    }


    /**
     * Adds a Node following the one currently being referenced by the iterator
     * @precondition iterator != null
     * @param data the data in the node to be add
     * @throws NullPointerException when precondition is violated
     */
    public void addIterator(T data) throws NullPointerException {
        if (offEnd()) {
            throw new NullPointerException("addIterator: iterator is null, cannot add!");
        } else if (iterator == last) { // edge case, iterator point to last node
            addLast(data);
        } else {
            Node p = new Node(data);
            p.next = iterator.next;
            iterator.next.prev = p;
            iterator.next = p;
            p.prev = iterator;
            length++;
        }
    }

    /**
     * Removes the element currently pointed to by the iterator.
     * @precondition !offEnd()
     * @throws NullPointerException when precondition is violated
     * @postcondition iterator set to null
     */
    public void removeIterator() throws NullPointerException {
        if (offEnd()) {
            throw new NullPointerException("removeIterator: iterator is null, cannot remove!");
        } else if (iterator == first) {
            removeFirst();
        } else if (iterator == last) {
            removeLast();
        } else {
            iterator.prev.next = iterator.next;
            iterator.next.prev = iterator.prev;
            length--;
        }
        iterator = null;

    }


    /**
     * Create a new first element
     * @param data the data to insert at the front of the list
     * @postcondition a new first node is created
     */
    public void addFirst(T data) {
        if (length == 0) {
            first = last = new Node(data);
        } else {
            Node p = new Node(data);
            p.next = first;
            first.prev = p;
            first = p;
        }
        length++;
    }

    /**
     * Creates a new last element
     * @param data the data to insert at the
     * end of the list
     * @postcondition a new last node is created
     */
    public void addLast(T data) {
        if (length == 0) {
            first = last = new Node(data);
        } else {
            Node p = new Node(data);
            last.next = p;
            p.prev = last;
            last = p;
        }
        length++;
    }

    /**
     * removes the element at the front of the list
     * @precondition the list can not be empty
     * @postcondition original first node is removed, total length decrease by 1
     * @throws NoSuchElementException when precondition is violated
     */
    public void removeFirst() throws NoSuchElementException {
        if (length == 0) {
            throw new NoSuchElementException("removeFirst(): Cannot remove from an empty List!");
        } else if (length == 1) {
            first = last = null;
        } else {
            first = first.next;
            first.prev = null;
        }
        length--;
    }

    /**
     * removes the element at the end of the list
     * @precondition the list can not be empty
     * @postcondition original last node is removed, total length decrease by 1
     * @throws NoSuchElementException when precondition is violated
     */
    public void removeLast() throws NoSuchElementException {
        if (length == 0) {
            throw new NoSuchElementException("removeLast(): Cannot remove from an empty List!");
        } else if (length == 1) {
            first = last = null;
        } else {
            last = last.prev;
            last.next = null;
        }
        length--;
    }

    /****ADDITIONAL OPERATIONS****/

    /**
     * List with each value on its own line
     * At the end of the List a new line
     * @return the List as a String for display
     */
    @Override
    public String toString() {
        String display = "";
        Node p = first;
        while (p != null) {
            display = display + p.data + " ";
            p = p.next;
        }
        return display;
    }

    /**
     * Prints the contents of the linked list to the screen
     * in the format #. <element> followed by a newline
     */
    public void printNumberedList() {
        this.placeIterator();
        for (int i = 0; i < getLength(); i++) {
            System.out.println((i + 1) + ". " + getIterator());
            this.advanceIterator();
        }
    }


}
