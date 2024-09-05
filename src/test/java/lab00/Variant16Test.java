package lab00;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

//@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class Variant16Test {
    Variant16 variant16 = new Variant16();

    // region integerNumberTask
    @ParameterizedTest
    @DisplayName("Integer number")
    @CsvSource({
            "100, 100",
            "101, 110",
            "110, 101",
            "205, 250",
            "300, 300",
    })
    void integerNumbersTest(int expected, int number) {
        assertEquals(expected, variant16.integerNumbersTask(number));
    }

    @ParameterizedTest
    @DisplayName("Integer number invalid args")
    @ValueSource(ints = {99, 1000, -123, 10})
    void integerNumberTest_InvalidArguments(int number) {
        assertThrows(IllegalArgumentException.class, () -> variant16.integerNumbersTask(number));
    }
    // endregion

    // region booleanTask
    @ParameterizedTest
    @DisplayName("Boolean should return true")
    @ValueSource(ints = {10, 24, 98,})
    void booleanTest_ShouldReturnTrue(int number) {
        assertTrue(variant16.booleanTask(number));
    }

    @ParameterizedTest
    @DisplayName("Boolean should return false")
    @ValueSource(ints = {-11, -10, -5, 0, 2, 7, 100, 111, 132})
    void booleanTest_ShouldReturnFalse(int number) {
        assertFalse(variant16.booleanTask(number), "Error with number" + number);
    }
    // endregion

    //region ifTask
    @ParameterizedTest
    @DisplayName("If")
    @CsvSource({
            "1, 2, 3, 3, 6, 9",
            "0, 1, 2, 0, 3, 6",
            "-3, 0, 3, -9, 0, 9",
            "1, 3, 2, -1, -3, -2",
            "0, 1, -2, 0, -1, 2"
    })
    void ifTest(double a, double b, double c, double expectedA, double expectedB, double expectedC) {
        assertArrayEquals(new double[]{expectedA, expectedB, expectedC}, variant16.ifTask(a, b, c), 1e-10);
    }
    // endregion

    // region switchTask
    @ParameterizedTest
    @DisplayName("Switch")
    @CsvSource({
            "21, Двадцять один рік",
            "30, Тридцять років",
            "44, Сорок чотири роки",
            "50, П'ятдесят років",
    })
    void switchTest(int number, String expectedString) {

        assertEquals(expectedString, variant16.switchTask(number));
    }

    @ParameterizedTest
    @DisplayName("Switch invalid args")
    @ValueSource(ints = {19, 70})
    void switchTest_invalidArguments(int number) {
        assertThrows(IllegalArgumentException.class, () -> variant16.switchTask(number));
    }
    // endregion

    // region forTask
    @ParameterizedTest
    @DisplayName("For")
    @MethodSource("forTaskTestData")
    void forTest(double[] expected, double a, int n) {
        assertArrayEquals(expected, variant16.forTask(a, n));
    }

    static Stream<Arguments> forTaskTestData() {
        return Stream.of(
                Arguments.arguments(new double[]{1, 1, 1}, 1.0, 3),
                Arguments.arguments(new double[]{2, 4, 8, 16}, 2.0, 4)
        );
    }

    @ParameterizedTest
    @DisplayName("For invalid args")
    @CsvSource({"1.0, -1", "1.0, 0"})
    void forTest_InvalidArguments(double a, int n) {
        assertThrows(IllegalArgumentException.class, () -> variant16.forTask(a, n));
    }
    // endregion

    // region whileTask
    @Test
    void whileTest() {
        assertArrayEquals(new double[]{5, 320}, variant16.whileTask(100));
    }

    @ParameterizedTest
    @DisplayName("While invalid args")
    @ValueSource(ints = {-1, 101})
    void whileTest_InvalidArguments(int percent) {
        assertThrows(IllegalArgumentException.class, () -> variant16.whileTask(percent));
    }
    // endregion

    // region arrayTask
    @ParameterizedTest
    @DisplayName("Array")
    @MethodSource("arrayTaskTestData")
    void arrayTest(double[] expectedArr, double[] argArray, String message) {
        assertArrayEquals(expectedArr, variant16.arrayTask(argArray), message);
    }

    static Stream<Arguments> arrayTaskTestData() {
        return Stream.of(
                Arguments.arguments(new double[]{1, 5, 2, 4, 3}, new double[]{1, 2, 3, 4, 5}, "Error with odd numbers of elements"),
                Arguments.arguments(new double[]{1, 4, 2, 3}, new double[]{1, 2, 3, 4}, "Error with even numbers of elements"),
                Arguments.arguments(new double[]{1}, new double[]{1}, "Error with alone element in array"),
                Arguments.arguments(new double[]{}, new double[]{}, "Error with an empty argument array")
        );
    }
    // endregion

    // region twoDimensionArrayTask
    @ParameterizedTest
    @DisplayName("Two dimensional array")
    @MethodSource("twoDimensionArrayTestData")
    void twoDimensionArrayTest(int[] expected, int[][] argArray, String message) {
        assertArrayEquals(
                expected,
                variant16.twoDimensionArrayTask(argArray),
                message
        );
    }

    static Stream<Arguments> twoDimensionArrayTestData() {
        return Stream.of(
                Arguments.arguments(
                        new int[]{1},
                        new int[][]{
                                {1}
                        },
                        "Test failed for the matrix with order 1"
                ),
                Arguments.arguments(
                        new int[]{1, 4, 7, 8, 9, 6, 3, 2, 5},
                        new int[][]{
                                {1, 2, 3},
                                {4, 5, 6},
                                {7, 8, 9}
                        }, "Test failed for the matrix with order 3"),
                Arguments.arguments(
                        new int[]{1, 6, 11, 16, 21, 22, 23, 24, 25, 20, 15, 10, 5, 4, 3, 2, 7, 12, 17, 18, 19, 14, 9, 8, 13},
                        new int[][]{
                                {1, 2, 3, 4, 5},
                                {6, 7, 8, 9, 10},
                                {11, 12, 13, 14, 15},
                                {16, 17, 18, 19, 20},
                                {21, 22, 23, 24, 25}
                        }, "Test failed for the matrix with order 5")
        );
    }

    @ParameterizedTest
    @DisplayName("Two dimension array invalid args")
    @MethodSource("twoDimensionArrayInvalidTestData")
    void twoDimensionArrayTest_invalidArguments(int[][] array, String message) {
        assertThrows(IllegalArgumentException.class, () -> variant16.twoDimensionArrayTask(
                array
        ), message);
    }

    static Stream<Arguments> twoDimensionArrayInvalidTestData() {
        return Stream.of(
                Arguments.arguments(
                        new int[][]{{}},
                        "Empty matrix should throw exception"),
                Arguments.arguments(new int[][]{
                        {1, 2, 3, 4},
                        {5, 6, 7, 8},
                        {9, 10, 11, 12},
                        {13, 14, 15, 16}
                }, "Even order matrix should throw exception"),
                Arguments.arguments(new int[][]{
                        {1, 2, 3, 4, 5},
                        {6, 7, 8, 9},
                        {11, 12, 13, 14, 15},
                        {16, 17, 18, 19, 20},
                        {21, 22, 23, 24, 25}
                }, "Non-square matrix should throw exception")
        );
    }

    // endregion
}