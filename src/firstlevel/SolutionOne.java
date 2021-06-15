package firstlevel;

public class SolutionOne {

    public static void main(String[] args) {
        System.out.println(solution(5));
    }

    public static String solution(int i) {
        final StringBuilder concat = new StringBuilder();
        for (int n = 2; n < 50000; n++) {
            if (checkPrimality(n)) {
                concat.append(n);
            }
        }
        return concat.substring(i, i + 5);
    }

    // Sieve of Eratosthenes
    private static boolean checkPrimality(int n) {
        if (n == 2 || n == 3) {
            return true;
        }
        if (n % 2 == 0 || n % 3 == 0) {
            return false;
        }
        int root = (int) Math.sqrt(n) + 1;
        for (int i = 6; i < root + 1; i += 6) {
            if (n % (i - 1) == 0 || n % (i + 1) == 0) {
                return false;
            }
        }
        return true;
    }

}
