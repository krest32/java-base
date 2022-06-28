package Leetcode;

import java.util.LinkedList;
import java.util.Queue;

public class lc64 {

    private static int extracted2(int[][] grid) {
        Queue<int[]> queue = new LinkedList<>();
        int rows = grid.length;
        int colums = grid[0].length;
        boolean[][] vis = new boolean[rows][colums];
        int[][] dp = new int[rows][colums];
        queue.offer(new int[]{0, 0, grid[0][0]});
        int cur = Integer.MAX_VALUE;
        while (!queue.isEmpty()) {
            int size = queue.size();
            System.out.println(size);
            for (int i = 0; i < size; i++) {
                int[] arr = queue.poll();
                int curi = arr[0];
                int curj = arr[1];
                int val = arr[2];

                if (curi == rows - 1 && curj == colums - 1) {
                    cur = Math.min(cur, val);
                }

                if (curi + 1 < rows && !vis[curi + 1][curj]) {
                    if (curi != rows - 1 && curj != colums - 1) {
                        vis[curi + 1][curj] = true;
                    }
                    queue.offer(new int[]{curi + 1, curj, val + grid[curi + 1][curj]});
                }
                if (curj + 1 < colums && !vis[curi][curj + 1]) {
                    if (curi != rows - 1 && curj != colums - 1) {
                        vis[curi][curj + 1] = true;
                    }
                    queue.offer(new int[]{curi, curj + 1, val + grid[curi][curj + 1]});
                }
            }
        }
        return cur;
    }

    public static void main(String[] args) {
        int[][] grid = {
                {7, 1, 3, 5, 8, 9, 9, 2, 1, 9, 0, 8, 3, 1, 6, 6, 9, 5},
                {9, 5, 9, 4, 0, 4, 8, 8, 9, 5, 7, 3, 6, 6, 6, 9, 1, 6},
                {8, 2, 9, 1, 3, 1, 9, 7, 2, 5, 3, 1, 2, 4, 8, 2, 8, 8},
                {6, 7, 9, 8, 4, 8, 3, 0, 4, 0, 9, 6, 6, 0, 0, 5, 1, 4},
                {7, 1, 3, 1, 8, 8, 3, 1, 2, 1, 5, 0, 2, 1, 9, 1, 1, 4},
                {9, 5, 4, 3, 5, 6, 1, 3, 6, 4, 9, 7, 0, 8, 0, 3, 9, 9},
                {1, 4, 2, 5, 8, 7, 7, 0, 0, 7, 1, 2, 1, 2, 7, 7, 7, 4},
                {3, 9, 7, 9, 5, 8, 9, 5, 6, 9, 8, 8, 0, 1, 4, 2, 8, 2},
                {1, 5, 2, 2, 2, 5, 6, 3, 9, 3, 1, 7, 9, 6, 8, 6, 8, 3},
                {5, 7, 8, 3, 8, 8, 3, 9, 9, 8, 1, 9, 2, 5, 4, 7, 7, 7},
                {2, 3, 2, 4, 8, 5, 1, 7, 2, 9, 5, 2, 4, 2, 9, 2, 8, 7},
                {0, 1, 6, 1, 1, 0, 0, 6, 5, 4, 3, 4, 3, 7, 9, 6, 1, 9}
        };
        System.out.println(extracted2(grid));
    }

}
