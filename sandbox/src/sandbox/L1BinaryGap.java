package sandbox;

import java.util.Arrays;

/**
 * 
 * Find longest sequence of zeros in binary representation of an integer.
 * 
 * Got 100% score in codility:
 * https://app.codility.com/demo/results/trainingW3MFRB-5DT/
 * 
 * @author bash
 *
 */
public class L1BinaryGap {

	public static void main(String[] args) throws Exception {
//		Scanner scanner = new Scanner(System.in);
//		int N = scanner.nextInt();		
		int[] arrayN = { 1041, 15, 32, -55, -2, 0, 1, 2147483647, 5, 55, 66, 145, 999, 1000, 1002, 10000 };
		for (int i : arrayN) {
			caller(i);
		}
	}

	public static void caller(int N) {
		System.out.println("Input number: " + N);
		System.out.println("Its Binary number: " + Integer.toBinaryString(N));
		System.out.println("Max count of zeros: " + solution(N));
		System.out.println();
	}

	public static int solution(int N) {
		String strippedOffString = Integer.toBinaryString(N).replaceAll(".0*$", "");
		return Arrays.asList(strippedOffString.split("1")).stream().mapToInt(zeros -> zeros.length()).max().orElse(0);
	}
}
