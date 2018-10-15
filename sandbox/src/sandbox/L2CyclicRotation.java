package sandbox;

import java.util.Arrays;

/**
 * Rotate an array to the right by a given number of steps.
 * 
 * Got 100% score in codility:
 * https://app.codility.com/demo/results/trainingYEUD4M-5K8/
 * 
 * @author bash
 *
 */
public class L2CyclicRotation {

	public static void main(String[] args) {
		int[] arrayN = { 1, 2, 3, 4, 5 };

		System.out.println("Input Array: " + Arrays.toString(arrayN));
		System.out.println("Rotated 1 time: " + Arrays.toString(solution(arrayN, 1)));
		System.out.println("Rotated 2 times: " + Arrays.toString(solution(arrayN, 2)));
		System.out.println("Rotated 3 times: " + Arrays.toString(solution(arrayN, 3)));
		System.out.println("Rotated 4 times: " + Arrays.toString(solution(arrayN, 4)));
		System.out.println("Rotated 5 times: " + Arrays.toString(solution(arrayN, 5)));
		System.out.println("Rotated 6 times: " + Arrays.toString(solution(arrayN, 6)));
		System.out.println("Rotated 7 times: " + Arrays.toString(solution(arrayN, 7)));
		System.out.println("Rotated 8 times: " + Arrays.toString(solution(arrayN, 8)));
		System.out.println("Rotated 9 times: " + Arrays.toString(solution(arrayN, 9)));
	}

	public static int[] solution(int[] A, int K) {
		if (K == A.length || K == 0 || A.length == 0) {
			return A;
		}
		K = K % A.length;
		int[] results = new int[A.length];
		for (int i = 0; i < A.length; i++) {
			int newIndex = i + K;
			if (newIndex >= A.length) {
				newIndex -= A.length;
			}
			results[newIndex] = A[i];
		}
		return results;
	}
}
