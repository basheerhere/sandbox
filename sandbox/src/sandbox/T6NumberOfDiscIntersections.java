package sandbox;

import java.util.Arrays;
import java.util.Comparator;

/**
 * Compute the number of intersections in a sequence of discs.
 * 
 * Scored 100%: https://app.codility.com/demo/results/trainingUNHPRR-TXX/
 * 
 * @author bash
 *
 */
public class T6NumberOfDiscIntersections {
	public static void main(String[] args) {
		caller(new int[] {});
		caller(new int[] { 10000 });
		caller(new int[] { 1, 5, 2, 1, 4, 0 });
		caller(new int[] { 2, 1, 1, 2, 3, 1 });
		caller(new int[] { 4, 2, 2, 5, 1, 5, 8 });
//		caller(new int[] { 2147483645, 2147483646, 2147483647 });
	}

	public static void caller(int[] A) {
		System.out.println("Input Array = " + Arrays.toString(A));
		System.out.println("Number of disc intesects = " + solution(A));
		System.out.println();
	}

	public static int incorrectSolution(int[] A) {
		int[] startingPoints = new int[A.length];
		int[] endingPoints = new int[A.length];

		for (int i = 0; i < A.length; i++) {
			startingPoints[i] = i - A[i];
			endingPoints[i] = i + A[i];
		}

		Arrays.sort(startingPoints);
		Arrays.sort(endingPoints);
		int numberOfIntersects = 0;

		for (int startingPointsIndex = 0; startingPointsIndex < A.length; startingPointsIndex++) {
			for (int endingPointsIndex = startingPointsIndex + 1; endingPointsIndex < A.length; endingPointsIndex++) {
				if (endingPoints[endingPointsIndex] > startingPoints[startingPointsIndex]) {
					// they dont intesect.
					continue;
				} else {
					numberOfIntersects++;
				}
			}
		}
		return numberOfIntersects > 10E6 ? -1 : numberOfIntersects;
	}

	public static int solution(int[] A) {
		int j = 0;
		Pair[] arr = new Pair[A.length * 2];
		for (int i = 0; i < A.length; i++) {
			Pair s = new Pair((long) i - (long) A[i], true);
			arr[j] = s;
			j++;
			Pair e = new Pair((long) i + (long) A[i], false);
			arr[j] = e;
			j++;
		}
		Arrays.sort(arr, new Pair(0, true));

		long totalNumberOfIntersects = 0;
		long currentNumberOfDiscsCount = 0;
		for (Pair p : arr) {
			if (p.start) {
				// When a new disc starts, it will intersect with any earlier ones that has not
				// ended yet.
				totalNumberOfIntersects += currentNumberOfDiscsCount;
				if (totalNumberOfIntersects > 10000000) {
					return -1;
				}
				currentNumberOfDiscsCount++;
			} else {
				// When a disc ends, it will no longer intersect with upcoming new discs, so
				// there is no need to icrement the total number of intesects count
				currentNumberOfDiscsCount--;
			}
		}

		return (int) totalNumberOfIntersects;
	}

	static private class Pair implements Comparator<Pair> {
		private long x;
		private boolean start;

		public Pair(long x, boolean start) {
			this.x = x;
			this.start = start;
		}

		public int compare(Pair p1, Pair p2) {
			if (p1.x < p2.x) {
				return -1;
			} else if (p1.x > p2.x) {
				return 1;
			} else {
				if (p1.start && p2.start == false) {
					return -1;
				} else if (p1.start == false && p2.start) {
					return 1;
				} else {
					return 0;
				}
			}
		}
	}
}
