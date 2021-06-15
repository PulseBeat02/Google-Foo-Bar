package thirdlevel;

public class SolutionOne {

    public static void main(String[] args) {
        long start = System.currentTimeMillis();
        solution(200);
        long end = System.currentTimeMillis();
        System.out.println(end - start);
    }

    public static int[][] dict;

    public static int solution(int n) {
        dict = new int[n + 2][n + 2];
        for (int i = 0; i < dict.length; i++) {
            for (int j = 0; j < dict.length; j++) {
                dict[i][j] = -1;
            }
        }
        return recurse(1, n) - 1;
    }

    public static int recurse(int height, int remaining) {
        if (!(dict[height][remaining] == -1)) {
            return dict[height][remaining];
        }
        if (remaining == 0) {
            return 1;
        }
        if (remaining < height) {
            return 0;
        }
        int c = recurse(height + 1, remaining - height)
                + recurse(height + 1, remaining);
        dict[height][remaining] = c;
        return c;
    }

}
