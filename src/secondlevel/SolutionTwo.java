package secondlevel;

public class SolutionTwo {

    public static String solution(long x, long y) {
        long increment = 0;
        long start = 1;
        for (long i = 1; i < y; start += increment, i++) {
            increment++;
        }
        increment += 2;
        long count = start;
        for (long i = 1; i < x; i++) {
            count += increment;
            increment++;
        }
        return String.valueOf(count);
    }

}