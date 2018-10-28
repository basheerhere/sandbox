package sandbox;

import java.util.Arrays;

/**
 * Determine whether a triangle can be built from a given set of edges.
 * 
 * Scored 100%: https://app.codility.com/demo/results/training2RNETZ-4HA/
 * 
 * Hint: Watch for arithmetic overflow
 * 
 * @author bash
 *
 */

public class L6Triangle {

	public static void main(String[] args) {
		caller(new int[] {});
		caller(new int[] { 10000 });
		caller(new int[] { 2, 1, 1, 2, 3, 1 });
		caller(new int[] { 4, 2, 2, 5, 1, 5, 8 });
		caller(new int[] { -3, -5, -8, -4, -10 });
		caller(new int[] { 2147483645, 2147483646, 2147483647 });
		caller(new int[] { -2147483648, -2147483647, -2147483646 });
	}

	public static void caller(int[] A) {
		System.out.println("Input Array = " + Arrays.toString(A));
		System.out.println("Does a trianglular triplet exists for this array? " + solution(A));
		System.out.println();
	}

	public static int solution(int[] A) {
		if (A.length < 3) {
			return 0;
		}
		Arrays.sort(A);
		for (int i = 0; i < A.length - 2; i++) {
			if ((long) A[i] + A[i + 1] > A[i + 2]) {
				return 1;
			}
		}
		return 0;
	}
}
