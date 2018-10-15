package sandbox;

import java.util.Arrays;

public class G3Merge2PreSortedArrays {

	public static void main(String[] args) {
		caller(new int[] { 1, 3, 5, 7 }, new int[] { 0, 2, 4, 6 });
		caller(new int[] { 0, 0, 0, 0, 99, 99, 99, 99, 1000, 1000, 1000, 1000 },
				new int[] { 1, 1, 1, 1, 100, 100, 100, 100, 2000, 2000, 2000, 2000 });
		caller(new int[] { 5, 4, 3, 2, 1 }, new int[] { 9, 8, 7, 6, 5 }); // INVALID INPUT AND SO OUTPUT WILL BE
																			// UNPREDICTABLE
	}

	public static void caller(int[] A, int[] B) {
		System.out.println("Sorted Array 1: " + Arrays.toString(A));
		System.out.println("Sorted Array 2: " + Arrays.toString(B));
		System.out.println("Merged Array: " + Arrays.toString(solution(A, B)));

	}

	public static int[] solution(int[] A, int[] B) {
		int totalLength = A.length + B.length;
		int[] result = new int[totalLength];

		for (int i = 0, indxA = 0, indxB = 0, lengthA = A.length, lengthB = B.length; i < totalLength; i++) {
			if (indxA >= lengthA) {
				result[i] = B[indxB++];
			} else if (indxB >= lengthB) {
				result[i] = A[indxA++];
			} else if (A[indxA] < B[indxB]) {
				result[i] = A[indxA++];
			} else {
				result[i] = B[indxB++];
			}
		}
		return result;
	}
}
