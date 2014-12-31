package ch06.ex09;

import static org.junit.Assert.assertArrayEquals;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.Arrays;

import org.junit.Test;

class Matrix {

    public int e11, e12, e21, e22;

    public Matrix(int e11, int e12, int e21, int e22) {
        this.e11 = e11;
        this.e12 = e12;
        this.e21 = e21;
        this.e22 = e22;
    }

    public Matrix multiply(Matrix other) {
        return new Matrix(
                this.e11 * other.e11 + this.e12 * other.e21,
                this.e11 * other.e12 + this.e12 * other.e22,
                this.e21 * other.e11 + this.e22 * other.e21,
                this.e21 * other.e12 + this.e22 * other.e22);
    }

    public String toString() {
        StringWriter str = new StringWriter();
        PrintWriter out = new PrintWriter(str);
        out.printf("{{%02d, %02d}, {%02d, %02d}}", e11, e12, e21, e22);
        return str.toString();
    }
}

public class ParallelFibonacci {

    @Test
    public void computeF10() {
        int[] expect = { 1, 2, 3, 5, 8, 13, 21, 34, 55, 89 };

        Matrix[] f10 = new Matrix[10];
        Arrays.parallelSetAll(f10, i -> new Matrix(1, 1, 1, 0));
        Arrays.parallelPrefix(f10, (x, y) -> x.multiply(y));
        int[] actual = Arrays.stream(f10).mapToInt(m -> m.e11).toArray();

        assertArrayEquals(actual, expect);
    }
}
