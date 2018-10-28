package sandbox;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Split an array in two, where one side has as many occurrences of x as the
 * other side has occurrences of any number but x.
 * 
 * E.g., Given the array : [5, 5, 2, 3, 5, 1, 6] and x being "5", the function
 * should return "4" (Position 4, holding the number "3" above is the point
 * where you have 2 5's on the one side, and two "not fives" on the other.
 * 
 * @author bash
 *
 */
public class TopTaleTask1ArraySplitToXvsNonX {

	public static void main(String[] args) {
		caller(5, new int[] { 5, 5, 2, 3, 5, 1, 6 });
		caller(4, new int[] { 1, 4, 1 });
		caller(1, new int[] { 1, 1 });
	}

	public static void caller(int X, int[] A) {
		System.out.println("Number to be considered: " + X);
		System.out.println("Input Array: " + Arrays.toString(A));
		System.out.println("Split position: " + solution(X, A));
		System.out.println();
	}

	public static int solution1(int X, int[] A) {
		int splitPosition = 1;

		while (splitPosition < A.length - 2) {
			int xCountLHS = 0, nonXCountRHS = 0;
			for (int i = 0; i < splitPosition; i++) {
				if (A[i] == X) {
					xCountLHS++;
				}
			}

			for (int i = splitPosition; i < A.length; i++) {
				if (A[i] != X) {
					nonXCountRHS++;
				}
			}

			if (xCountLHS == nonXCountRHS) {
				return splitPosition;
			}
			splitPosition++;
		}
		return -1;
	}

	public static int solution2(int X, int[] A) {
		int splitPosition = 1;

		List<Integer> AList = Arrays.stream(A).boxed().collect(Collectors.toList());
		while (splitPosition < A.length - 2) {
			int xCountLHS = 0, nonXCountRHS = 0;
			xCountLHS = (int) AList.subList(0, splitPosition).stream().filter(x -> x == X).count();
			nonXCountRHS = (int) AList.subList(splitPosition, A.length).stream().filter(x -> x != X).count();
			if (xCountLHS == nonXCountRHS) {
				return splitPosition;
			}
			splitPosition++;
		}
		return -1;
	}

	public static int solution3(int X, int[] A) {
		int splitPosition = 1;

		List<Integer> AList = Arrays.stream(A).boxed().collect(Collectors.toList());
		while (splitPosition < A.length - 2) {
			int xCountLHS = 0, nonXCountRHS = 0;
			xCountLHS = Collections.frequency(AList.subList(0, splitPosition), X);
			List<Integer> sublistRHS = AList.subList(splitPosition, A.length);
			nonXCountRHS = sublistRHS.size() - Collections.frequency(sublistRHS, X);
			if (xCountLHS == nonXCountRHS) {
				return splitPosition;
			}
			splitPosition++;
		}
		return -1;
	}

	public static int solution4(int X, int[] A) {
		int xCountLHS = X == A[0] ? 1 : 0;
		int nonXCountRHS = 0;

		// Save the first element for LHS.
		for (int i = 1; i < A.length; i++) {
			nonXCountRHS += X != A[i] ? 1 : 0;
		}

		if (xCountLHS == nonXCountRHS)
			return 1;

		// Skip the last element since its already added to RHS
		for (int i = 1; i < A.length - 1; i++) {
			xCountLHS += X == A[i] ? 1 : 0;
			nonXCountRHS -= X != A[i] ? 1 : 0;
			if (xCountLHS == nonXCountRHS)
				return i;
		}
		return -1;
	}

	/**
	 * https://github.com/Jarosh/Exercises/wiki/Split-array-into-two-parts,-such-that-the-number-of-elements-equal-to-X-in-the-first-part-is-the-same-as-the-number-of-elements-different-from-X-in-the-other-part
	 * 
	 * @param X
	 * @param A
	 * @return
	 */
	public static int solution(int X, int[] A) {
		int totalXCount = 0, consecutiveXCount = 0, length = A.length;
		for (int i : A) {
			if (i == X) {
				totalXCount++;
				consecutiveXCount++;
			} else {
				consecutiveXCount = 0;
			}
		}

		return (A[length - 1] != X || totalXCount > consecutiveXCount) ? (length - totalXCount) : length;

	}
}
