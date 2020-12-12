import java.util.Iterator;

public class Deque<Item> implements Iterable<Item> {
    private class Node {
        Item item;
        Node next;
    }
    private Node first, last, beforeLast;
    private int size;

    // construct an empty deque
    public Deque() {
        first = null;
        last = null;
        beforeLast= null;
        size = 0;
    }

    // is the deque empty?
    public boolean isEmpty() {
        return first == null;
    }

    // return the number of items on the deque
    public int size() {
        return size;
    }

    // add the item to the front
    public void addFirst(Item item) {
        if (item == null)
            throw new IllegalArgumentException();

        Node oldFirst = first;
        first = new Node();
        first.item = item;
        first.next = oldFirst;

        size++;
        if (size == 1) {
            last = first;
        }
        else if (size == 2) {
            beforeLast = first;
        }
    }

    // add the item to the back
    public void addLast(Item item) {
        if (item == null)
            throw new IllegalArgumentException();

        Node oldLast = last;
        last = new Node();
        last.item = item;
        last.next = null;
        size++;
        if (oldLast == null) {
            first = last;
        }
        else {
            oldLast.next = last;
            beforeLast = oldLast;
        }
    }

    // remove and return the item from the front
    public Item removeFirst() {
        Item item = null;
        if (first != null) {
            item = first.item;
            size--;
            if (first != last) {
                if (beforeLast == first) {
                    beforeLast = null;
                }
                 first = first.next;
            }
            else {
                first = null;
                last = null;
            }   
        }
        else {
            throw new java.util.NoSuchElementException();
        }

        return item;
    }

    // remove and return the item from the back
    public Item removeLast() {
        Item item = null;
        if (first != null) {
            item = last.item;
            size--;
            if (beforeLast == first) {
                first.next = null;
                last = first;
                beforeLast = null;
            }
            else if (first == last) {
                first = null;
                last = null;
            }
            else {
                beforeLast.next = null;
                last = beforeLast;
            }

        }
        else {
            throw new java.util.NoSuchElementException();
        }
        return item;
    }

    // return an iterator over items in order from front to back
    public Iterator<Item> iterator() {
        return new DequeIterator();
    }

    private class DequeIterator implements Iterator<Item> {
        private Node current = first;

        public boolean hasNext() {
            return current != null;
        }

        public Item next() {
            if (isEmpty()) 
                throw new java.util.NoSuchElementException();

            Item item = current.item;
            current = current.next;

            return item;              
        }
        public void remove() {
            throw new UnsupportedOperationException();
        }
    }



    // unit testing (required)
    public static void main(String[] args) {
        Deque<Integer> deque = new Deque<>();

        deque.addFirst(1);
        deque.addLast(2);
        deque.addLast(3);
        deque.addFirst(4);
        System.out.println(deque.size());
        Iterator<Integer> element = deque.iterator();
        while (element.hasNext()) {
            System.out.println(element.next());
        }
    }

}