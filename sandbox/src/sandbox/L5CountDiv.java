package sandbox;

/**
 * Compute number of integers divisible by k in range [a..b].
 * 
 * Scored 100%: https://app.codility.com/demo/results/trainingS3QUUF-NWA/
 * 
 * @author bash
 *
 */
public class L5CountDiv {

	public static void main(String[] args) {
		caller(6, 11, 2);
		caller(0, 501, 5);
		caller(5, 501, 5);
		caller(6, 120, 6);
	}

	public static void caller(int A, int B, int K) {
		System.out.printf("Input Range: %s..%s\n", A, B);
		System.out.println("Divisor: " + K);
		System.out.printf("Number of integers divisible by %s are %s\n", K, solution(A, B, K));
		System.out.println();
	}

	public static int solution(int A, int B, int K) {
		int numberOfDivisors = (B / K) - (A / K);
		if (A % K == 0) {
			numberOfDivisors++;
		}
		return numberOfDivisors;
	}
}
