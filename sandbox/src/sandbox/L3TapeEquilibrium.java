package sandbox;

import java.util.Arrays;

/**
 * Minimize the value |(A[0] + ... + A[P-1]) - (A[P] + ... + A[N-1])|.
 * 
 * Scored 100%: https://app.codility.com/demo/results/trainingR7AJSJ-YAA/
 * 
 * @author bash
 *
 */
public class L3TapeEquilibrium {

	public static void main(String[] args) {
		int[] A = { 3, 1, 2, 4, 3 };
		System.out.println("Input Array: " + Arrays.toString(A));
		System.out.println("Minimum Difference = " + solution(A));
	}

	public static int solution(int[] A) {
		int leftSum = A[0];
		int rightSum = 0;

		// Save the first element for left sum.
		for (int i = 1; i < A.length; i++) {
			rightSum += A[i];
		}

		int minimumDifference = Math.abs(leftSum - rightSum);

		// Skip the last element since its already added to right sum
		for (int i = 1; i < A.length - 1; i++) {
			leftSum += A[i];
			rightSum -= A[i];
			if (Math.abs(leftSum - rightSum) < minimumDifference) {
				minimumDifference = Math.abs(leftSum - rightSum);
			}
		}
		return minimumDifference;
	}
}
