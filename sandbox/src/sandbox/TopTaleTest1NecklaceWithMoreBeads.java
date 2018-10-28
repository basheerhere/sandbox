package sandbox;

import java.util.Arrays;

public class TopTaleTest1NecklaceWithMoreBeads {

	public static void main(String[] args) {
		caller(new int[] { 5, 4, 0, 3, 1, 6, 2 });
		caller(new int[] { 0, 2, 0 });
		caller(new int[] { 1, 1 });
	}

	public static void caller(int[] A) {
		long start = System.currentTimeMillis();
		System.out.println("Input Array: " + Arrays.toString(A));
		System.out.println("Max Beads: " + solution(A));
		long end = System.currentTimeMillis();
		System.out.println("Execution Time: " + (end - start) + " ms.");
		System.out.println();
	}

	public static int incorrectSolution(int[] A) {
		boolean[] seenBeads = new boolean[A.length];
		int maxBeads = 0;

		for (int i = 0; i < A.length; i++) {
			int beadsCount = 1;
			int nextBeadNumber = i;

			// loop until starting bead is found
			while (i != A[nextBeadNumber]) {
				if (seenBeads[nextBeadNumber] == true) {
					continue;
				}
				seenBeads[nextBeadNumber] = true;
				beadsCount++;
				nextBeadNumber = A[nextBeadNumber];
			}

			if (beadsCount > maxBeads) {
				maxBeads = beadsCount;
			}
		}

//		for (int i = 0; i < A.length; i++) {
//			int nextBeadNumber = A[i];
//			if (seenBeads[i] = true) {
//				continue;
//			}
//			seenBeads[i] = true;
//			beadsCount++;
//			while (A[i] != A[nextBeadNumber]) {
//				beadsCount++;
//				seenBeads[A[nextBeadNumber]] = true;
//				nextBeadNumber = A[nextBeadNumber];
//			}
//
//			if (beadsCount > maxBeads) {
//				maxBeads = beadsCount;
//			}
//		}

		return maxBeads;
	}

	public static int solution(int[] A) {
		int maxBeads = 0;
		boolean[] seen = new boolean[A.length];

		for (int i = 0; i < A.length; i++) {
			if (seen[i]) {
				// skip previously seen beads to save valuable resources.
				continue;
			}
			seen[i] = true;
			int beadsCount = 1;
			int currentNecklaceStartingBead = i;
			int nextBead = A[currentNecklaceStartingBead];

			// loop until starting bead is found again
			while (currentNecklaceStartingBead != nextBead && !seen[nextBead]) {
				seen[nextBead] = true;
				beadsCount++;
				nextBead = A[nextBead];
			}

			if (beadsCount > maxBeads) {
				maxBeads = beadsCount;
			}
		}

		return maxBeads;
	}

}
