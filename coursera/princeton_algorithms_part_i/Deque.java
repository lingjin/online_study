import java.util.Iterator;

public class Deque<Item> implements Iterable<Item> {
    private class Node {
        Item item;
        Node next;
        Node prev;
    }
    private Node first, last;
    private int size;

    // construct an empty deque
    public Deque() {
        first = null;
        last = null;
        size = 0;
    }

    // is the deque empty?
    public boolean isEmpty() {
        return size == 0;
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
        first.prev = null;

        if (oldFirst != null)
            oldFirst.prev = first;
        else
            last = first;

        size++;
    }

    // add the item to the back
    public void addLast(Item item) {
        if (item == null)
            throw new IllegalArgumentException();

        Node oldLast = last;
        last = new Node();
        last.item = item;
        last.next = null;
        last.prev = oldLast;

        if (oldLast != null)
            oldLast.next = last;
        else
            first = last;

        size++;
    }

    // remove and return the item from the front
    public Item removeFirst() {
        Item item = null;
        if (first != null) {
            item = first.item;

            if (first.next != null)
                first.next.prev = null;
            else
                last = null;
            first = first.next;
            size--;
        }
        else {
            throw new java.util.NoSuchElementException();
        }

        return item;
    }

    // remove and return the item from the back
    public Item removeLast() {
        Item item = null;
        if (last != null) {
            item = last.item;
            

            if (last.prev != null)
                last.prev.next = null;
            else
                first = null;
            last = last.prev;
            size--;
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
            if (current == null) 
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

        deque.addFirst(4);
        deque.addFirst(3);
        deque.addFirst(2);
        deque.addFirst(1);
        Integer r = deque.removeLast();
        r = deque.removeLast();
        r = deque.removeLast();
        r = deque.removeLast();
        r = deque.removeLast();
        System.out.println(deque.size());
        Iterator<Integer> element = deque.iterator();
        while (element.hasNext()) {
            System.out.println(element.next());
        }
    }

}