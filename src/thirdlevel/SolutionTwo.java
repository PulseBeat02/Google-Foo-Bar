package thirdlevel;

import java.util.LinkedList;
import java.util.Queue;

public class SolutionTwo {

    public static int width;
    public static int height;

    public static int[][] directionals = {
            {-1, 0},
            {0, -1},
            {1, 0},
            {0, 1}
    };

    public static int solution(int[][] map) {
        width = map[0].length;
        height = map.length;
        return bfs(map);
    }

    public static int bfs(int[][] map) {

        boolean[][][] visited = new boolean[height][width][2];

        int destX = height - 1;
        int destY = width - 1;

        Queue<Box> queue = new LinkedList<>();
        queue.add(new Box(0, 0, 1, false));

        int min = Integer.MAX_VALUE;

        while (queue.size() > 0) {

            Box slot = queue.remove();
            if (slot.x == destX && slot.y == destY) {
                min = Math.min(min, slot.count);
                continue;
            }

            int indx = slot.wall ? 0 : 1;

            if (visited[slot.x][slot.y][indx]) {
                continue;
            }

            visited[slot.x][slot.y][indx] = true;

            for (int[] dir : directionals) {
                int newX = dir[0] + slot.x;
                int newY = dir[1] + slot.y;
                if (inBounds(newX, newY)) {
                    if (!(map[newX][newY] == 1 && slot.wall)) {
                        if (map[newX][newY] == 1) {
                            queue.add(new Box(newX, newY, slot.count + 1, true));
                        } else {
                            queue.add(new Box(newX, newY, slot.count + 1, slot.wall));
                        }
                    }
                }
            }
        }

        return min;

    }

    public static boolean inBounds(int x, int y) {
        return x < height && x >= 0 && y < width && y >= 0;
    }

    public static class Box {

        public final int x;
        public final int y;
        public final int count;
        public final boolean wall;

        public Box(final int x, final int y, final int count, final boolean wall) {
            this.x = x;
            this.y = y;
            this.count = count;
            this.wall = wall;
        }

    }

}