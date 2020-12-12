import java.util.Iterator;

import edu.princeton.cs.algs4.StdRandom;


public class RandomizedQueue<Item> implements Iterable<Item> {

    private Item[] s;
    private int N = 0;

    // construct an empty randomized queue
    public RandomizedQueue() {
        s = (Item[]) new Object[2];
    }

    // is the randomized queue empty?
    public boolean isEmpty() {
        return N == 0;
    }

    // return the number of items on the randomized queue
    public int size() {
        return N;
    }

    // add the item
    public void enqueue(Item item) {
        if (item == null) 
            throw new java.lang.IllegalArgumentException ();
        if (N == s.length) 
            resize( 2 * N);
        s[N]=item;
        N++;
    }

    private void resize(int capacity) {
        Item[] temp = (Item[]) new Object[capacity];
        for (int i = 0; i < N; i++) {
          temp[i] = s[i];
        }
        s = temp;
    }

    // remove and return a random item
    public Item dequeue() {
        if (N == 0) 
            throw new java.util.NoSuchElementException();

        int r = StdRandom.uniform(N);
        Item item = s[r];
        s[r] = s[N-1];
        s[N-1] = null;
        N--;

        if (N > 0 && N == s.length / 4) 
            resize(s.length / 2);
        return item;
    }

    // return a random item (but do not remove it)
    public Item sample() {
        if (N == 0) 
            throw new java.util.NoSuchElementException();
        
        int r = StdRandom.uniform(N);
        Item item = s[r];
        return item;
    }

    // return an independent iterator over items in random order
    public Iterator<Item> iterator() {
        return new RandomizedQueueIterator();
    }

    private class RandomizedQueueIterator implements Iterator<Item> {
        private int current = 0;

        public boolean hasNext() {
            return current < N;
        }

        public Item next() {
            if (isEmpty() || current > N)
                throw new java.util.NoSuchElementException();

            Item item = s[current];
            current++;

            return item;              
        }

        public void remove() {
            throw new UnsupportedOperationException();
        }
    }
    // unit testing (required)
    public static void main(String[] args) {
        int ret;
        RandomizedQueue<Integer> randomized_queue = new RandomizedQueue<Integer>();

        randomized_queue.enqueue(1);
        randomized_queue.enqueue(2);
        randomized_queue.enqueue(3);
        ret = randomized_queue.sample();
        ret = randomized_queue.dequeue();

        Iterator<Integer> element = randomized_queue.iterator();
        while (element.hasNext()) {
            System.out.println(element.next());
        }
    }

}