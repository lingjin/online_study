import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

public class Permutation {
    public static void main(String[] args) {
        int N = Integer.parseInt(args[0]);
        RandomizedQueue<String> randomized_queue = new RandomizedQueue<String>();

        while (!StdIn.isEmpty()) {
            String item = StdIn.readString();
            if (item != null && item.length() > 0)
                randomized_queue.enqueue(item);
        }
        
        while(randomized_queue.size() > 0 && N > 0) {
            StdOut.println(randomized_queue.dequeue());
            N--;
        }

    }
}
