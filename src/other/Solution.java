package other;

import java.util.Arrays;

public class Solution {

    public static void main(String[] args) {
        System.out.println(Arrays.toString(solution(new int[]{4, 30, 50})));
    }

    public static int[] solution(int[] pegs) {

        /*

        Suppose we have a sequence S_n such that
        S_0 equals 0. The (n + 1)th term would be defined
        as the nth term multiplied to a sign. This sign which
        would be defined as a power of -1 raised to the value of
        the nth term subtracted from the (n + 1)th term. This would
        give us the correct sign for such a sequence.

        Suppose we defined another sequence R_n such that R
        would be another sequence. The radii of the wheel where
        the nth term of R_n would be defined as -1 raised to the power
        of the (n - 1)th term multiplied to the nth term of S_n subtracting
        with r.

         */

        int first   =   0;
        int alt     = - 1;

        float max   = Float.MIN_VALUE;
        float min   = Float.MAX_VALUE;

        for (int i = 0; i < pegs.length - 1; i++) {

            first -= alt * (pegs[i + 1] - pegs[i]);
            alt   *= -1;

            if (i % 2 == 0) {
                min = Math.min(min, first);
            } else {
                max = Math.max(max, first);
            }

        }

        int num     = 2 * first;
        int den     = Math.abs(1 + 2 * alt);

        if (num < den * (max + 1) || num > den * (min - 1)) {
            return new int[]{-1, -1};
        }

        if (alt == 1 && num % 3 == 0) {
            num = Math.abs(Math.floorDiv(num, 3));
            den = 1;
        }

        return new int[]{num, den};

    }

//    public static int[] solution(int[] pegs) {
//
//        int[] invalid = {-1, -1};
//
//        if (pegs.length == 1) {
//            return invalid;
//        }
//
//        boolean parity     =  pegs.length % 2 == 0;
//
//        int first          =  pegs[0];
//        int last           =  pegs[pegs.length - 1];
//        int sum            =  parity ?
//                                -1 * first + last :
//                                -1 * first - last;
//
//        if (pegs.length > 2) {
//            for (int i = 1; i < pegs.length - 1; i++) {
//                sum += 2 * Math.pow((-1), (i + 1)) * pegs[i];
//            }
//        }
//
//        Fraction firstGear =  parity ?
//                                new Fraction(2 * ((float)sum/3)) :
//                                new Fraction(2 * (float)sum);
//        firstGear.simplify();
//
//        if (firstGear.getFloatValue() < 2) {
//            return invalid;
//        }
//
//        Fraction radius = firstGear;
//        for (int i = 0; i < pegs.length - 2; i++) {
//
//            int center        = pegs[i + 1] - pegs[i];
//            float radiusValue = radius.getFloatValue();
//            float next        = center - radiusValue;
//
//            if (next < 1 || radiusValue < 1) {
//                return invalid;
//            } else {
//                radius = new Fraction(next);
//                radius.simplify();
//            }
//
//        }
//
//        return new int[]{firstGear.numerator, firstGear.denominator};
//
//    }
//
//    private static class Fraction {
//
//        private int numerator;
//        private int denominator;
//
//        public Fraction(float d) {
//            String[] dec = String.valueOf(d).split("\\.");
//            int denominator = (int) Math.pow(10, dec[1].length());
//            this.numerator = (int) (d * denominator);
//            this.denominator = denominator;
//        }
//
//        private void simplify() {
//            int GCD = gcd(numerator, denominator);
//            numerator /= GCD;
//            denominator /= GCD;
//        }
//
//        private int gcd(int first, int second) {
//            return second == 0 ?
//                    first :
//                    gcd(second, first % second);
//        }
//
//        private float getFloatValue() {
//            return BigDecimal.valueOf(numerator)
//                    .divide(BigDecimal.valueOf(denominator), RoundingMode.HALF_UP)
//                    .floatValue();
//        }
//
//    }

//    Brute Force Solution (doesn't work for decimals due to precision)
//    public static int[] solution(int[] pegs) {
//        BigDecimal[] answers = new BigDecimal[pegs.length];
//        BigDecimal first;
//        outer:
//        for (first = BigDecimal.valueOf(2);
//             first.compareTo(BigDecimal.valueOf(10000)) < 0;
//             first = first.add(BigDecimal.valueOf(0.01d))) {
//            BigDecimal last = first.divide(BigDecimal.valueOf(2L));
//            BigDecimal subtract = first;
//            answers[0] = first;
//            for (int i = 1; i < pegs.length; i++) {
//                BigDecimal left = BigDecimal.valueOf(pegs[i] - pegs[i - 1] - subtract.doubleValue());
//                if (left.doubleValue() <= 0) {
//                    continue outer;
//                }
//                answers[i] = left;
//                subtract = left;
//            }
//            if (subtract.equals(last)) {
//                answers[answers.length - 1] = last;
//                break;
//            }
//        }
//        if (answers[0].doubleValue() <= 0 || first.doubleValue() >= 10000) {
//            return new int[]{-1, -1};
//        }
//        return getSimplestForm(answers[0].doubleValue());
//    }
//
//    public static int[] getSimplestForm(double x) {
//        String[] dec = String.valueOf(x).split("\\.");
//        int denominator = (int) Math.pow(10, dec[1].length());
//        int numerator = (int) (x * denominator);
//        int gcd = getGreatestCommonFactor(numerator, denominator);
//        return new int[]{numerator/gcd, denominator/gcd};
//    }
//
//    public static int getGreatestCommonFactor(int n1, int n2) {
//        if (n2 == 0) {
//            return n1;
//        }
//        return getGreatestCommonFactor(n2, n1 % n2);
//    }


}
