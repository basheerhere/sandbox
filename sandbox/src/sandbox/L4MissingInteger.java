package sandbox;

import java.util.Arrays;

/**
 * Find the smallest positive integer that does not occur in a given sequence.
 * 
 * Scored 100%: https://app.codility.com/demo/results/trainingMZKS3F-PKD/
 * 
 * @author bash
 *
 */
public class L4MissingInteger {

	public static void main(String[] args) {
		caller(new int[] { 1, 3, 6, 4, 1, 2 });
		caller(new int[] { 1, 2, 3 });
		caller(new int[] { -1, -3 });
		caller(new int[] { 1, 1 });
		caller(new int[] { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11 });
		caller(new int[] { 1, 2, 3, 4, 5, 1, 2, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 6, 7, 8, 9, 10, 11 });
	}

	public static void caller(int[] A) {
		System.out.println("Input Array = " + Arrays.toString(A));
		System.out.println("Missing integer: " + solution(A));
	}

	public static int solution(int[] A) {
		boolean[] numberSeen = new boolean[A.length];

		for (int value : A) {
			if (value > 0 && value <= A.length) {
				numberSeen[value - 1] = true;
			}
		}

		for (int i = 0; i < numberSeen.length; i++) {
			if (numberSeen[i] == false) {
				return i + 1;
			}
		}

		return A.length + 1;

	}

}
