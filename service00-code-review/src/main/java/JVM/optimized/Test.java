package JVM.optimized;

import java.util.LinkedList;
import java.util.Queue;

public class Test {
    public static void main(String[] args) {
        Queue<int[]> queue = new LinkedList<int[]>();
        while (true) {
            int[] tmp = new A().execute();
            if (queue.size() > 1000) {
                queue.poll();
            } else {
                queue.offer(tmp);
            }
        }
    }
}
