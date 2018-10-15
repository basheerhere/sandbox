package sandbox;

import java.util.Arrays;

/**
 * Find value that occurs in odd number of elements.
 * 
 * Got 100% score in codility:
 * https://app.codility.com/demo/results/training4X7W24-WMM/
 * @author bash
 *
 */
public class L2OddOccurrancesInArray {

	public static void main(String[] args) {
		int[] A = { 1, 2, 1, 2, 3, 4, 3, 4, 5 };
		System.out.println("Input Array: " + Arrays.toString(A));
		System.out.println("Missing int = " + solution(A));
	}

	public static int solution(int[] A) {
		int missingInt = 0;
		for (int i : A) {
			missingInt ^= i;
		}
		return missingInt;
	}
}
