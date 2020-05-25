/**
 * Class to test List.java
 *
 * @author Simon Zhang
 */

import java.util.NoSuchElementException;

public class ListTest {
    public static void main(String[] args) {
        List<Integer> L = new List<>();
        System.out.println("Should print nothing (an empty list): " + L);

        System.out.println("**Testing addFirst**");
        L.addFirst(2); //Testing Edge case: length == 0
        System.out.println("Should print 2: " + L);
        L.addFirst(1); //Testing General case: length >= 1
        System.out.println("Should print 1 2: " + L);

        System.out.println("**Testing getFirst**");
        System.out.println("Should print 1: " + L.getFirst()); //testing general case
        List<String> L3 = new List<>();
        System.out.println("Should an error message for an empty List: ");
        try { //testing precondition
            System.out.println("Should print error: " + L3.getFirst());
        } catch (NoSuchElementException e) {
            System.out.println(e.getMessage() + "\n");
        }

        System.out.println("**Testing addLast**");
        L = new List<>();
        L.addLast(1); //Testing Edge case: length == 0
        System.out.println("Should print 1: " + L);
        L.addLast(2); //Testing General case: length >= 1
        System.out.println("Should print 1 2: " + L);

        System.out.println("**Testing getLast**");
        System.out.println("Should print 2: " + L.getLast()); //testing general case
        List<String> L4 = new List<>();
        System.out.println("Should an error message for an empty List: ");
        try { //testing precondition
            System.out.println("Should print error: " + L4.getLast());
        } catch (NoSuchElementException e) {
            System.out.println(e.getMessage() + "\n");
        }

        System.out.println("**Testing removeFirst**");
        L.removeFirst(); //Testing General case: length >1
        System.out.println("Should print 2: " + L);
        L.removeFirst(); //Testing Edge case: length == 1
        System.out.println("Should print an empty List: " + L);
        System.out.println("Should an error message for an empty List: ");
        try { //place in a try-catch so program does not end when exception thrown
            L.removeFirst(); //Testing Precondition: length == 0
        } catch (NoSuchElementException e) {
            System.out.println(e.getMessage() + "\n");
        }

        L.addFirst(1);
        L.addLast(2);
        System.out.println("**Testing removeLast**");
        L.removeLast(); //Testing General case: length >1
        System.out.println("Should print 1: " + L);
        L.removeLast(); //Testing Edge case: length == 1
        System.out.println("Should print an empty List: " + L);
        System.out.println("Should an error message for an empty List: ");
        try { //place in a try-catch so program does not end when exception thrown
            L.removeLast(); //Testing Precondition: length == 0
        } catch (NoSuchElementException e) {
            System.out.println(e.getMessage() + "\n");
        }

        System.out.println("**Testing isEmpty**");
        List<Integer> L2 = new List<>();
        System.out.println("Should print true: " + L2.isEmpty());
        L2.addLast(1);
        System.out.println("Should print false: " + L2.isEmpty() + "\n");

        System.out.println("**Testing getLength**");
        System.out.println("Should print 1: " + L2.getLength());
        L2.removeFirst();
        System.out.println("Should print 0: " + L2.getLength() + "\n");

        System.out.println("**Testing getIterator**");
        try {
            L.getIterator();
        } catch (NullPointerException e) {
            System.out.println("Should an error massage for null iterator: ");
            System.out.println(e.getMessage());
        }
        L.placeIterator();
        try {
            L.getIterator();
        } catch (NullPointerException e) {
            System.out.println("Should an error massage for null iterator although place it: ");
            System.out.println(e.getMessage());
        }
        L.addLast(1);
        L.placeIterator();
        System.out.println("Should print 1: " + L.getIterator() + "\n");

        System.out.println("**Testing advanceIterator**");
        L.addLast(2);
        L.advanceIterator();
        System.out.println("Should print 2: " + L.getIterator());
        L.advanceIterator(); // OK to let iterator go to null
        try {
            L.advanceIterator();
        } catch (NullPointerException e) {
            System.out.println("Should print an error message for advanceIterator: ");
            System.out.println(e.getMessage() + "\n");
        }

        System.out.println("**Testing reverseIterator**");
        System.out.println("Should print 1 2: " + L);
        L.placeIterator();
        L.reverseIterator();
        try {
            L.reverseIterator();
        } catch (NullPointerException e) {
            System.out.println("Should print an error message for reverseIterator: ");
            System.out.println(e.getMessage());
        }
        L.placeIterator();
        L.advanceIterator();
        L.reverseIterator();
        System.out.println("where is the iterator? (1): " + L.getIterator() + "\n");

        System.out.println("**Testing offEnd**");
        System.out.println("Should print true: " + L.offEnd());
        L.placeIterator();
        L.advanceIterator();
        System.out.println("Should print false: " + L.offEnd());
        System.out.println("Should print 1 2: " + L + "\n");

        System.out.println("**Testing addIterator**");
        L = new List<>();
        try {
            L.addIterator(4);
        } catch (NullPointerException e) {
            System.out.println("Should print error message for null iterator, no place to add");
            System.out.println(e.getMessage());
        }
        L.addLast(1);
        L.placeIterator();
        L.addIterator(2);
        System.out.println("Edge case, iterator point to last node:");
        System.out.println("Last node? (2): " + L.getLast());
        System.out.println("where is iterator? (1): " + L.getIterator());
        System.out.println("size? (2): " + L.getLength());
        System.out.println("1 2: " + L);
        L.addIterator(3);
        System.out.println("General case, Last node? (2) " + L.getLast());
        System.out.println("1 3 2: " + L);
        System.out.println("where is iterator? (1): " + L.getIterator());
        System.out.println("The size should be 3: " + L.getLength() + "\n");

        System.out.println("**Testing removeIterator**");
        L = new List<>(); // null case
        try {
            L.removeIterator();
        } catch (NullPointerException e) {
            System.out.println("Should print error message for null iterator: ");
            System.out.println(e.getMessage());
        }
        L.addFirst(1); // iterator point at first
        L.addLast(2);
        L.placeIterator();
        L.removeIterator();
        System.out.println("Should print 2: " + L);
        try {
            L.getIterator();
        } catch (NullPointerException e) {
            System.out.println("Should get error message for null iterator: ");
            System.out.println(e.getMessage());
        }
        L.addLast(3); // iterator point to last
        L.placeIterator();
        L.advanceIterator();
        L.removeIterator();
        System.out.println("Should print 2: " + L);
        L.addFirst(1);// general case
        L.addLast(3);
        L.placeIterator();
        L.advanceIterator();
        L.removeIterator();
        System.out.println("Should print 1 3: " + L + "\n");

        System.out.println("**Testing copy constructor**");
        List<String> L5 = new List<>();
        List<String> Lcopy = new List<>(L5);
        System.out.println("Should print nothing: " + L5);

        for (int i = 0; i < 10; i++) {
            L5.addLast("" + i);
        }
        Lcopy = new List<>(L5);
        System.out.println("Print Lcopy: " + Lcopy + "\n");

        System.out.println("**Testing equals**");
        List<Integer> L6 = new List<>();
        List<Integer> L7 = new List<>();
        List<String> L8 = new List<>();
        System.out.println("Should print true: " + L6.equals(L8));
        System.out.println("Should print true: " + L6.equals(L7));
        L6.addLast(1);
        System.out.println("Should print false: " + L6.equals(L7));
        L7.addLast(1);
        System.out.println("Should print true: " + L6.equals(L7));
        L8.addLast("one");
        System.out.println("Should print false: " + L8.equals(L7) + "\n");

        System.out.println("**Testing printNumberedList**");
        L8.removeFirst();
        System.out.println("Should print nothing: ");
        L8.printNumberedList();
        L8.addLast("two");
        L8.addLast("three");
        L8.printNumberedList();

        List<Character> list = new List<>();
        list.addLast('A');
        list.addLast('B');
        list.addLast('C');
        list.addLast('D');
        list.addLast('E');
        list.addLast('F');
        list.addLast('G');
        System.out.println("Should print A B C D E F G: "+ list);
        //write a swap routine to swap the third and fifth values in the List
        // (i.e. 'C' and 'E'). Your swap routine does not need to be written as a method,
        // but it must call advanceIterator, addIterator, getIterator, placeIterator, and removeIterator
        // and no other methods - or use any other structures (array, ArrayList, etc.) to perform the swap.
        list.placeIterator();
        for (int i = 0; i < 4; i++) {
            list.advanceIterator();
        }
        char e = list.getIterator();
        list.removeIterator();
        System.out.println("e is E: " + e);
        System.out.println("A B C D F G: " + list);
        list.placeIterator();
        list.advanceIterator();
        list.addIterator(e);
        System.out.println("A B E C D F G: " + list);
        System.out.println("Where is iterator? (B): " + list.getIterator());
        list.advanceIterator();
        list.advanceIterator();
        char c = list.getIterator();
        System.out.println("c is C: " + list.getIterator());
        list.removeIterator();
        System.out.println("A B E D F G: " + list);
        list.placeIterator();
        for (int i = 0; i < 3; i++) {
            list.advanceIterator();
        }
        list.addIterator(c);
        System.out.println("A B E D C F G: " + list);






    }


}