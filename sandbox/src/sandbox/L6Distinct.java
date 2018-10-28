package sandbox;

import java.util.Arrays;

/**
 * Compute number of distinct values in an array.
 * 
 * Scored 100%: https://app.codility.com/demo/results/trainingRGWHAY-KQP/
 * 
 * @author bash
 *
 */
public class L6Distinct {

	public static void main(String[] args) {
		caller(new int[] {});
		caller(new int[] { 10000 });
		caller(new int[] { 2, 1, 1, 2, 3, 1 });
		caller(new int[] { 4, 2, 2, 5, 1, 5, 8 });
		caller(new int[] { -3, -5, -8, -4, -10 });
	}

	public static void caller(int[] A) {
		System.out.println("Input Array = " + Arrays.toString(A));
		System.out.println("Distinct numbers in array are: " + solution(A));
		System.out.println();
	}

	public static int solution(int[] A) {
		if (A.length == 0) {
			return 0;
		}
		Arrays.sort(A);
		int result = 1;
		for (int i = 1; i < A.length; i++) {
			if (A[i - 1] != A[i]) {
				result++;
			}
		}
		return result;
	}
}
