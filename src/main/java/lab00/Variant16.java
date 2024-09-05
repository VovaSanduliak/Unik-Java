package lab00;

public class Variant16 {

    /**
     * Takes a three-digit number and returns a new number with tens and units digits reserved.
     * <p>
     * For example, if the input is 123, the method will return 321
     *
     * @param number a three-digit natural number
     * @return a new number with tens and units digits reserved
     */
    public int integerNumbersTask(int number) {
        if (number < 100 || number > 999) {
            throw new IllegalArgumentException("The number must be a three-digit");
        }

        int hundreds = number / 100;
        int tens = (number / 10) % 10;
        int units = number % 10;

        return hundreds * 100 + units * 10 + tens;
    }

    /**
     * @param number number to check
     * @return true, if number is even and two-digits
     */
    public boolean booleanTask(int number) {
        return number > 9 && number < 100 && number % 2 == 0;
    }

    /**
     * Processes three double values and returns them in an array after applying a transformation based on their order.
     *
     * <p>If the values are in non-decreasing order (i.e., {@code a <= b <= c}), each value is multiplied by 3.
     * Otherwise, each value is multiplied by -1.</p>
     *
     * @param a is double number
     * @param b is double number
     * @param c is double number
     * @return new double[]
     */
    public double[] ifTask(double a, double b, double c) {

        if (a <= b && b <= c) {
            a *= 3;
            b *= 3;
            c *= 3;
        } else {
            a *= -1;
            b *= -1;
            c *= -1;
        }

        return new double[]{a, b, c};
    }

    /**
     * Converts a number in the range from 20 to 69 into its representation of years.
     *
     * @param number number from 20 to 69
     * @return string
     * @throws IllegalArgumentException if the number is not within the range from 20 to 69
     */
    public String switchTask(int number) {

        if (number < 20 || number > 69) {
            throw new IllegalArgumentException("The number must be from 20 to 69");
        }

        StringBuilder result = new StringBuilder();

        String[] tens = {"", "", "Двадцять", "Тридцять", "Сорок", "П'ятдесят", "Шістдесят"};
        String[] units = {"", "один", "два", "три", "чотири", "п'ять", "шість", "сім", "вісім", "дев'ять"};

        int tenPart = number / 10;
        int unitPart = number % 10;

        result.append(tens[tenPart]);
        result.append(" ");
        result.append(units[unitPart]);

        if (unitPart != 0)
            result.append(" ");

        return switch (unitPart) {
            case 0, 5, 6, 7, 8, 9 -> result.append("років").toString();
            case 2, 3, 4 -> result.append("роки").toString();
            case 1 -> result.append("рік").toString();
            default -> result.toString();
        };
    }

    /**
     * @param a double
     * @param n int
     * @return double[n];
     * @throws IllegalArgumentException when number is 0 or less
     */
    public double[] forTask(double a, int n) {
        if (n < 1) {
            throw new IllegalArgumentException("Number must be more than 0");
        }

        double[] results = new double[n];
        for (int i = 1; i <= n; i++) {
            results[i - 1] = Math.pow(a, i);
        }

        return results;
    }

    /**
     * @param percent percentage of distance increase from a previous day
     * @return days count and total distance
     */
    public double[] whileTask(int percent) {
//        assert (a >0 && b > 0): "Argument should be more than zero";

        if (percent < 0 || percent > 100) {
            throw new IllegalArgumentException("Percent must be from 0 to 100");
        }

        double distance = 10;
        int days = 0;

        while (distance <= 200) {
            distance += distance * percent / 100;
            days++;
        }

        return new double[]{days, distance};
    }

    /**
     * @param array double
     * @return double[]
     */

    public double[] arrayTask(double[] array) {
        int N = array.length;
        double[] result = new double[N];

        int start = 0;
        int end = N - 1;

        for (int i = 0; i < N; i++) {
            if (i % 2 == 0) {
                result[i] = array[start++];
            } else {
                result[i] = array[end--];
            }
        }

        return result;
    }

    /**
     * @param array square matrix with odd order
     * @return new array - all matrix elements in a counterclockwise spiral
     */
    public int[] twoDimensionArrayTask(int[][] array) {
        int m = array.length;

        if (m % 2 == 0) {
            throw new IllegalArgumentException("Matrix must have an odd order");
        }

        for (int[] row : array) {
            if (row.length != m) {
                throw new IllegalArgumentException("Matrix must be square");
            }
        }

        int[] result = new int[m * m];
        int left = 0;
        int right = m - 1;
        int top = 0;
        int bottom = m - 1;

        int index = 0;
        while (left <= right && top <= bottom) {
            for (int i = top; i <= bottom; i++) {
                result[index] = array[i][left];
                index++;
            }
            left++;

            for (int i = left; i <= right; i++) {
                result[index] = array[bottom][i];
                index++;
            }
            bottom--;

            if (left <= right) {
                for (int i = bottom; i >= top; i--) {
                    result[index] = array[i][right];
                    index++;
                }
                right--;
            }

            if (top <= bottom) {
                for (int i = right; i >= left; i--) {
                    result[index] = array[top][i];
                    index++;
                }
                top++;
            }
        }

        return result;
    }
//
//    public static void main(String... strings) {
//        System.out.println("Start of zero lab");
//        System.out.println("Done!!!");
//    }

}