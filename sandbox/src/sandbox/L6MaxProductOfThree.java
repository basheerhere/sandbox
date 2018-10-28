package sandbox;

import java.util.Arrays;

/**
 * Maximize A[P] * A[Q] * A[R] for any triplet (P, Q, R)
 * 
 * Scored 100%: https://app.codility.com/demo/results/trainingRTBSAR-4YP/
 * 
 * @author bash
 *
 */
public class L6MaxProductOfThree {
	public static void main(String[] args) {
		caller(new int[] { 1, 2, 3 });
		caller(new int[] { -1, -1, 0 });
		caller(new int[] { -5, 5, -5, 4 });
		caller(new int[] { 1, 5, 2, 1, 4, 0 });
		caller(new int[] { 2, 1, 1, 2, 3, 1 });
		caller(new int[] { 4, 2, 2, 5, 1, 5, 8 });
		caller(new int[] { 1000, 999, 888 });
	}

	public static void caller(int[] A) {
		System.out.println("Input Array = " + Arrays.toString(A));
		System.out.println("Maximum product of a triplet = " + solution(A));
		System.out.println();
	}

	/**
	 * After sorting, largest product can be found by multiplying the last three
	 * elements. This is result candidate 1.
	 * 
	 * However, if there are 2 negative numbers, they will become a positive after
	 * multiplying, so by multiplying the two largest negatives with the largest
	 * positive, we get another candidate. This is result candidate 2.
	 * 
	 * Result is the bigger of those 2 candidates.
	 * 
	 * As a side note, if all numbers are negative, the first result candidate [the
	 * three largest (closest to 0)] still get the largest element!
	 * 
	 * @param A
	 * @return
	 */
	public static int solution(int[] A) {
		Arrays.sort(A);
		int length = A.length;
		int maxProduct1 = A[length - 3] * A[length - 2] * A[length - 1];
		int maxProduct2 = A[0] * A[1] * A[length - 1];
		return maxProduct1 > maxProduct2 ? maxProduct1 : maxProduct2;
	}
}
