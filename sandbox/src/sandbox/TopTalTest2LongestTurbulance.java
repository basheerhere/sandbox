package sandbox;

import java.util.Arrays;

public class TopTalTest2LongestTurbulance {
	public static void main(String[] args) {
		caller(new int[] { 9, 9, 9, 4, 2, 10, 7, 8, 8, 1, 9 });
		caller(new int[] { 9, 4, 2, 10, 7, 8, 8, 1, 9 });
		caller(new int[] { 4, 8, 12, 16 });
		caller(new int[] { 100 });
		caller(new int[] { 50, 150, 50, 150, 50, 150, 50, 150, 50, 150, 50, 150, 50, 150, 50, 150, 50, 150, 50, 150, 50,
				150, 50, 150, 50, 150, 50, 150, 50, 150, 50, 150, 50, 150, 50, 150, 50, 150, 50, 150, 50, 150, 50, 150,
				50, 150, 50, 150, 50, 150, 50, 150 });

		caller(new int[] { 0, 2, 0 });
		caller(new int[] { 1, 1 });
		caller(new int[] { 1, 2 });
	}

	public static void caller(int[] A) {
		System.out.println("Input Array: " + Arrays.toString(A));
		System.out.println("Array Length: " + A.length);
		System.out.println("Longest Turbulant Period: " + solution(A));
		System.out.println();
	}

	public static int solution1(int[] A) {
		int turbulancePeriod = 0, longestTurbulancePeriod = 0;
		int prevHeight = A[0];
		boolean inTurbulance = false;
		for (int i = 1; i < A.length; i++) {
			int currentHeight = A[i];
			if (currentHeight < prevHeight) {
				turbulancePeriod++;
			} else if (currentHeight > prevHeight) {
				turbulancePeriod++;
			} else {
				if (turbulancePeriod > longestTurbulancePeriod) {
					longestTurbulancePeriod = turbulancePeriod;
				}
				turbulancePeriod = 1;
			}
		}
		return longestTurbulancePeriod;
	}

	public static int solution2(int[] A) {
		int startingPosition = 0;
		int longestTurbulancestartingPosition = 0;
		int turbulantPeriod = 1; // to include the starting position
		int longestTurbulantPeriod = 0;

		if (A.length == 1)
			return 1;

		for (int i = 1; i < A.length - 1; i++) {
			if (A[i - 1] > A[i] && A[i] < A[i + 1]) {
				turbulantPeriod++;
			} else if (A[i - 1] < A[i] && A[i] > A[i + 1]) {
				turbulantPeriod++;
			} else {
				if (turbulantPeriod > longestTurbulantPeriod) {
					longestTurbulantPeriod = ++turbulantPeriod;
					longestTurbulancestartingPosition = startingPosition;
				}
				turbulantPeriod = 1;
				startingPosition = i;
			}
		}
		System.out.printf("Longest turbulant period is %s and it started at: %s seconds.\n", longestTurbulantPeriod,
				longestTurbulancestartingPosition);
		return longestTurbulantPeriod;
	}

	public static int solution3(int[] A) {
		int currentLength = 1, bestLength = 0, lastDirection = 0, lastValue = 0;
		for (int currentVal : A) {
			int newDirection = currentVal - lastValue;
			if (newDirection * lastDirection >= 0 || newDirection == 0) {
				currentLength = 1;
			} else {
				currentLength++;
				bestLength = Math.max(currentLength, bestLength);
			}
			lastValue = currentVal;
			lastDirection = newDirection;
		}
		return bestLength;
	}

	public static int solution(int[] A) {
		// Handle edge cases first.
		if (A.length == 2 && A[0] == A[1]) {
			return 0;
		} else if (A.length == 1) {
			return 1;
		}

		int turbulentPeriod = 1, longestTurbulencePeriod = 0, lastDelta = 0;
		for (int i = 1; i < A.length; i++) {
			int currentHeight = A[i];
			int lastHeight = A[i - 1];
			turbulentPeriod++;
			int newDelta = currentHeight - lastHeight;
			if (newDelta < 0 && lastDelta < 0 || newDelta > 0 && lastDelta > 0) {
				turbulentPeriod--;
			} else if (newDelta == 0) {
				turbulentPeriod = 1;
			}
			longestTurbulencePeriod = Math.max(turbulentPeriod, longestTurbulencePeriod);
			lastHeight = currentHeight;
			lastDelta = newDelta;
		}
		return longestTurbulencePeriod;
	}
}
