package sandbox;

import java.util.Arrays;

/**
 * Find the minimal average of any slice containing at least two elements.
 * 
 * scored 100%: https://app.codility.com/demo/results/training8QWXRQ-RP8/
 * 
 * @author bash
 *
 */

public class L5MinAvgTwoSlice {
	public static void main(String[] args) {
		caller(new int[] { 4, 2, 2, 5, 1, 5, 8 });
		caller(new int[] { -3, -5, -8, -4, -10 });
	}

	public static void caller(int[] A) {
		System.out.println("Input Array = " + Arrays.toString(A));
		System.out.println("Minimum average index = " + solution(A));
		System.out.println();
	}

	/**
	 * Incorrect due to the issue below:
	 * 
	 * For example, for the input [-3, -5, -8, -4, -10] the solution returned a
	 * wrong answer (got 3 expected 2).
	 * 
	 * @param A
	 * @return
	 */
	public static int incorrectSolution(int[] A) {
//		int[] prefixSum = new int[A.length + 1];

//		for (int i = 0; i < A.length; i++) {
//			prefixSum[i + 1] = prefixSum[i] + A[i];
//		}
//
		double[] averagesOfTwoSlices = new double[A.length - 1];
		double currentAverage = 0, minimumAverage = Double.MAX_VALUE;
		int minimumAverageIndex = -1;
		for (int i = 0; i < A.length - 1; i++) {
			currentAverage = (A[i] + A[i + 1]) / (double) 2;
			if (currentAverage < minimumAverage) {
				minimumAverage = currentAverage;
				minimumAverageIndex = i;
			}
			averagesOfTwoSlices[i] = currentAverage;
		}
		System.out.println("Averages of consecutive 2 slices = " + Arrays.toString(averagesOfTwoSlices));
		return minimumAverageIndex;
	}

	public static int solution(int[] A) {
		int minimumAverageIndex = 0;
		double minimumAverage = (A[0] + A[1]) / 2; // At least two elements in A.
		double currentAverage;
		for (int i = 0; i < A.length - 2; i++) {
			/**
			 * We check first the two-element slice
			 */
			currentAverage = ((double) (A[i] + A[i + 1])) / 2;
			if (currentAverage < minimumAverage) {
				minimumAverage = currentAverage;
				minimumAverageIndex = i;
			}
			/**
			 * We check the three-element slice
			 */
			currentAverage = ((double) (A[i] + A[i + 1] + A[i + 2])) / 3;
			if (currentAverage < minimumAverage) {
				minimumAverage = currentAverage;
				minimumAverageIndex = i;
			}
		}
		/**
		 * Now we have to check the remaining two elements of the array Inside the for
		 * we checked ALL the three-element slices (the last one began at A.length-3)
		 * and all but one two-element slice (the missing one begins at A.length-2).
		 */
		currentAverage = ((double) (A[A.length - 2] + A[A.length - 1])) / 2;
		if (currentAverage < minimumAverage) {
			minimumAverage = currentAverage;
			minimumAverageIndex = A.length - 2;
		}
		return minimumAverageIndex;
	}
}
