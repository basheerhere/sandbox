package sandbox;

import java.util.Arrays;
import java.util.stream.IntStream;

/**
 * Find the missing element in a given permutation.
 * 
 * Scored 100%: 
 * https://app.codility.com/demo/results/training499MJT-JFJ/
 * https://app.codility.com/demo/results/trainingEVG27E-26B/
 * 
 * @author bash
 *
 */
public class L3PermMissingElem {

	public static void main(String[] args) {
		int[] A = { 1, 2, 3, 4, 5, 6, 7, 9 };
		System.out.println("Input Array: " + Arrays.toString(A));
		System.out.println("Missing int = " + solution(A));
	}

	/**
	 * Scored 80% in Codility:
	 * https://app.codility.com/demo/results/trainingWYQ3S9-X7T/ Score reduced due
	 * to performance issues with medium test, length = ~10,000
	 * 
	 * @param A
	 * @return
	 */
	public static int solution1(int[] A) {
		int expectedSum = IntStream.rangeClosed(0, A.length + 1).sum();
		int actualSum = Arrays.stream(A).sum();
		int missingInt = expectedSum - actualSum;
		return missingInt;
	}

	/**
	 * Scored 100% in codility:
	 * https://app.codility.com/demo/results/training499MJT-JFJ/
	 * 
	 * @param A
	 * @return
	 */
	public static int solution2(int[] A) {
		long length = A.length;
		long missingInt = (length + 1) * (length + 2) / 2;
		for (int i : A) {
			missingInt -= i;
		}
		return (int) missingInt;
	}

	/**
	 * Scored 100%:
	 * https://app.codility.com/demo/results/trainingEVG27E-26B/
	 * 
	 * @param A
	 * @return
	 */
	public static int solution(int[] A) {
		long length = A.length;
		long expectedSum = (length + 1) * (length + 2) / 2;
		long actualSum = 0;
		for (int i : A) {
			actualSum += i;
		}
		return (int) (expectedSum - actualSum);
	}
}
