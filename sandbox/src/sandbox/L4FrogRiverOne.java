package sandbox;

import java.util.Arrays;

/**
 * Find the earliest time when a frog can jump to the other side of a river.
 * 
 * Scored 100%: https://app.codility.com/demo/results/trainingFXUY36-EQS/
 * 
 * @author bash
 *
 */
public class L4FrogRiverOne {

	public static void main(String[] args) {
		caller(3, new int[] { 4, 1, 3, 2 });
		caller(4, new int[] { 1, 4, 1 });
		caller(1, new int[] { 1, 1 });
	}

	public static void caller(int X, int[] A) {
		System.out.println("Positions required to jump: " + X);
		System.out.println("Input Array: " + Arrays.toString(A));
		System.out.println("Earliest time the frog can jump is after " + solution(X, A) + " seconds.");
		System.out.println();
	}

	public static int solution(int X, int[] A) {
		boolean[] leafAvailableToJump = new boolean[X];
		int leavesYetToFall = X;
		for (int time = 0; time < A.length; time++) {
			int position = A[time];
			if (position < 0 || position > X) {
				return -1;
			}

			if (leafAvailableToJump[position - 1] == false) {
				leafAvailableToJump[position - 1] = true;
				leavesYetToFall--;
				if (leavesYetToFall == 0) {
					return time;
				}
			}
		}
		return -1;
	}
}
