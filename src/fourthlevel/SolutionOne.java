package fourthlevel;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SolutionOne {

    public static int size;
    public static List<Integer> ans;

    public static int[] solution(int[][] times, int times_limit) {
        int length = times.length;
        if (length <= 2 || (length != times[0].length)) {
            return new int[]{};
        }
        if (validate(times, length)) {
            int[] ans = new int[length - 2];
            for (int i = 0; i < length - 2; i++) {
                ans[i] = i;
            }
            return ans;
        } else {
            size = 0;
            ans = new ArrayList<>();
            boolean[] visited = new boolean[length];
            visited[0] = true;
            for (int i = 1; i < length - 1; i++) {
                dfs(i, times_limit - times[0][i], times, new ArrayList<>(), visited);
            }
            if (ans.isEmpty()) {
                return new int[]{};
            }
            int[] ret = new int[ans.size()];
            for (int i = 0; i < ret.length; i++) {
                ret[i] = ans.get(i);
            }
            Arrays.parallelSort(ret);
            return ret;
        }
    }

    public static void dfs(int u, int time, int[][] times, List<Integer> list, boolean[] visited) {
        int n = times.length;
        if (time <= -999 || (u == n - 1 && time < 0) || (size == n - 2)) {
            return;
        }
        if (time >= 0 && u == n - 1) {
            if (list.size() > size) {
                ans = new ArrayList<>(list);
                size = list.size();
            }
            return;
        }
        if (visited[u]) {
            return;
        }
        visited[u] = true;
        list.add(u - 1);
        for (int v = 1; v < n; ++v) {
            if (v == u) {
                continue;
            }
            dfs(v, time - times[u][v], times, list, visited);
        }
        list.remove(list.size() - 1);
        visited[u] = false;
    }

    public static boolean validate(int[][] times, int n) {
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                for (int k = 0; k < n; k++) {
                    if (times[i][k] + times[k][j] < times[i][j]) {
                        times[i][j] = times[i][k] + times[k][j];
                    }
                }
            }
        }
        for (int i = 0; i < n; i++) {
            if (times[i][i] < 0) {
                return true;
            }
        }
        return false;
    }

}