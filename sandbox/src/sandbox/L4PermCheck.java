package sandbox;

import java.util.Arrays;

/**
 * Check whether array A is a permutation.
 * 
 * Scored 100%: https://app.codility.com/demo/results/trainingVNRPB3-P5Y/
 * 
 * @author bash
 *
 */
public class L4PermCheck {

	public static void main(String[] args) {
		caller(new int[] { 4, 1, 3, 2 });
		caller(new int[] { 1, 4, 1 });
		caller(new int[] { 1, 1 });
	}

	public static void caller(int[] A) {
		System.out.println("Input Array: " + Arrays.toString(A));
		System.out.println("Is this a permutation array? " + solution(A));
		System.out.println();
	}

	/**
	 * Scored 58% due to incorrect results: For example, for the input [1, 4, 1] the
	 * solution returned a wrong answer (got 1 expected 0).
	 * 
	 * https://app.codility.com/demo/results/trainingZDQHUR-QB9/
	 * 
	 * @param A
	 * @return
	 */
	public static int solution1(int[] A) {
		long length = A.length;
		long expectedSum = length * (length + 1) / 2;
		long actualSum = Arrays.stream(A).sum();
		return expectedSum == actualSum ? 1 : 0;
	}

	/**
	 * Scored 100%: https://app.codility.com/demo/results/trainingVNRPB3-P5Y/
	 * 
	 * @param A
	 * @return
	 */
	public static int solution(int[] A) {
		boolean[] alreadyEncounteredThisNumber = new boolean[A.length];

		for (int i : A) {
			if (i < 0 || i > A.length) {
				return 0;
			}

			// Carefully avoid ArrayIndexOutOfBounds here by assigning to the index - 1th
			// element
			if (alreadyEncounteredThisNumber[i - 1] == true) {
				return 0;
			}
			alreadyEncounteredThisNumber[i - 1] = true;
		}

		return 1;
	}

}
