package sandbox;

import java.util.Arrays;

/**
 * Calculate the values of counters after applying all alternating operations:
 * increase counter by 1; set value of all counters to current maximum.
 * 
 * Scored 100%: https://app.codility.com/demo/results/training9YJEVN-BQE/
 * 
 * @author bash
 *
 */
public class L4MaxCounters {

	public static void main(String[] args) {
		caller(5, new int[] { 3, 4, 4, 6, 1, 4, 4 });
		caller(3, new int[] { 4, 1, 3, 2 });
		caller(3, new int[] { 1, 4, 1 });
		caller(2, new int[] { 1, 1 });
		caller(10, new int[] { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11 });
		caller(10, new int[] { 1, 2, 3, 4, 5, 1, 2, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 6, 7, 8, 9, 10, 11 });
	}

	public static void caller(int N, int[] A) {
		System.out.println("Number of counters = " + N);
		System.out.println("Input Array = " + Arrays.toString(A));
		System.out.println("Result array after operation: " + Arrays.toString(solution(N, A)));
		System.out.println();
	}

	public static int[] solution(int N, int[] A) {
		int[] counters = new int[N];
		int maxValueOnAnyCounter = 0, effectiveMaxValue = 0, countedValueOnThisCounter = 0;
		for (int counterNumber : A) {
			if (counterNumber == N + 1) {
				// Effective max value is what every counter should have from now on.
				effectiveMaxValue = maxValueOnAnyCounter;
			} else {
				countedValueOnThisCounter = counters[counterNumber - 1];

				// If current value on this counter is less than the effective maximum, then set
				// the counter to effective max first and then increment it
				countedValueOnThisCounter = countedValueOnThisCounter < effectiveMaxValue ? effectiveMaxValue + 1
						: (countedValueOnThisCounter + 1);
				counters[counterNumber - 1] = countedValueOnThisCounter;

				// Set the new maximum but it wont become effective maximum until N+1 is
				// encountered.
				if (countedValueOnThisCounter > maxValueOnAnyCounter) {
					maxValueOnAnyCounter = countedValueOnThisCounter;
				}
			}
		}

		// for any non visited counters, set them to effective max value
		for (int i = 0; i < N; i++) {
			counters[i] = counters[i] < effectiveMaxValue ? effectiveMaxValue : counters[i];
		}
		return counters;
	}

}
